package com.misiontic.ordenesapi.converters;

import com.misiontic.ordenesapi.dtos.PedidoDTO;
import com.misiontic.ordenesapi.entity.Pedido;

public class PedidoConverter extends AbstractConverter<Pedido, PedidoDTO> {

	@Override
	public PedidoDTO fromEntity(Pedido entity) {
		return PedidoDTO.builder()
				.idPedido(entity.getIdPedido())
				.idCliente(entity.getIdCliente())
				.fecha(entity.getFecha())
				.total(entity.getTotal())
				.build();
	}

	@Override
	public Pedido fromDTO(PedidoDTO dto) {
		return Pedido.builder()
				.idPedido(dto.getIdPedido())
				.idCliente(dto.getIdCliente())
				.fecha(dto.getFecha())
				.total(dto.getTotal())
				.build();
	}

}
