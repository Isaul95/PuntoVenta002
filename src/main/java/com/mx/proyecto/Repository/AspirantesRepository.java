package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Aspirantes;

public interface AspirantesRepository {
	List<Aspirantes>getAspirantes();
	Integer insertAspirantes(Aspirantes nuevoAspirante);
	Integer updateAspirantes(Aspirantes aspirante);
	Integer deleteAspirantes(Aspirantes aspirante);
	int[][]  insertAspirantesMasivo(List<Aspirantes> aspirantes);
	List<Integer> getValidCursoIds(Aspirantes aspirante);
	List<Integer> getValidMaestroIds(Aspirantes aspirante);
	boolean cursoExists(Aspirantes aspirante);
}
