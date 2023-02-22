package com.misiontic.ordenesapi.validators;

import com.misiontic.ordenesapi.entity.Pedido;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;

public class PedidoValidator {
	
	public static void validador(Pedido pedido) {
		
		if (pedido.getIdCliente() == null) {
			throw new ValidateServiceException("El idcliente para el pedido es obligatorio.");
		}
		
		if (pedido.getFecha() == null) {
			throw new ValidateServiceException("La fecha del pedido es obligatoria.");
		}
		
		if (pedido.getTotal() == null) {
			throw new ValidateServiceException("El total del pedido es ogligatorio.");
		}
		
	}

}
