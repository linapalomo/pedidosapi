package com.misiontic.ordenesapi.converters;

import com.misiontic.ordenesapi.dtos.UsuarioDTO;
import com.misiontic.ordenesapi.entity.Usuario;

public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO fromEntity(Usuario entity) {
		return UsuarioDTO.builder()
				.idUsuario(entity.getIdUsuario())
				.idPerfil(entity.getIdPerfil())
				.nombres(entity.getNombres())
				.apellidos(entity.getApellidos())
				.nombreUsuario(entity.getNombreUsuario())
				.clave(entity.getClave())
				.build();
	}

	@Override
	public Usuario fromDTO(UsuarioDTO dto) {
		return Usuario.builder()
				.idUsuario(dto.getIdUsuario())
				.idPerfil(dto.getIdPerfil())
				.nombres(dto.getNombres())
				.apellidos(dto.getApellidos())
				.nombreUsuario(dto.getNombreUsuario())
				.clave(dto.getClave())
				.build();
	}

}
