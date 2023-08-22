package com.mx.proyecto.ServicesImplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.Services.UsuariosAdminService2;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Service
public class UsuariosAdminServiceImpl2 implements UsuariosAdminService2{
	
	@Autowired
	private UsuariosAdminDAO2 usuariosAdminDAO2;

	
	// Regla 01: Necesito un lista de usuarios
	// Regla 02: Necesito que cuando no existan usuarios el sistema me arroje un mensaje -> "No existen registros"
	@Override
	public ResponseDto getUsuarios2() {
		ResponseDto response = new ResponseDto();
		try {
			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtieneUsuarios();
			
//				if(listaUsuarios != null){
			    if(listaUsuarios.isEmpty()){
					response.setMessage("No existen registros");
					response.setCode(100); // 200 -> Ok
				}else {
					response.setCode(200); // 200 -> Ok
					response.setMessage("Lista de usuarios");
					response.setList(listaUsuarios);
				}
			
		}catch (Exception e) {
			response.setCode(500); // 200 -> Ok
			response.setMessage("Ocurrio un error en el metodo getUsuarios2 en la clase x");
		}
				
		return response;
	}


	
	/*
 	 1.- Todos los datos deben ser obligatorios
 	 2.- CAMPOS OBLIGATORIOS: -> NombreCompleto, edad, direccion
 	 rfc - validar
 	 curp - validar
 	 
 	 nss - validar -> 
 	 1.-numerico/que no recibas texto
 	 2.- limimte de 8 digitos cuenta digitos son #
 	 3.-  identifaca si es menor de 8 -> 12345
 	 agregues ceros a la izq-> 00012345
	*/
	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto response = new ResponseDto();
		// null
		// ""
		try {
			// OPERADORES
			// && => AND (y) la condicion es se cumple uno y l aotra
			// || => OR (o) la condicion se cumple uno o la otra
			
			if(nuevoUsuario != null) { // Val general si es diferente a null -> (que no vienen vacio)
				if(nuevoUsuario.getNombreCompleto() != null && !nuevoUsuario.getNombreCompleto().isEmpty() &&
						nuevoUsuario.getEdad() != 0 &&
						nuevoUsuario.getDireccion() != null && !nuevoUsuario.getDireccion().isEmpty()) {
					
					UsuariosAdmin datos = new UsuariosAdmin(); 
//					datos.setIdUser(nuevoUsuario.getIdUser()); // Esta forma es manual ingresando el ide
					datos.setIdUser(usuariosAdminDAO2.obtenerValorSecuenciaTabla());// conusltar el ide autoincrementable
					datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
					datos.setDireccion(nuevoUsuario.getDireccion());
					datos.setEdad(nuevoUsuario.getEdad());
					datos.setEstado(nuevoUsuario.getEstado());
					
					usuariosAdminDAO2.create(datos); // Desde aqui se hace el insert
					response.setCode(200);
					response.setMessage("Los datos se guardaron correctamente");
					
				}else {
					response.setCode(300);
					response.setMessage("Los datos obligatorios vienen vacios - (Nombre completo, edad y dirección)");
				}
			}else {
				response.setCode(400);
				response.setMessage("Los datos vienen vacios");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en la clase x en el metodo guardar....");
//			response.setMessage("Ocurrio un error en el servidor.!");
		}
		
		return response;
	}


	
// validar que el id no venga vacio
// validar que el id sea numerico QUE NO SEA TEXTO
	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		try {
			if(idUser.getIdUser() != 0) {
				usuariosAdminDAO2.delete(idUser.getIdUser());
				response.setCode(200);
				response.setMessage("El registro se elimino correctamente");
			}else {
				response.setCode(400);
				response.setMessage("El PK viene con cero");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en la clase x en el metodo eliminar....");
		}
		return response;
	}

	
	

	@Override
	public ResponseDto actualizarUsuario(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();
		try {
			
			UsuariosAdmin datos2 = new UsuariosAdmin(); 
			datos2.setIdUser(datos.getIdUser());
			datos2.setNombreCompleto(datos.getNombreCompleto());
			datos2.setDireccion(datos.getDireccion());
			datos2.setEdad(datos.getEdad());
			datos2.setEstado(datos.getEstado());
			
			usuariosAdminDAO2.update(datos2);
			response.setCode(200);
			response.setMessage("El registro se actualizo correctamente");
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en la clase x en el metodo update....");
		}
		
		return response;
	}



	@Override
	public ResponseDto getUsuariosAdminPorId(UsuariosAdminDTO dato) { 
		ResponseDto response = new ResponseDto();
		try {
			
			// select * from tabla where id = ? and rol = ? and edad = ?
//			UsuariosAdmin elDatos = usuariosAdminDAO2.obtenerPorIdEdad(dato.getIdUser());
			
			UsuariosAdmin elDatos = usuariosAdminDAO2.read(dato.getIdUser()); // select * from tabla where id = ?
			
			if(elDatos != null) { // != null -> si no viene nulo es decir que existe info 
				response.setCode(200);
				response.setMessage("Usuario encontrado");
				response.setContent(elDatos);
			}else {
				response.setCode(100);
				response.setMessage("Usuario no encontrado");
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en la clase x en el metodo obtenerUsuarios por id....");
		}
		
		return response;
	}

		
	
//	CRUD => 
	
	
	
	

} // fin de la clase
