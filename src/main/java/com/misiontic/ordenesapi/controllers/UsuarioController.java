package com.misiontic.ordenesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic.ordenesapi.converters.UsuarioConverter;
import com.misiontic.ordenesapi.dtos.LoginRequestDTO;
import com.misiontic.ordenesapi.dtos.LoginResponseDTO;
import com.misiontic.ordenesapi.dtos.UsuarioDTO;
import com.misiontic.ordenesapi.entity.Usuario;
import com.misiontic.ordenesapi.services.UsuarioService;
import com.misiontic.ordenesapi.utils.WrapperResponse;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	private UsuarioConverter converter = new UsuarioConverter();
	
	@GetMapping(value = "/usuarios/{usuarioId}")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> findById(@PathVariable("usuarioId") Long usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		UsuarioDTO usuarioDTO = converter.fromEntity(usuario);
		return new WrapperResponse<>(true, "success", usuarioDTO).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/usuarios/{usuarioId}")
	public ResponseEntity<?> delete(@PathVariable("usuarioId") Long usuarioId) {
		usuarioService.delete(usuarioId);
		return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/usuarios")
	public ResponseEntity<WrapperResponse<List<UsuarioDTO>>> findAll() {
		List<Usuario> arregloUsuarios = usuarioService.findAll();
		List<UsuarioDTO> DTOusuarios = converter.fromEntity(arregloUsuarios);
		return new WrapperResponse<>(true, "success", DTOusuarios).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/usuarios")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> create(@RequestBody UsuarioDTO usuario) {
		Usuario nuevoUsuario = usuarioService.create(converter.fromDTO(usuario));
		UsuarioDTO usuarioDTO = converter.fromEntity(nuevoUsuario);
		return new WrapperResponse<>(true, "success", usuarioDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/usuarios")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> update(@RequestBody UsuarioDTO usuario) {
		Usuario existeUsuario = usuarioService.update(converter.fromDTO(usuario));
		UsuarioDTO usuarioDTO = converter.fromEntity(existeUsuario);
		return new WrapperResponse<>(true, "success", usuarioDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> signUp(@RequestBody UsuarioDTO usuario) {
		Usuario nuevoUsuario = usuarioService.create(converter.fromDTO(usuario));
		UsuarioDTO usuarioDTO = converter.fromEntity(nuevoUsuario);
		return new WrapperResponse<>(true, "El usuario fue creado correctamente.", usuarioDTO).createResponse(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = usuarioService.login(request);
		return new WrapperResponse<>(true, "success", response).createResponse(HttpStatus.OK);
	}

}
