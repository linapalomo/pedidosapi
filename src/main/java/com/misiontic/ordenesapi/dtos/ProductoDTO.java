package com.misiontic.ordenesapi.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ProductoDTO {

	private Long idProducto;
	private String nombre;
	private String referencia;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private Integer cantidad;
	private Double precioCosto;
	private Double precioVenta;

}
