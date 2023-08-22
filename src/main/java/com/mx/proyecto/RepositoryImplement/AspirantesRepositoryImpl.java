package com.mx.proyecto.RepositoryImplement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Mapper.AspirantesListMapper;
import com.mx.proyecto.Repository.AspirantesRepository;

@Repository
public class AspirantesRepositoryImpl implements AspirantesRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Aspirantes> getAspirantes() {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "SELECT ASP.IDALUMNO, ASP.NOMBREALUMNO, ASP.EDAD, ASP.FECHAINSCRIPCION, C.NOMBRE_CURSO, MT.NOMBRE_MAESTRO " +
                "FROM ASPIRANTES ASP " + 
				"JOIN CURSOS C ON ASP.CURSOID = C.CURSOID " + 
                "JOIN MAESTROS MT ON ASP.MAESTROID = MT.MAESTROID";
		
//		return jdbcTemplate.query(sqlQuery, new AspirantesListMapper());
//		System.out.println(sqlQuery);
		return jdbcTemplate.query(sqlQuery, new AspirantesListMapper());
	}
	
	@Override
	public Integer insertAspirantes(Aspirantes nuevoAspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("INSERT INTO ASPIRANTES (nombreAlumno, edad, fechaInscripcion, cursoId, maestroId) "
		+ "VALUES (?,?,?,?,?)",nuevoAspirante.getNombreAlumno() ,nuevoAspirante.getEdad(),
		nuevoAspirante.getFechaInscripcion(),nuevoAspirante.getCursoId(), nuevoAspirante.getMaestroId());
	}
	
	@Override
	public Integer updateAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE ASPIRANTES SET NOMBREALUMNO = ?, EDAD = ?, FECHAINSCRIPCION = ?  WHERE IDALUMNO = ?",
				new Object[] {aspirante.getNombreAlumno(), aspirante.getEdad(), aspirante.getFechaInscripcion(), aspirante.getIdAlumno()});
	}
	
	@Override
	public Integer deleteAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("DELETE FROM ASPIRANTES WHERE IDALUMNO = ? ", aspirante.getIdAlumno());
	}
	

	@Override
	public int[][]  insertAspirantesMasivo(List<Aspirantes> aspirantes){
		jdbcTemplate.setDataSource(getDataSource());
	
		int[][] updateCounts=null;
		try {
		        updateCounts = jdbcTemplate.batchUpdate(
		        	    "INSERT INTO ASPIRANTES (NOMBREALUMNO, EDAD,FECHAINSCRIPCION,CURSOID,MAESTROID) VALUES (?,?,SYSDATE,?,?)",
		        	    aspirantes,
		        	    100,
		                new ParameterizedPreparedStatementSetter<Aspirantes>() {
							@Override
							public void setValues(PreparedStatement ps, Aspirantes argument) throws SQLException {
								  ps.setString(1,argument.getNombreAlumno());
				                  ps.setInt(2, argument.getEdad()); 
				                  ps.setBigDecimal(3, argument.getCursoId()); 
				                  ps.setBigDecimal(4, argument.getMaestroId()); 
							}
		                }
		                );
			}catch(org.springframework.dao.DuplicateKeyException DKE) {
				System.out.println("EXECEPTION POR DATO DUPLICADO: "+DKE.getMessage()); 
			}
			  return updateCounts;
	}
	
	@Override
	public boolean cursoExists(Aspirantes aspirante) {
		 jdbcTemplate.setDataSource(getDataSource());

	        Integer count = jdbcTemplate.queryForObject(
	            "SELECT COUNT(*) FROM CURSOS WHERE CURSOID = ?",
	            Integer.class, aspirante.getCursoId()
	        );
	        return count != null && count > 0;
	}
	
	@Override
	public List<Integer> getValidCursoIds(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());

		List<Integer> validCursoIds = jdbcTemplate.queryForList("SELECT CURSOID FROM CURSOS", Integer.class);
		return validCursoIds;
	}
	
	@Override
	public List<Integer> getValidMaestroIds(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());

		List<Integer> validMaestroIds = jdbcTemplate.queryForList("SELECT MAESTROID FROM MAESTROS", Integer.class);
		return validMaestroIds;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}