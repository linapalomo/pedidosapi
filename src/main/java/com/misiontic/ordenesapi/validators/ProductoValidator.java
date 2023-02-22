package com.misiontic.ordenesapi.validators;

import com.misiontic.ordenesapi.entity.Producto;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;

public class ProductoValidator {

	public static void validador(Producto producto) {

		if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre del producto es obligatorio.");
		}

		if (producto.getReferencia() == null || producto.getReferencia().trim().isEmpty()) {
			throw new ValidateServiceException("La referencia del producto es obligatorio.");
		}

		if (producto.getFecha() == null) {
			throw new ValidateServiceException("La fecha del producto es obligatorio.");
		}

		if (producto.getCantidad() == null || producto.getCantidad() <= 0) {
			throw new ValidateServiceException("La cantidad del producto es obligatorio.");
		}

		if (producto.getPrecioCosto() == null || producto.getPrecioCosto() <= 0) {
			throw new ValidateServiceException("El precio costo del producto es obligatorio.");
		}

		if (producto.getPrecioVenta() == null || producto.getPrecioVenta() <= 0) {
			throw new ValidateServiceException("El precio venta del producto es obligatorio.");
		}

		if (producto.getNombre().length() > 200) {
			throw new ValidateServiceException("El nombre del producto es muy largo debe tener maximo 200 caracteres.");
		}
		
		if (producto.getReferencia().length() > 50) {
			throw new ValidateServiceException("La referencia del producto es muy larga debe tener maximo 50 caracteres.");
		}

	}

}
