package com.mx.proyecto.ServicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Repository.InicioRepository;
import com.mx.proyecto.Services.InicioService;

// VAMOS A MANEJAR TODA LA LOGICA DEL NEGOCIO QUE VENGA DEL CONTROLADOR HACIA LA BD
// O QUE VAYA DE LA BD HACIA EL CONTROLADOR
@Service
public class InicioServiceImpl implements InicioService{
	
	@Autowired
	private InicioRepository inicioRepository;

	@Override
	public String saludo() {
		// TODO Auto-generated method stub
		inicioRepository.saludoAmistoso();
		
		return null;
	}

}
