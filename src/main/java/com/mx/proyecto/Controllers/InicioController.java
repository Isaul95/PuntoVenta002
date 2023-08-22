package com.mx.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Services.InicioService;

// ESTE CONTROLADOR Y OTROS MÁS VAN A ADMINISTRAR LAS PETICIONES QUE EL USUARIO HAGA A LA APLICACIÓN

// Anotacion que sirve para indicar que esta ligado a Spring y va a funcionar.
	//GET = RETORNAR INFORMACION (TAMBIEN PUEDE RECIBIR INFORMACION)
	//POST = ENVIAR Y RETORNAR INFORMACION
	//PUT = ENVIAR Y RETORNAR INFORMACION , ACTUALIZAR REGISTROS.
	//PUT = ENVIAR Y RETORNAR INFORMACION , DELETE REGISTROS.

	//PATH = "SUENA A RUTA"
	//OPTIONS = SIRVE PARA ENVIAR UNA LISTA DE OPCIONES
@Controller
@RequestMapping("MyInicio")
public class InicioController {

	@Autowired
	private InicioService inicioService;

	@RequestMapping("/irAInicio")
	public String irAInicio() {

		return "Inicio";
	}

	@RequestMapping("getAlumnos")
	public String getAlumnos() {

		inicioService.saludo();

		return "";
	}
	
	
}