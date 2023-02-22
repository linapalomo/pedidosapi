package com.misiontic.ordenesapi.entity;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario", nullable = false)
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idperfil", nullable = false, updatable = true, referencedColumnName = "idperfil")
	private Perfil idPerfil;

	@Column(name = "nombres", length = 200, nullable = false)
	private String nombres;

	@Column(name = "apellidos", length = 200, nullable = false)
	private String apellidos;

	@Column(name = "nombreusuario", length = 100, nullable = true)
	private String nombreUsuario;

	@Column(name = "clave", length = 100, nullable = true)
	private String clave;
	
	//@OneToMany(mappedBy = "idCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//private List<Pedido> listadoPedidos;

}
