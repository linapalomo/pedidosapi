package com.misiontic.ordenesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic.ordenesapi.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
