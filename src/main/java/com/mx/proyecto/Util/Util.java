package com.mx.proyecto.Util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mx.proyecto.Dto.Cursos;

public class Util {
	
	//Metodo para leer archivo txt
	public List<Cursos> leerArchivoCurso(){
		List<Cursos> cursos = new ArrayList<>();
		String directorioArchivo = "C:\\Users\\LEO\\eclipse-workspace\\PuntoVentaV2\\CURSOS.txt";
		BufferedReader buffer = null;
		String linea = "";
		int lineas = 0;
		Cursos curso = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			// Obtencion de archivo
			FileInputStream fileInput = new FileInputStream(directorioArchivo);
			// Apertura de archivo
			DataInputStream dataInput = new DataInputStream(fileInput);
			buffer = new BufferedReader(new InputStreamReader(dataInput));
			
			// Bucle de lectura de archivo data stage
			while ( (linea = buffer.readLine()) != null) {
				curso = new Cursos();
				
				System.out.println(linea);
				String separador = Pattern.quote("|");
				String[] parts = linea.split(separador);
				lineas++;
				
				LocalDate fecha = LocalDate.parse(parts[2], formatter);
				
				curso.setNombreCurso(parts[0]);
				curso.setDuracionMeses(Integer.parseInt(parts[1]));
				curso.setFechaInicio(java.sql.Date.valueOf(fecha));
				curso.setCantidadAlumnos(Integer.parseInt(parts[3]));
				
				cursos.add(curso);
			}
			// Cierre de archivo data stage
			dataInput.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo especificado: " + e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("No se encontro el archivo especificado: " + e.getMessage());
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		return cursos;
	}

}