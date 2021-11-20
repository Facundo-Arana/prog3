package ejercicioEntregable.modelo;

import java.util.HashSet;

public class Alumno implements Comparable<Alumno> {

	private String id;
	private int puntaje;

	// los ejemplares leidos sirven para saber si el alumno ya tiene un libro dado
	// segun el siguiente documento el HasSet es optimo para esta tarea
	// https://www.baeldung.com/java-hashset-arraylist-contains-performance
	private HashSet<Libro> ejemplaresLeidos;

	public Alumno(String id, int puntaje) {
		this.id = id;
		this.puntaje = puntaje;
		this.ejemplaresLeidos = new HashSet<Libro>();
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

	public boolean puedeLeer(Libro libro) {
		return !this.ejemplaresLeidos.contains(libro);
	}

	public void leer(Libro libro) {
		this.ejemplaresLeidos.add(libro);
		this.puntaje += libro.puntaje();
	}

	public void restaurar(Libro libro) {
		this.ejemplaresLeidos.remove(libro);
		this.puntaje -= libro.puntaje();
	}
	
	public String librosLeidos() {
		return (ejemplaresLeidos.isEmpty()) ? "empty" : ejemplaresLeidos.toString();
	}
	
	public HashSet<Libro> getLibros() {
		HashSet<Libro> copy = new HashSet<>();
		for(Libro l : ejemplaresLeidos) {
			copy.add(l.clone());
		}
		return copy;
	}
	
	private void setLibros(HashSet<Libro> copy) {
		this.ejemplaresLeidos = copy;
	}
	
	public boolean estaAprobado(float nota) {	
		return puntaje >= (int) nota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Alumno other = (Alumno) obj;
		if (!id.equals(other.id))
			return false;

		return true;
	}

	@Override
	public Alumno clone() {
		Alumno copy = new Alumno(id, puntaje);
		copy.setLibros(this.getLibros());
		return copy;
	}

	@Override
	public String toString() {
		return this.id + " -> \npuntaje: " + this.puntaje ;
	}

	@Override
	public int compareTo(Alumno o) {
		return o.puntaje() - this.puntaje;
	}

}
