package ejercicioEntregable.backtracking;

import java.util.ArrayList;

import ejercicioEntregable.modelo.*;

public class Backtracking {

	private ArrayList<Alumno> alumnos;
	private ArrayList<Libro> libros;
	private Solucion solucionFinal;
	private Solucion solucionParcial;
	private int c;
	private int indiceLibro;
	private int aprobados;
	private float nota; // nota para aprobar asignatura

	
	public Solucion asignarLibros(Biblioteca biblioteca, Asignatura asignatura) {
		this.solucionFinal = new Solucion();
		this.solucionParcial = new Solucion();
		this.alumnos = asignatura.getAlumnos();
		this.libros = biblioteca.getLibros();
		this.nota = asignatura.nota();
		this.c = 0;
		this.indiceLibro = 0;
		this.aprobados = 0;
		this.asignarLibros(this.solucionParcial);
		this.solucionFinal.setIteraciones(c);
		return solucionFinal;
	}

	private void asignarLibros(Solucion estado) {
		
		// limito los estados posibles a 100 millones porque sino explota mi procesador
		if (c == 100000000) {
			return;
		}
		c++;
		if (this.solucionParcial.aprobados() > this.solucionFinal.aprobados()) {
			this.solucionFinal = this.solucionParcial.clone();
		} else {
			
			if (this.hayLibro()) {			
				Libro libro = this.libros.get(indiceLibro);			
				boolean poda = true;
				for (Alumno alumno : this.alumnos()) {
					if (!this.poda(alumno, libro)) {
						poda = false;
						this.asignar(alumno, libro);
						this.asignarLibros(estado);
						this.restaurar(alumno, libro);
					}
				}
				if (poda && this.libroSiguiente()) {
					this.indiceLibro++;
					this.asignarLibros(estado);
					this.indiceLibro--;
				}
			}

		}
	}

	private boolean hayLibro() {
		return this.indiceLibro < this.libros.size();
	}
	
	private boolean libroSiguiente() {
		return this.indiceLibro < this.libros.size() -1;
	}


	private ArrayList<Alumno> alumnos() {
		return this.alumnos;
	}

	private boolean poda(Alumno alumno, Libro libro) {
		return !alumno.puedeLeer(libro) || alumno.estaAprobado(this.nota) || !libro.tieneEjemplares();
	}

	private void asignar(Alumno alumno, Libro libro) {
		alumno.leer(libro);
		libro.restarEjemplar();
		if (!libro.tieneEjemplares()) {
			this.indiceLibro++;
		}
		if (alumno.estaAprobado(this.nota)) {
			this.aprobados++;
			this.solucionParcial.setAprobados(this.aprobados);
			this.solucionParcial.add(alumno);
		}
		if (this.aprobados == this.alumnos.size()) {
			this.solucionParcial.fin();
		}
	}

	private void restaurar(Alumno alumno, Libro libro) {
		if (alumno.estaAprobado(this.nota)) {
			this.aprobados--;
			this.solucionParcial.setAprobados(this.aprobados);
		}
		alumno.restaurar(libro);
		this.solucionParcial.add(alumno);

		if (!libro.tieneEjemplares()) {
			this.indiceLibro--;
		}
		libro.sumarEjemplar();
	}

}
