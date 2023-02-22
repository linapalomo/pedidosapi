package com.misiontic.ordenesapi.validators;

import com.misiontic.ordenesapi.entity.Perfil;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;

public class PerfilValidator {
	
	public static void validador(Perfil perfil) {
		
		if (perfil.getNombre() == null || perfil.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre del perfil es obligatorio.");
		}
		
		if (perfil.getNombre().length() > 200) {
			throw new ValidateServiceException("El nombre del perfil debe tener máximo 200 caracteres.");
		}

	}
}
