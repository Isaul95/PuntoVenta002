package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.Maestros;

public class AspirantesListMapper implements RowMapper<Aspirantes> {
	
	public Aspirantes mapRow (ResultSet rs, int rowNum) throws SQLException {
		Aspirantes aspirante = new Aspirantes(); 
		
		aspirante.setIdAlumno(rs.getBigDecimal("IDALUMNO")); 
		aspirante.setNombreAlumno(rs.getString("NOMBREALUMNO")); 
		aspirante.setEdad(rs.getInt ("EDAD")); 
		aspirante.setFechaInscripcion (rs.getDate("FECHAINSCRIPCION"));
		
		// Mapeo de las columnas de la tabla CURSOS
        Cursos curso = new Cursos();
        curso.setNombreCurso(rs.getString("NOMBRE_CURSO"));
        
        // Mapeo de las columnas de la tabla MAESTROS
        Maestros maestro = new Maestros();
        maestro.setNombreMaestro(rs.getString("NOMBRE_MAESTRO"));
        
        // Establecer las relaciones entre las entidades
//        aspirante.setCursoId(rs.getBigDecimal("CURSOID"));
//        aspirante.setMaestroId(rs.getBigDecimal("MAESTROID"));
        
     // Establecer las relaciones entre las entidades
        aspirante.setCurso(curso);
        aspirante.setMaestro(maestro);

		return aspirante;
	}
}
