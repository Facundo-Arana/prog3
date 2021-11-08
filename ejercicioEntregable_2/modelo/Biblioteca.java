package ejercicioEntregable.modelo;

import ejercicioEntregable.reader.CSVReaderLibros;
import java.util.ArrayList;

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
			copy.add(l.clone());
		}
		return copy;
	}

}
