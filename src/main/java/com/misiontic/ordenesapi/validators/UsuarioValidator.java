package com.misiontic.ordenesapi.validators;

import com.misiontic.ordenesapi.entity.Usuario;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;

public class UsuarioValidator {

	public static void validador(Usuario usuario) {

		if (usuario.getIdPerfil() == null) {
			throw new ValidateServiceException("El perfil del usuario es obligatorio.");
		}

		if (usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()) {
			throw new ValidateServiceException("Los nombres del usuario son obligatorios.");
		}

		if (usuario.getApellidos() == null || usuario.getApellidos().trim().isEmpty()) {
			throw new ValidateServiceException("Los apellidos del usuario son obligatorios.");
		}

		if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre del usuario es obligatorio.");
		}

		if (usuario.getClave() == null || usuario.getClave().trim().isEmpty()) {
			throw new ValidateServiceException("La clave del usuario es obligatoria.");
		}

		if (usuario.getNombres().length() > 200) {
			throw new ValidateServiceException("Los nombres del usuario deben ser máximo 200 caracteres.");
		}

		if (usuario.getApellidos().length() > 200) {
			throw new ValidateServiceException("Los apellidos del usuario deben ser máximo 200 caracteres.");
		}

		if (usuario.getNombreUsuario().length() > 100) {
			throw new ValidateServiceException("El nombre del usuario deben ser máximo 100 caracteres.");
		}

		if (usuario.getClave().length() > 200) {
			throw new ValidateServiceException("La clave del usuario deben ser máximo 100 caracteres.");
		}

	}

}
