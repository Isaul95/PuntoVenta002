package com.mx.proyecto.Dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Maestros {
	
	private Integer maestroId; 
	private String nombreMaestro; 
	private Integer edad;
	private Date fechaNacimiento; 
	private Integer numeroCursos;
	
	
	public Integer getMaestroId() {
		return maestroId;
	}
	public void setMaestroId(Integer maestroId) {
		this.maestroId = maestroId;
	}
	public String getNombreMaestro() {
		return nombreMaestro;
	}
	public void setNombreMaestro(String nombreMaestro) {
		this.nombreMaestro = nombreMaestro;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getNumeroCursos() {
		return numeroCursos;
	}
	public void setNumeroCursos(Integer numeroCursos) {
		this.numeroCursos = numeroCursos;
	}
	
	
	
}
