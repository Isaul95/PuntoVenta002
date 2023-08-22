package com.mx.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.MaestrosService;

@Controller
@RequestMapping("maestro")
public class MaestrosController {
	@Autowired
	private MaestrosService maestrosService;
	
	// CONSULTA MAESTROS - GET
	@ResponseBody
	@RequestMapping(value = "/getMaestros", method = RequestMethod.GET, produces = "application/json") //
	ResponseEntity<ResponseDto> getMaestros() {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = new ResponseDto();
		response = maestrosService.getMaestros();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
	
	// INSERTA MAESTROS - POST
	@ResponseBody
	@RequestMapping(value = "/insertMaestros", method = RequestMethod.POST, produces = "application/json") //
	ResponseEntity<ResponseDto> insertMaestros(@RequestBody Maestros nuevoMaestro) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = new ResponseDto();
		response = maestrosService.insertMaestros(nuevoMaestro);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
	
	// ACTUALIZA MAESTROS - PUT
	@ResponseBody
	@RequestMapping(value = "/updateMaestros", method = RequestMethod.PUT, produces = "application/json") //
	ResponseEntity<ResponseDto> updateMaestros(@RequestBody Maestros maestro) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		ResponseDto response = new ResponseDto();
		response = maestrosService.updateMaestros(maestro);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// ELIMINA MAESTROS - DELETE
	@ResponseBody
	@RequestMapping(value = "/deleteMaestros", method = RequestMethod.DELETE, produces = "application/json") //
	ResponseEntity<ResponseDto> deleteMaestros(@RequestBody Maestros maestro) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		ResponseDto response = new ResponseDto();
		response = maestrosService.deleteMaestros(maestro);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// INSERCION MASIVA DE MAESTROS - POST
	@ResponseBody
	@RequestMapping(value = "/insertMaestrosMasivo", method = RequestMethod.POST, produces = "application/json") //
	ResponseEntity<ResponseDto> insertMaestrosMasivo(@RequestBody Maestros[] maestros) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		ResponseDto response = new ResponseDto();
		response = maestrosService.insertMaestrosMasivo(maestros);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
}