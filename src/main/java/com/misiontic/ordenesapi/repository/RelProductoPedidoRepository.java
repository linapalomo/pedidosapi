package com.misiontic.ordenesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic.ordenesapi.entity.RelProductoPedido;

@Repository
public interface RelProductoPedidoRepository extends JpaRepository<RelProductoPedido, Long> {

}
