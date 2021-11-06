package ejercicioEntregable;

import java.util.ArrayList;

import ejercicioEntregable.reader.CSVReaderLibros;

public class Biblioteca {

	private ArrayList<Libro> libros;
	private CSVReaderLibros libroReader;

	public Biblioteca(String path) {
		this.libroReader = new CSVReaderLibros();
		libroReader.setPath(path);
		this.libros = libroReader.read();
	}

	public ArrayList<Libro> getLibros() {
		ArrayList<Libro> copy = new ArrayList<>();
		for (Libro l : libros) {
			copy.add(l);
		}
		return copy;
	}

}
