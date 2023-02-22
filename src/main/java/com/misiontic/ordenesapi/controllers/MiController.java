package com.misiontic.ordenesapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiController {
	
	@GetMapping(value="metodo-saludar")
	public String saludar() {
		return "Hola como estas en este dia.";
	}

}
