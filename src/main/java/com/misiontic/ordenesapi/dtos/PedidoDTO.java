package com.misiontic.ordenesapi.dtos;

import java.time.LocalDate;

import com.misiontic.ordenesapi.entity.Usuario;

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
public class PedidoDTO {

	private Long idPedido;
	private Usuario idCliente;
	private LocalDate fecha;
	private Double total;

}
