package com.misiontic.ordenesapi.validators;

import com.misiontic.ordenesapi.entity.RelProductoPedido;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;

public class RelProductoPedidoValidator {

	public static void validador(RelProductoPedido objRel) {

		if (objRel.getIdProducto() == null) {
			throw new ValidateServiceException("El idproducto es obligatorio en el detalle pedido.");
		}

		if (objRel.getIdPedido() == null) {
			throw new ValidateServiceException("El idpedido es obligatorio en el detalle pedido.");
		}

		if (objRel.getCantidad() == null) {
			throw new ValidateServiceException("La cantidad es obligatoria en el detalle pedido.");
		}

		//if (objRel.getCantidad() > 0) {
			//throw new ValidateServiceException("La cantidad debe ser mayor a cero en el detalle pedido.");
		//}

		if (objRel.getTotal() == null) {
			throw new ValidateServiceException("El total es obligatorio en el detalle pedido.");
		}

	}

}
