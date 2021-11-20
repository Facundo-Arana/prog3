package ejercicioEntregable.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ejercicioEntregable.modelo.Libro;

public class CSVReaderLibros {

	private String path;

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<Libro> read() {
//		int c = 0;
		ArrayList<String[]> lines = this.readContent();
		ArrayList<Libro> libros = new ArrayList<Libro>();
		for (String[] line : lines) {
			Integer idLibro = Integer.parseInt(line[0].trim());
			String titulo = line[1].trim();
			String autor = line[2].trim();
			String genero = line[3].trim();
			Integer paginas = Integer.parseInt(line[4].trim());
			Integer puntaje = Integer.parseInt(line[5].trim());
			Integer cantidadEjemplares = Integer.parseInt(line[6].trim());
//			c+= cantidadEjemplares;
			Libro l = new Libro(idLibro, titulo, autor, genero, paginas, puntaje, cantidadEjemplares);
			libros.add(l);
		}
//		System.out.println(c);
		return libros;
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
