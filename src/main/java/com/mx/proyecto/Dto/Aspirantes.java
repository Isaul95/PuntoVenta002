package com.mx.proyecto.Dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Aspirantes {
	
	private BigDecimal idAlumno; 
	private String nombreAlumno; 
	private Integer edad;
	private Date fechaInscripcion;
	private BigDecimal cursoId; 
	private BigDecimal maestroId;
	
	 // Otras propiedades de Aspirantes
    private Cursos curso;
    private Maestros maestro;
	
	public BigDecimal getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(BigDecimal idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public BigDecimal getCursoId() {
		return cursoId;
	}
	public void setCursoId(BigDecimal cursoId) {
		this.cursoId = cursoId;
	}
	public BigDecimal getMaestroId() {
		return maestroId;
	}
	public void setMaestroId(BigDecimal maestroId) {
		this.maestroId = maestroId;
	}
	
	public Cursos getCurso() {
		return curso;
	}
	public void setCurso(Cursos curso) {
		this.curso = curso;
	}
	public Maestros getMaestro() {
		return maestro;
	}
	public void setMaestro(Maestros maestro) {
		this.maestro = maestro;
	}
	
	
	
}
