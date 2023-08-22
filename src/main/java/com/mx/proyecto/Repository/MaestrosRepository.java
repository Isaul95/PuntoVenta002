package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Dto.Maestros;

public interface MaestrosRepository {
	List<Maestros> getMaestros();
	Integer insertMaestros(Maestros nuevoMaestro);
	Integer updateMaestros(Maestros maestro);
	Integer inactivaMaestro(Maestros maestro);
	Integer deleteMaestros(Maestros maestro);
	Integer existeMaestroNombre(Maestros maestro);
	int[][]  insertMaestrosMasivo(List<Maestros> maestros);
}