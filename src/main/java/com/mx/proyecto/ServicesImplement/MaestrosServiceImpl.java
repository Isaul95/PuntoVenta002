package com.mx.proyecto.ServicesImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.MaestrosRepository;
import com.mx.proyecto.Services.MaestrosService;


@Service
public class MaestrosServiceImpl implements MaestrosService{
	
	@Autowired
	private MaestrosRepository maestrosRepository;
	
	@Override
	public ResponseDto getMaestros() {
		ResponseDto response = new ResponseDto();
		try {
			List<Maestros> lista = maestrosRepository.getMaestros(); 
			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla Maestros");
				response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar de la tabla Maestros");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Maestros");
		} catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseDto insertMaestros(Maestros nuevoMaestro) {
		ResponseDto response = new ResponseDto();
		StringBuilder mensajeError = new StringBuilder();
		Integer respuesta = 0;
		Integer cantidadMaestros = 0;
		try {
			// validamos que campo nombreMaestro no sea nullo o vacio
			if(nuevoMaestro.getNombreMaestro() == null  || nuevoMaestro.getNombreMaestro().equals("")) {
				mensajeError.append("El campo nombreMaestro no puede ser NULL o Vacio. ");
			}
			else {
				// Verificamos si ya existe un registro con el mismo nombre
				cantidadMaestros = maestrosRepository.existeMaestroNombre(nuevoMaestro);
	            if (cantidadMaestros > 0) {
	                mensajeError.append("!!NO SE PUEDEN DUPLICAR REGISTROS!! Ya existe el Maestro: ["+nuevoMaestro.getNombreMaestro()+"]. ");
	            }
			}

			// validamos que campo sea valido o igual a un mes
			if (!(nuevoMaestro.getEdad() >= 0 && nuevoMaestro.getEdad() <= 100)) {
				mensajeError.append("El campo Edad no puede contener un valor mayor a 3 cifras. ");
			}
			
			// validamos que campo fechaInscripcion no sea nullo o vacio
			if (nuevoMaestro.getFechaNacimiento() == null || nuevoMaestro.getFechaNacimiento().equals("")) {
				mensajeError.append("El campo FechaNacimiento no puede ser NULL o Vacio. ");
			}
			
			if (nuevoMaestro.getNumeroCursos() == null || nuevoMaestro.getNumeroCursos().equals("")) {
				mensajeError.append("El campo NumeroCursos no puede ser NULL o Vacio. ");
			}


			// Verificar si hay errores en las validaciones
			if (mensajeError.length() > 0) {
				// Si hay errores, establecer el mensaje de error en el response
				response.setMessage(mensajeError.toString());
			}else {
				respuesta = maestrosRepository.insertMaestros(nuevoMaestro);
			}
			

			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se inserto correctamente");
			}else {
				response.setCode(-1);
			}
				
		}
		catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
        }
		catch(Exception e) {
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: "+e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto updateMaestros(Maestros maestro) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;

		try {
			respuesta = maestrosRepository.updateMaestros(maestro);
			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se actualizo correctamente");
			} else {
				response.setCode(-1);
				response.setMessage("Error: No se actualizo correctamente");
			}

		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Aspirantes");
		} catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseDto deleteMaestros(Maestros maestro) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;

		try {
			//respuesta = maestrosRepository.deleteMaestros(maestro);
			respuesta = maestrosRepository.inactivaMaestro(maestro);

			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se elimino correctamente " + respuesta + " registro (s)");
			} else {
				response.setCode(-1);
				response.setMessage("Error: No se elimino correctamente");
			}

		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Aspirantes");
		} catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto insertMaestrosMasivo(Maestros[] maestros) {
		ResponseDto response = new ResponseDto();
		List<Maestros> maestrosList = new ArrayList();

		for (Maestros maestro : maestros) {
			maestrosList.add(maestro);
		}
		
		maestrosRepository.insertMaestrosMasivo(maestrosList);

		response.setMessage("Se insertaron correctamente los " + maestrosList.size() + " registros");
		return response;
	}
}