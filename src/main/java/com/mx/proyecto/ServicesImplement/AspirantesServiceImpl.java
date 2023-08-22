package com.mx.proyecto.ServicesImplement;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.AspirantesRepository;
import com.mx.proyecto.Services.AspirantesService;

@Service
public class AspirantesServiceImpl implements AspirantesService {

	@Autowired
	private AspirantesRepository aspirantesRepository;
	
	@Override
	public ResponseDto getAspirantes() {
		
		ResponseDto response = new ResponseDto();
		
		try {
			List<Aspirantes> lista = aspirantesRepository.getAspirantes();
			
			//System.out.println("Lista "+lista.get(1).getMaestro());
			if(lista != null && lista.size()>0) {
				response.setCode(0);
				response.setMessage("Hay "+lista.size()+" registros que mostrar de la tabla Aspirantes");
				response.setContent(lista);
			}
			else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar de la tabla Aspirantes");
			}
		}
		catch(NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Aspirantes");
		}
		catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista está vacía o el índice no existe.");
        }
		catch(Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: "+e.getMessage());
		}
		
		return response;
	}
	
	@Override
	public ResponseDto insertAspirantes(Aspirantes nuevoAspirante) {
		ResponseDto response = new ResponseDto();
		StringBuilder mensajeError = new StringBuilder();
		Integer respuesta = 0;
		
		try {
			
			// validamos que campo nombrealumno no sea nullo o vacio
			if(nuevoAspirante.getNombreAlumno() == null  || nuevoAspirante.getNombreAlumno().equals("")) {
				mensajeError.append("El campo NombreAlumno no puede ser NULL o Vacio. ");
			}

			// validamos que campo edad sea mayor a 3 digitos
			if (!(nuevoAspirante.getEdad() >= 0 && nuevoAspirante.getEdad() <= 999)) {
				mensajeError.append("El campo Edad no puede contener un valor mayor a 3 cifras. ");
			}
			
			// validamos que campo fechaInscripcion no sea nullo o vacio
			if (nuevoAspirante.getFechaInscripcion() == null || nuevoAspirante.getFechaInscripcion().equals("")) {
				mensajeError.append("El campo fechaInscripcion no puede ser NULL o Vacio. ");
			}
			
			//validar cursoID opcion1
//			boolean cursoIDExists = aspirantesRepository.cursoExists(nuevoAspirante);
//			if (cursoIDExists == false) {
//	            // Manejar el caso en que el curso no existe
//				mensajeError.append("El cursoID no existe en la base datos");
//	        }
			
			//validar cursoID opcion2
			List<Integer> cursoIdList =  aspirantesRepository.getValidCursoIds(nuevoAspirante);
			Integer cursoIdInteger = nuevoAspirante.getCursoId().intValue(); // Convertir a Integer

			boolean cursoIdValido = false;
		    for (Integer cursoId : cursoIdList) {
		    	if (cursoId.equals(cursoIdInteger)) {
		            cursoIdValido = true;
		            break; // No es necesario seguir recorriendo si ya encontraste una coincidencia
		        }
		    }
		    if (!cursoIdValido) {
		    	mensajeError.append("(Error -5) El cursoId proporcionado no es válido. cursoId's disponibles: " + cursoIdList.toString()+". ");
		    }
		    
		  //validar maestroID
			List<Integer> maestroIdList =  aspirantesRepository.getValidMaestroIds(nuevoAspirante);
			Integer maestroIdInteger = nuevoAspirante.getMaestroId().intValue(); // Convertir a Integer

			boolean maestroIdValido = false;
		    for (Integer maestroId : maestroIdList) {
		    	if (maestroId.equals(maestroIdInteger)) {
		    		maestroIdValido = true;
		            break; // No es necesario seguir recorriendo si ya encontraste una coincidencia
		        }
		    }
		    if (!maestroIdValido) {
		    	mensajeError.append("(Error -6) El maestroId proporcionado no es válido. maestroId's disponibles: " + maestroIdList.toString() +". ");
		    }
			

			// Verificar si hay errores en las validaciones
			if (mensajeError.length() > 0) {
				// Si hay errores, establecer el mensaje de error en el response
				response.setMessage(mensajeError.toString());
			}else {
				respuesta =  aspirantesRepository.insertAspirantes(nuevoAspirante);
			}
			

			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se inserto correctamente");
			}else {
				response.setCode(-1);
			}
				
		}
		catch(NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Aspirantes");
		}
		catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista está vacía o el índice no existe.");
        }
		catch(Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: "+e.getMessage());
		}
		
		return response;
	}

	@Override
	public ResponseDto updateAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;

		try {
			respuesta = aspirantesRepository.updateAspirantes(aspirante);

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
			response.setMessage("La lista está vacía o el índice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseDto deleteAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;

		try {
			respuesta = aspirantesRepository.deleteAspirantes(aspirante);

			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se elimino correctamente "+respuesta + " registro (s)");
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
			response.setMessage("La lista está vacía o el índice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseDto insertAspirantesMasivo(Aspirantes[] aspirantes) {
		ResponseDto response = new ResponseDto();
		List<Aspirantes> aspirantesList = new ArrayList();
		for(Aspirantes aspirante : aspirantes) {
			aspirantesList.add(aspirante); 
		}
		aspirantesRepository.insertAspirantesMasivo(aspirantesList);
		response.setMessage("Se insertaron correctamente los "+aspirantesList.size()+" registros");
		return response;
	}

}
