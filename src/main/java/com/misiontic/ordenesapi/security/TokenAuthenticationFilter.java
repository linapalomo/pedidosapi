package com.misiontic.ordenesapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.misiontic.ordenesapi.entity.Usuario;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.repository.UsuarioRepository;
import com.misiontic.ordenesapi.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = getJwtFromRequest(request);
			
			if (StringUtils.hasText(jwt) && usuarioService.validateToken(jwt)) {
				String nombreUsuario = usuarioService.getNombreUsuarioFromToken(jwt);
				
				Usuario usuario = usuarioRepo.findBynombreUsuario(nombreUsuario)
						.orElseThrow(() -> new NoDataFoundException("No existe el usuario."));
				
				UsuarioPrincipal principal = UsuarioPrincipal.create(usuario);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Error al auntenticar al usuario.");
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken ) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	

}
