package com.misiontic.ordenesapi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpedido", nullable = false)
	private Long idPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcliente", nullable = false, updatable = true, referencedColumnName = "idusuario")
	private Usuario idCliente;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "total", nullable = false)
	private Double total;
	
	//@OneToMany(mappedBy = "idPedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//private List<RelProductoPedido> listaPedidos;

}
