package ejercicioEntregable.reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ejercicioEntregable.Alumno;

public class CSVReaderAlumnos {

	private String path;
	
	public CSVReaderAlumnos() {
	}
	
	public void setPath(String path) {
		this.path = path;
		
	}
	
	public ArrayList<Alumno> read() {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent();
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String idAlumno = line[0].trim();
			Integer puntaje = Integer.parseInt(line[1].trim());
			alumnos.add(new Alumno(idAlumno,puntaje));
		}
		return alumnos;
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine(); // Salteo la primera linea
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}

}
