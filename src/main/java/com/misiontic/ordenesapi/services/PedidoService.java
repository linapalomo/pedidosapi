package com.misiontic.ordenesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic.ordenesapi.entity.Pedido;
import com.misiontic.ordenesapi.exceptions.GeneralServiceException;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;
import com.misiontic.ordenesapi.repository.PedidoRepository;
import com.misiontic.ordenesapi.validators.PedidoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	public Pedido findById(Long pedidoId) {

		try {
			Pedido pedido = pedidoRepo.findById(pedidoId)
					.orElseThrow(() -> new NoDataFoundException("No existe el pedido."));
			return pedido;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public void delete(Long pedidoId) {
		try {
			Pedido pedido = pedidoRepo.findById(pedidoId)
					.orElseThrow(() -> new NoDataFoundException("No existe el pedido."));
			pedidoRepo.delete(pedido);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public List<Pedido> findAll() {
		try {
			List<Pedido> arregloPedidos = pedidoRepo.findAll();
			return arregloPedidos;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Pedido create(Pedido pedido) {
		try {
			PedidoValidator.validador(pedido);
			Pedido nuevoPedido = pedidoRepo.save(pedido);
			return nuevoPedido;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Pedido update(Pedido pedido) {
		try {
			PedidoValidator.validador(pedido);
			Pedido existePedido = pedidoRepo.findById(pedido.getIdPedido())
					.orElseThrow(() -> new NoDataFoundException("No existe el pedido."));
			
			existePedido.setFecha(pedido.getFecha());
			existePedido.setTotal(pedido.getTotal());
			existePedido.setIdCliente(pedido.getIdCliente());
			
			pedidoRepo.save(existePedido);
			return existePedido;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

}
