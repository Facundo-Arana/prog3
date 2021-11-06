package ejercicioEntregable;

import java.util.HashSet;

public class Alumno implements Comparable<Alumno> {

	private String id;
	private int puntaje;

	// los ejemplares leidos sirven para saber si el alumno ya tiene un libro dado
	// segun el siguiente documento el HasSet es optimo para esta tarea
	// https://www.baeldung.com/java-hashset-arraylist-contains-performance
	private HashSet<Integer> ejemplaresLeidos;

	public Alumno(String id, int puntaje) {
		this.id = id;
		this.puntaje = puntaje;
		this.ejemplaresLeidos = new HashSet<Integer>();
	}

	public int puntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getId() {
		return id;
	}

	public boolean estaAsignado(String idLibro) {
		return this.ejemplaresLeidos.contains(idLibro);
	}

	public boolean puedeLeer(Libro libro) {
		return !this.ejemplaresLeidos.contains(libro.id());
	}

	public void leer(Integer libro, int puntaje) {
		this.ejemplaresLeidos.add(libro);
		this.puntaje += puntaje;
	}

	@Override
	public Alumno clone() {
		return new Alumno(id, puntaje);
	}

	@Override
	public String toString() {
		return this.id + " -> " + this.puntaje;
	}

	@Override
	public int compareTo(Alumno o) {
		return o.puntaje() - this.puntaje;
	}
}
