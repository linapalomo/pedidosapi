package com.misiontic.ordenesapi.converters;

import com.misiontic.ordenesapi.dtos.PerfilDTO;
import com.misiontic.ordenesapi.entity.Perfil;

public class PerfilConverter extends AbstractConverter<Perfil, PerfilDTO> {

	@Override
	public PerfilDTO fromEntity(Perfil entity) {
		return PerfilDTO.builder()
				.idPerfil(entity.getIdPerfil())
				.nombre(entity.getNombre())
				.build();
	}

	@Override
	public Perfil fromDTO(PerfilDTO dto) {
		return Perfil.builder()
				.idPerfil(dto.getIdPerfil())
				.nombre(dto.getNombre())
				.build();
	}

}
