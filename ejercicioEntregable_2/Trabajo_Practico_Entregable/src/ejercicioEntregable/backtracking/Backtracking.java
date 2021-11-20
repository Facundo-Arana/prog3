package ejercicioEntregable.backtracking;

import java.util.ArrayList;

import ejercicioEntregable.modelo.*;

public class Backtracking {

	private ArrayList<Alumno> alumnos;
	private ArrayList<Libro> libros;
	private Estado mejorSolucion;
	private int c;
	private int indiceLibro;
	private int aprobados;
	private float nota; // nota para aprobar asignatura

	public Estado asignarLibros(Biblioteca biblioteca, Asignatura asignatura) {
		this.mejorSolucion = new Estado();
		Estado solucionParcial = new Estado();
		this.alumnos = asignatura.getAlumnos();
		this.libros = biblioteca.getLibros();
		this.nota = asignatura.nota();
		this.c = 0;
		this.indiceLibro = 0;
		this.aprobados = 0;
		this.asignarLibros(solucionParcial);
		this.mejorSolucion.setIteraciones(c);
		return mejorSolucion;
	}

	private void asignarLibros(Estado estado) {

		// limito los estados posibles a 100 millones porque sino explota mi procesador
		if (c == 100000000) {
			return;
		}
		
		c++;

	
		if (estado.esFinal() || this.esSolucionOptima()) {
			
			// para el caso de que no apruebe ningun estudiante, se registra la primera solucion final
			if (estado.aprobados() > this.mejorSolucion.aprobados() || this.mejorSolucion.isEmpty()) {
				
				this.mejorSolucion = estado.clone();
			}

		} else {
			Libro libro = this.libros.get(indiceLibro);
			boolean poda = true;
			for (Alumno alumno : this.alumnos) {

				if (!poda(alumno, libro) && cumpleRestricciones(alumno, libro)) {
					poda = false;
					this.asignar(alumno, libro, estado);
					this.asignarLibros(estado);
					this.restaurar(alumno, libro, estado);
				}
			}
			if (poda && this.libroSiguiente()) {
				this.indiceLibro++;
				this.asignarLibros(estado);
				this.indiceLibro--;
			}
		}
	}

	private boolean esSolucionOptima() {
		return this.mejorSolucion.aprobados() == this.alumnos.size();
	}

	private boolean hayLibro() {
		return this.indiceLibro < this.libros.size();
	}

	private boolean libroSiguiente() {
		return this.indiceLibro < this.libros.size() - 1;
	}

	private boolean poda(Alumno alumno, Libro libro) {
		return alumno.estaAprobado(this.nota);
	}

	private boolean cumpleRestricciones(Alumno alumno, Libro libro) {
		return alumno.puedeLeer(libro) && libro.tieneEjemplares();
	}

	private void asignar(Alumno alumno, Libro libro, Estado estado) {
		boolean aprueba = false;
		if (!alumno.estaAprobado(nota)) {
			aprueba = true;
		}
		alumno.leer(libro);
		libro.restarEjemplar();
		if (!libro.tieneEjemplares()) {
			this.indiceLibro++;
		}
		if (alumno.estaAprobado(this.nota) && aprueba) {
			this.aprobados++;
			estado.setAprobados(this.aprobados);
		}
		estado.add(alumno);
		if (this.aprobados == this.alumnos.size() || !this.hayLibro()) {
			estado.fin();
		}
	}

	private void restaurar(Alumno alumno, Libro libro, Estado estado) {
		boolean desaprueba = false;
		if (alumno.estaAprobado(nota)) {
			desaprueba = true;
		}
		alumno.restaurar(libro);
		if (!alumno.estaAprobado(this.nota) && desaprueba) {
			this.aprobados--;
			estado.setAprobados(this.aprobados);
		}
		estado.add(alumno);

		if (!libro.tieneEjemplares()) {
			this.indiceLibro--;
		}

		if (estado.esFinal()) {
			estado.noEsFinal();
		}
		libro.sumarEjemplar();
	}

}
