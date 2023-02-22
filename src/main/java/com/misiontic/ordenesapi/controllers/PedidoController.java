package com.misiontic.ordenesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic.ordenesapi.converters.PedidoConverter;
import com.misiontic.ordenesapi.dtos.PedidoDTO;
import com.misiontic.ordenesapi.entity.Pedido;
import com.misiontic.ordenesapi.services.PedidoService;
import com.misiontic.ordenesapi.utils.WrapperResponse;

@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	private PedidoConverter converter = new PedidoConverter();
	
	@GetMapping(value = "/pedidos/{pedidoId}")
	public ResponseEntity<WrapperResponse<PedidoDTO>> findById(@PathVariable("pedidoId") Long pedidoId) {
		Pedido pedido = pedidoService.findById(pedidoId);
		PedidoDTO pedidoDTO = converter.fromEntity(pedido);
		return new WrapperResponse<>(true, "success", pedidoDTO).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/pedidos/{pedidoId}")
	public ResponseEntity<?> delete(@PathVariable("pedidoId") Long pedidoId) {
		pedidoService.delete(pedidoId);
		return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/pedidos")
	public ResponseEntity<WrapperResponse<List<PedidoDTO>>> findAll() {
		List<Pedido> arregloPedidos = pedidoService.findAll();
		List<PedidoDTO> DTOpedidos = converter.fromEntity(arregloPedidos);
		return new WrapperResponse<>(true, "success", DTOpedidos).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/pedidos")
	public ResponseEntity<WrapperResponse<PedidoDTO>> create(@RequestBody PedidoDTO pedido) {
		Pedido nuevoPedido = pedidoService.create(converter.fromDTO(pedido));
		PedidoDTO pedidoDTO = converter.fromEntity(nuevoPedido);
		return new WrapperResponse<>(true, "success", pedidoDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/pedidos")
	public ResponseEntity<WrapperResponse<PedidoDTO>> update(@RequestBody PedidoDTO pedido) {
		Pedido existePedido = pedidoService.update(converter.fromDTO(pedido));
		PedidoDTO pedidoDTO = converter.fromEntity(existePedido);
		return new WrapperResponse<>(true, "success", pedidoDTO).createResponse(HttpStatus.OK);
	}	

}
