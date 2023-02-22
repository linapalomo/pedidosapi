package com.misiontic.ordenesapi.converters;

import com.misiontic.ordenesapi.dtos.RelProductoPedidoDTO;
import com.misiontic.ordenesapi.entity.RelProductoPedido;

public class RelProductoPedidoConverter extends AbstractConverter<RelProductoPedido, RelProductoPedidoDTO> {

	@Override
	public RelProductoPedidoDTO fromEntity(RelProductoPedido entity) {
		return RelProductoPedidoDTO.builder()
				.idRelProPed(entity.getIdRelProPed())
				.idProducto(entity.getIdProducto())
				.idPedido(entity.getIdPedido())
				.cantidad(entity.getCantidad())
				.total(entity.getTotal())
				.build();
	}

	@Override
	public RelProductoPedido fromDTO(RelProductoPedidoDTO dto) {
		return RelProductoPedido.builder()
				.idRelProPed(dto.getIdRelProPed())
				.idProducto(dto.getIdProducto())
				.idPedido(dto.getIdPedido())
				.cantidad(dto.getCantidad())
				.total(dto.getTotal())
				.build();
	}

}
