package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Repository
public class UsuariosAdminDAOImpl2 extends GenericDAO<UsuariosAdmin, Long> implements UsuariosAdminDAO2{

//	@Autowired
//	private DataSource dataSource;
//	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
//
//	public DataSource getDataSource() {
//		return dataSource;
//	}
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	
	
	
	// la consulta --> select * from tabla
		@Override
		@Transactional
		public List<UsuariosAdmin> obtieneUsuarios() {
			
			final Session session = sessionFactory.getCurrentSession();
			final Criteria criteria = session.createCriteria(UsuariosAdmin.class);// --> select * from tabla
			
			return (List<UsuariosAdmin>) criteria.list(); // --> retorna una lista de usuarios
		}

		
		
		// select name_seq.nexval from dual;
		@Override
		@Transactional
		public Long obtenerValorSecuenciaTabla() {
			String sqlSequence = "SELECT SEQ_USUARIOS_ADMIN.NEXTVAL AS SECUENCIAUSER FROM DUAL";
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sqlSequence);
			List result = query.list();
			return ((BigDecimal) result.get(0)).longValue();
		}
	
		
		
		
		
		
		
		
		
		
} // fin de la clase
