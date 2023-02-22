package com.misiontic.ordenesapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "relproductospedidos")
public class RelProductoPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrelproped", nullable = false)
	private Long idRelProPed;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproducto", nullable = false, updatable = false, referencedColumnName = "idproducto")
	private Producto idProducto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpedido", nullable = false, updatable = false, referencedColumnName = "idpedido")
	private Pedido idPedido;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@Column(name = "total", nullable = false)
	private Double total;

}
