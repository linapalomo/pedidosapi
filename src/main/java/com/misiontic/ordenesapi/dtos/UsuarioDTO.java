package com.misiontic.ordenesapi.dtos;
import com.misiontic.ordenesapi.entity.Perfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long idUsuario;
	private Perfil idPerfil;
	private String nombres;
	private String apellidos;
	private String nombreUsuario;
	private String clave;

}
