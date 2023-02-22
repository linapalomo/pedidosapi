package com.misiontic.ordenesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic.ordenesapi.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
