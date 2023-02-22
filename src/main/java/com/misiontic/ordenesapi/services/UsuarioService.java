package com.misiontic.ordenesapi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.misiontic.ordenesapi.converters.UsuarioConverter;
import com.misiontic.ordenesapi.dtos.LoginRequestDTO;
import com.misiontic.ordenesapi.dtos.LoginResponseDTO;
import com.misiontic.ordenesapi.entity.Usuario;
import com.misiontic.ordenesapi.exceptions.GeneralServiceException;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;
import com.misiontic.ordenesapi.repository.UsuarioRepository;
import com.misiontic.ordenesapi.validators.UsuarioValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {
	
	@Value("${jwt.clave}")
	private String jwtSecret;

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	private UsuarioConverter converter = new UsuarioConverter();

	public Usuario findById(Long usuarioId) {

		try {
			Usuario usuario = usuarioRepo.findById(usuarioId)
					.orElseThrow(() -> new NoDataFoundException("No existe el usuario."));
			return usuario;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public void delete(Long usuarioId) {
		try {
			Usuario usuario = usuarioRepo.findById(usuarioId)
					.orElseThrow(() -> new NoDataFoundException("No existe el usuario."));
			usuarioRepo.delete(usuario);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public List<Usuario> findAll() {
		try {
			List<Usuario> arregloUsuarios = usuarioRepo.findAll();
			return arregloUsuarios;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Usuario create(Usuario usuario) {
		try {
			Usuario existeUsuario = usuarioRepo.findBynombreUsuario(usuario.getNombreUsuario()).orElse(null);

			if (existeUsuario != null) {
				throw new ValidateServiceException("El nombre de usuario ya existe.");
			}

			UsuarioValidator.validador(usuario);
			Usuario nuevoUsuario = usuarioRepo.save(usuario);
			return nuevoUsuario;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Usuario update(Usuario usuario) {
		try {
			UsuarioValidator.validador(usuario);
			Usuario existeUsuario = usuarioRepo.findById(usuario.getIdUsuario())
					.orElseThrow(() -> new NoDataFoundException("No existe el usuario."));

			existeUsuario.setIdPerfil(usuario.getIdPerfil());
			existeUsuario.setNombres(usuario.getNombres());
			existeUsuario.setApellidos(usuario.getApellidos());
			existeUsuario.setNombreUsuario(usuario.getNombreUsuario());
			existeUsuario.setClave(usuario.getClave());

			usuarioRepo.save(existeUsuario);
			return existeUsuario;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Usuario signUp(Usuario usuario) {
		try {
			Usuario existeUsuario = usuarioRepo.findBynombreUsuario(usuario.getNombreUsuario()).orElse(null);

			if (existeUsuario != null) {
				throw new ValidateServiceException("El nombre de usuario ya existe.");
			}

			UsuarioValidator.validador(usuario);
			Usuario nuevoUsuario = usuarioRepo.save(usuario);
			return nuevoUsuario;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public LoginResponseDTO login(LoginRequestDTO request) {
		
		try {
			//validamos el usuario que si exista
			Usuario usuario = usuarioRepo.findBynombreUsuario(request.getNombreUsuario())
					.orElseThrow(() -> new ValidateServiceException("Nombre de usuario o clave incorrecta."));
			
			//validamos la clave del usuario
			if (!usuario.getClave().equals(request.getClave())) {
				throw new ValidateServiceException("Nombre de usuario o clave incorrecta.");
			}
			
			//crear el token de seguridad
			String token = createToken(usuario);
			
			return new LoginResponseDTO(converter.fromEntity(usuario), token);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}
	
	public String createToken(Usuario usuario) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60));
		
		return Jwts.builder()
				.setSubject(usuario.getNombreUsuario())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("JWT in a particular format/configuration that does not match the format expected");
		} catch (MalformedJwtException e) {
			log.error("JWT was not correctly constructed and should be rejected.");
		} catch (SignatureException e) {
			log.error("signature or verifying an existing signature of a JWT failed.");
		} catch (ExpiredJwtException e) {
			log.error("JWT was accepted after it expired and must be rejected.");
		}
		return false;
	}
	
	public String getNombreUsuarioFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("Token invalido.");
		}
	}

}
