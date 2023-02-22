package com.misiontic.ordenesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic.ordenesapi.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
