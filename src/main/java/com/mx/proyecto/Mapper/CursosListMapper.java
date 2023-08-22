package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Cursos;

public class CursosListMapper<T> implements RowMapper<Cursos> {

	
	public Cursos mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cursos objeto = new Cursos();
		objeto.setCursoId(rs.getInt("CURSOID"));
		objeto.setNombreCurso(rs.getString("NOMBRE_CURSO"));
		objeto.setDuracionMeses(rs.getInt("DURACION_MESES"));
		objeto.setFechaInicio(rs.getDate("FECHA_INICIO"));
		objeto.setCantidadAlumnos(rs.getInt("CANTIDAD_ALUMNOS"));
		
		return objeto;
	}
	

}
