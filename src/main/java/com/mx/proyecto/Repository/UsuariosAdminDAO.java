package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.entidad.UsuariosAdmin;

public interface UsuariosAdminDAO {
	
	
	List<UsuariosAdmin> obtenerTodosLosDatos(); // declarar metodo

	Integer insertarDatosHibernate(UsuariosAdmin datos);

	Integer eliminarUser(UsuariosAdmin datoEliminar);

	Integer actualizaInfo(UsuariosAdmin datos);
	

}
