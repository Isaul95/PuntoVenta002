package com.mx.proyecto.RepositoryImplement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Mapper.MaestrosListMapper;
import com.mx.proyecto.Repository.MaestrosRepository;

@Repository
public class MaestrosRepositoryImpl implements MaestrosRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Maestros> getMaestros() {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "SELECT * FROM MAESTROS WHERE ACTIVO = 1";
		return jdbcTemplate.query(sqlQuery, new MaestrosListMapper<Maestros>());
	}

	@Override
	public Integer insertMaestros(Maestros nuevoMaestro) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "INSERT INTO MAESTROS (NOMBRE_MAESTRO, EDAD, FECHA_NACIMIENTO, NUMERO_CURSOS)"
				+ "VALUES (?,?,?,?)";
		return jdbcTemplate.update(sqlQuery, nuevoMaestro.getNombreMaestro(), nuevoMaestro.getEdad(), nuevoMaestro.getFechaNacimiento(),
				nuevoMaestro.getNumeroCursos());
	}

	@Override
	public Integer updateMaestros(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "UPDATE MAESTROS SET NOMBRE_MAESTRO = ?, EDAD = ?, FECHA_NACIMIENTO = ?, NUMERO_CURSOS = ? WHERE MAESTROID = ?";
		return jdbcTemplate.update(sqlQuery, new Object[] {  maestro.getNombreMaestro(), maestro.getEdad(), maestro.getFechaNacimiento(),
				maestro.getNumeroCursos(), maestro.getMaestroId() });
	}

	@Override
	public Integer deleteMaestros(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("DELETE FROM MAESTROS WHERE MAESTROID = ? ", maestro.getMaestroId());
	}
	
	@Override
	public Integer inactivaMaestro(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		 return jdbcTemplate.update("UPDATE MAESTROS SET ACTIVO = 0 WHERE MAESTROID = ?", maestro.getMaestroId());
	}

	@Override
	public int[][] insertMaestrosMasivo(List<Maestros> maestros) {
		jdbcTemplate.setDataSource(getDataSource());

		int[][] updateCounts = null;
		try {
			updateCounts = jdbcTemplate.batchUpdate(
					"INSERT INTO MAESTROS (NOMBRE_MAESTRO, EDAD,FECHA_NACIMIENTO,NUMERO_CURSOS) VALUES (?,?,?,?)",
					maestros, 100, new ParameterizedPreparedStatementSetter<Maestros>() {
						@Override
						public void setValues(PreparedStatement ps, Maestros argument) throws SQLException {
							ps.setString(1, argument.getNombreMaestro());
							ps.setInt(2, argument.getEdad());
							ps.setDate(3, new java.sql.Date(argument.getFechaNacimiento().getTime()));
							//ps.setDate(3, argument.getFechaNacimiento());
							ps.setInt(4, argument.getNumeroCursos());
						}
					});
		} catch (org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXECEPTION POR DATO DUPLICADO: " + DKE.getMessage());
		}
		return updateCounts;
	}
	
	@Override
	public Integer existeMaestroNombre(Maestros maestro) {
		Integer count = 0;
		jdbcTemplate.setDataSource(getDataSource());
	    String sqlQuery = "SELECT COUNT(*) FROM MAESTROS WHERE NOMBRE_MAESTRO = ?";
		return count = jdbcTemplate.queryForObject(sqlQuery, Integer.class, maestro.getNombreMaestro());
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