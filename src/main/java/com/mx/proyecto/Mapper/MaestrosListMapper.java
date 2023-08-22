package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.mx.proyecto.Dto.Maestros;

public class MaestrosListMapper<T> implements RowMapper<Maestros> {

	public Maestros mapRow(ResultSet rs, int rowNum) throws SQLException {
		Maestros objeto = new Maestros();
		
		objeto.setMaestroId(rs.getInt("MAESTROID"));
		objeto.setNombreMaestro(rs.getString("NOMBRE_MAESTRO"));
		objeto.setEdad(rs.getInt("EDAD"));
		objeto.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
		objeto.setNumeroCursos(rs.getInt("NUMERO_CURSOS"));
		
		return objeto; 
	}

}
