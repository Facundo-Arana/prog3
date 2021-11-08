package ejercicioEntregable.backtracking;

import java.util.*;
import ejercicioEntregable.modelo.*;

public class Estado {

	private ArrayList<Alumno> alumnos;
	private ArrayList<Libro> libros;

	private Solucion solucion;
	private int indiceLibro;
	private int aprobados;
	private float nota; // nota para aprobar asignatura

	public Estado(ArrayList<Libro> libros, ArrayList<Alumno> alumnos, float nota) {
		this.alumnos = alumnos;
		this.libros = libros;
		this.indiceLibro = 0;
		this.aprobados = 0;
		this.nota = nota;
		this.solucion = new Solucion();
	}

	public Libro libroSiguiente() {
		Libro l = null;
		if (indiceLibro < libros.size() && indiceLibro >= 0) {
			l = libros.get(indiceLibro);
		}
		return l;
	}

	public int aprobados() {
		return aprobados;
	}

	public Solucion solucion() {
		return this.solucion.clone();
	}

	public ArrayList<Alumno> alumnos() {
		return alumnos;
	}

	public boolean posible(Alumno alumno, Libro libro) {
		return alumno.puedeLeer(libro) && !alumno.estaAprobado(nota) && libro.tieneEjemplares();
	}

	public void asignar(Alumno alumno, Libro libro) {
		alumno.leer(libro.clone());
		libro.restarEjemplar();
		if (!libro.tieneEjemplares()) {
			indiceLibro++;
		}
		if (alumno.estaAprobado(nota)) {
			this.aprobados++;
			solucion.setAprobados(this.aprobados);
			solucion.add(alumno.clone());
		}
	}

	public void restaurar(Alumno alumno, Libro libro) {
		if (alumno.estaAprobado(nota)) {
			this.aprobados--;
			solucion.setAprobados(this.aprobados);
		}
		alumno.restaurar(libro);
		solucion.add(alumno.clone());

		if (!libro.tieneEjemplares()) {
			indiceLibro--;
		}
		libro.sumarEjemplar();
	}

	public void seguir() {
		indiceLibro++;
	}

	public void volver() {
		indiceLibro--;
	}

}
