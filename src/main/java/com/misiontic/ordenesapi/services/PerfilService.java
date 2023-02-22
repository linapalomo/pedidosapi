package com.misiontic.ordenesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic.ordenesapi.entity.Perfil;
import com.misiontic.ordenesapi.exceptions.GeneralServiceException;
import com.misiontic.ordenesapi.exceptions.NoDataFoundException;
import com.misiontic.ordenesapi.exceptions.ValidateServiceException;
import com.misiontic.ordenesapi.repository.PerfilRepository;
import com.misiontic.ordenesapi.validators.PerfilValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepo;

	public Perfil findById(Long perfilId) {

		try {
			Perfil perfil = perfilRepo.findById(perfilId)
					.orElseThrow(() -> new NoDataFoundException("No existe el perfil."));
			return perfil;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public void delete(Long perfilId) {
		try {
			Perfil perfil = perfilRepo.findById(perfilId)
					.orElseThrow(() -> new NoDataFoundException("No existe el perfil."));
			perfilRepo.delete(perfil);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public List<Perfil> findAll() {
		try {
			List<Perfil> arregloPerfiles = perfilRepo.findAll();
			return arregloPerfiles;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Perfil create(Perfil perfil) {
		try {
			PerfilValidator.validador(perfil);
			Perfil nuevoPerfil = perfilRepo.save(perfil);
			return nuevoPerfil;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	public Perfil update(Perfil perfil) {
		try {
			PerfilValidator.validador(perfil);
			Perfil existePerfil = perfilRepo.findById(perfil.getIdPerfil())
					.orElseThrow(() -> new NoDataFoundException("No existe el perfil."));
			
			existePerfil.setNombre(perfil.getNombre());
			
			perfilRepo.save(existePerfil);
			return existePerfil;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

}
