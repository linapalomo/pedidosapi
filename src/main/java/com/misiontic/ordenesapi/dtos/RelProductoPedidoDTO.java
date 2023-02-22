package com.misiontic.ordenesapi.dtos;

import com.misiontic.ordenesapi.entity.Pedido;
import com.misiontic.ordenesapi.entity.Producto;

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
public class RelProductoPedidoDTO {
	
	private Long idRelProPed;
	private Producto idProducto;
	private Pedido idPedido;
	private Integer cantidad;
	private Double total;

}
