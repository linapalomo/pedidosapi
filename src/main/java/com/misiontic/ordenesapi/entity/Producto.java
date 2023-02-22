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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@Column(name="idproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;
	
	@Column(name="nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "referencia", length = 50, nullable = false)
	private String referencia;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@Column(name = "preciocosto", nullable = false)
	private Double precioCosto;
	
	@Column(name = "precioventa", nullable = false)
	private Double precioVenta;
	
	//@OneToMany(mappedBy = "idProducto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//private List<RelProductoPedido> listaProductos;

}
