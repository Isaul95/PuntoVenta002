package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;

public interface CursosService {
	ResponseDto getCursos(); 
	ResponseDto insertCursos(Cursos nuevoCurso);
	ResponseDto updateCursos(Cursos curso);
	ResponseDto deleteCursos(Cursos curso);
	ResponseDto insertCursosMasivo(Cursos[] cursos);
	ResponseDto insertCursosMasivoByFile();
}
