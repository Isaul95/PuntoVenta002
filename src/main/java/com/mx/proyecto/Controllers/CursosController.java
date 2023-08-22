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
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.CursosService;

@Controller
@RequestMapping("curso")
public class CursosController {
	@Autowired
	private CursosService cursosService;
	
	//CONSULTA CURSOS - GET 
	@ResponseBody
	@RequestMapping(value="/getCursos", method = RequestMethod.GET, produces = "application/json") //
	ResponseEntity < ResponseDto > getCursos()
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = cursosService.getCursos();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	// INSERTA CURSOS - POST
	@ResponseBody
	@RequestMapping(value="/insertCursos", method = RequestMethod.POST, produces = "application/json") //
	ResponseEntity < ResponseDto > insertCursos(@RequestBody Cursos nuevoCurso)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = cursosService.insertCursos(nuevoCurso);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	// ACTUALIZA CURSOS - PUT
	@ResponseBody 
	@RequestMapping(value="/updateCursos", method = RequestMethod.PUT, produces = "application/json") //
	ResponseEntity < ResponseDto > updateAspirantes(@RequestBody Cursos curso)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		ResponseDto response = new ResponseDto();
		response = cursosService.updateCursos(curso);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	// ELIMINA CURSOS - DELETE
	@ResponseBody 
	@RequestMapping(value="/deleteCursos", method = RequestMethod.DELETE, produces = "application/json") //
	ResponseEntity < ResponseDto > deleteCursos(@RequestBody Cursos curso)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = cursosService.deleteCursos(curso);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	// INSERCION MASIVA DE CURSOS - POST
	@ResponseBody
	@RequestMapping(value="/insertCursosMasivo", method = RequestMethod.POST, produces = "application/json") //
	ResponseEntity < ResponseDto > insertCursosMasivo(@RequestBody Cursos[] cursos)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = cursosService.insertCursosMasivo(cursos);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}

	// INSERCION MASIVA DE CURSOS - CON ARCHIVO
	@ResponseBody
	@RequestMapping(value = "/insertCursosMasivoByFile", method = RequestMethod.GET, produces = "application/json") //
	ResponseEntity<ResponseDto> insertCursosMasivoByFile() {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = new ResponseDto();
		response = cursosService.insertCursosMasivoByFile();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
}