package com.misiontic.ordenesapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic.ordenesapi.converters.RelProductoPedidoConverter;
import com.misiontic.ordenesapi.dtos.RelProductoPedidoDTO;
import com.misiontic.ordenesapi.entity.RelProductoPedido;
import com.misiontic.ordenesapi.services.RelProductoPedidoService;
import com.misiontic.ordenesapi.utils.WrapperResponse;

@RestController
public class RelProductoPedidoController {
	
	@Autowired
	private RelProductoPedidoService relService;
	private RelProductoPedidoConverter converter = new RelProductoPedidoConverter();
	
	@DeleteMapping(value = "/detalle-pedido/{detalleId}")
	public ResponseEntity<?> delete(@PathVariable("detalleId") Long detalleId) {
		relService.delete(detalleId);
		return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/detalle-pedido")
	public ResponseEntity<WrapperResponse<RelProductoPedidoDTO>> create(@RequestBody RelProductoPedidoDTO objRel) {
		RelProductoPedido nuevoRel = relService.create(converter.fromDTO(objRel));
		RelProductoPedidoDTO relDTO = converter.fromEntity(nuevoRel);
		return new WrapperResponse<>(true, "success", relDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/detalle-pedido")
	public ResponseEntity<WrapperResponse<RelProductoPedidoDTO>> update(@RequestBody RelProductoPedidoDTO objRel) {
		RelProductoPedido existeRel = relService.update(converter.fromDTO(objRel));
		RelProductoPedidoDTO relDTO = converter.fromEntity(existeRel);
		return new WrapperResponse<>(true, "success", relDTO).createResponse(HttpStatus.OK);
	}

}
