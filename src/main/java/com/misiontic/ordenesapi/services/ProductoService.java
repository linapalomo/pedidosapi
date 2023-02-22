package com.misiontic.ordenesapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.misiontic.ordenesapi.entity.Producto;
import com.misiontic.ordenesapi.exceptions.GeneralServiceException;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;
import com.misiontic.ordenesapi.repository.ProductoRepository;
import com.misiontic.ordenesapi.validators.ProductoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepo;

	public Producto findById(Long productoId) {

		try {
			Producto producto = productoRepo.findById(productoId)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			return producto;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public void delete(Long productoId) {
		try {
			Producto producto = productoRepo.findById(productoId)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			productoRepo.delete(producto);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public List<Producto> findAll() {
		try {
			List<Producto> arregloProductos = productoRepo.findAll();
			return arregloProductos;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Producto create(Producto producto) {
		try {
			ProductoValidator.validador(producto);
			Producto nuevoProducto = productoRepo.save(producto);
			return nuevoProducto;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Producto update(Producto producto) {
		try {
			ProductoValidator.validador(producto);
			Producto existeProducto = productoRepo.findById(producto.getIdProducto())
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));

			existeProducto.setNombre(producto.getNombre());
			existeProducto.setReferencia(producto.getReferencia());
			existeProducto.setFecha(producto.getFecha());
			existeProducto.setCantidad(producto.getCantidad());
			existeProducto.setPrecioCosto(producto.getPrecioCosto());
			existeProducto.setPrecioVenta(producto.getPrecioVenta());
			productoRepo.save(existeProducto);
			return existeProducto;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

}
