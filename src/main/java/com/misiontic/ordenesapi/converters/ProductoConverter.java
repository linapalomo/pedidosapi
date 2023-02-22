package com.misiontic.ordenesapi.converters;

import com.misiontic.ordenesapi.dtos.ProductoDTO;
import com.misiontic.ordenesapi.entity.Producto;

public class ProductoConverter extends AbstractConverter<Producto, ProductoDTO> {

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		return ProductoDTO.builder()
				.idProducto(entity.getIdProducto())
				.nombre(entity.getNombre())
				.referencia(entity.getReferencia())
				.fecha(entity.getFecha())
				.cantidad(entity.getCantidad())
				.precioCosto(entity.getPrecioCosto())
				.precioVenta(entity.getPrecioVenta())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		return Producto.builder()
				.idProducto(dto.getIdProducto())
				.nombre(dto.getNombre())
				.referencia(dto.getReferencia())
				.fecha(dto.getFecha())
				.cantidad(dto.getCantidad())
				.precioCosto(dto.getPrecioCosto())
				.precioVenta(dto.getPrecioVenta())
				.build();
	}
	
}
