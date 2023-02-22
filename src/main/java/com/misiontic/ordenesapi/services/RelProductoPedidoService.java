package com.misiontic.ordenesapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic.ordenesapi.entity.RelProductoPedido;
import com.misiontic.ordenesapi.exceptions.GeneralServiceException;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;
import com.misiontic.ordenesapi.repository.RelProductoPedidoRepository;
import com.misiontic.ordenesapi.validators.RelProductoPedidoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelProductoPedidoService {
	
	@Autowired
	private RelProductoPedidoRepository relRepo;
	
	public void delete(Long relId) {
		try {
			RelProductoPedido objRel = relRepo.findById(relId)
					.orElseThrow(() -> new NoDataFoundException("No existe el detalle pedido."));
			relRepo.delete(objRel);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public RelProductoPedido create(RelProductoPedido objRel) {
		try {
			RelProductoPedidoValidator.validador(objRel);
			RelProductoPedido nuevoRel = relRepo.save(objRel);
			return nuevoRel;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public RelProductoPedido update(RelProductoPedido objRel) {
		try {
			RelProductoPedido existeRel = relRepo.findById(objRel.getIdRelProPed())
					.orElseThrow(() -> new NoDataFoundException("No existe el detalle del pedido."));
			
			existeRel.setCantidad(objRel.getCantidad());
			existeRel.setTotal(objRel.getTotal());
			
			relRepo.save(existeRel);
			return existeRel;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

}
