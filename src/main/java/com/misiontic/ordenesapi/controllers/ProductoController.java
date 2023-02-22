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

import com.misiontic.ordenesapi.converters.ProductoConverter;
import com.misiontic.ordenesapi.dtos.ProductoDTO;
import com.misiontic.ordenesapi.entity.Producto;
import com.misiontic.ordenesapi.services.ProductoService;
import com.misiontic.ordenesapi.utils.WrapperResponse;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	private ProductoConverter converter = new ProductoConverter();

	@GetMapping(value = "/productos/{productoId}")
	public ResponseEntity<WrapperResponse<ProductoDTO>> findById(@PathVariable("productoId") Long productoId) {
		Producto producto = productoService.findById(productoId);
		ProductoDTO proDTO = converter.fromEntity(producto);
		return new WrapperResponse<>(true, "success", proDTO).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/productos/{productoId}")
	public ResponseEntity<?> delete(@PathVariable("productoId") Long productoId) {
		productoService.delete(productoId);
		
		return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/productos")
	public ResponseEntity<WrapperResponse<List<ProductoDTO>>> findAll() {
		List<Producto> arregloProductos = productoService.findAll();
		List<ProductoDTO> DTOproductos = converter.fromEntity(arregloProductos);
		
		return new WrapperResponse<>(true, "success", DTOproductos).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/productos")
	public ResponseEntity<WrapperResponse<ProductoDTO>> create(@RequestBody ProductoDTO producto) {
		Producto nuevoProducto = productoService.create(converter.fromDTO(producto));
		ProductoDTO proDTO = converter.fromEntity(nuevoProducto);
		
		return new WrapperResponse<>(true, "success", proDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/productos")
	public ResponseEntity<WrapperResponse<ProductoDTO>> update(@RequestBody ProductoDTO producto) {
		Producto existeProducto = productoService.update(converter.fromDTO(producto));
		ProductoDTO proDTO = converter.fromEntity(existeProducto);
		
		return new WrapperResponse<>(true, "success", proDTO).createResponse(HttpStatus.OK);
	}

}
