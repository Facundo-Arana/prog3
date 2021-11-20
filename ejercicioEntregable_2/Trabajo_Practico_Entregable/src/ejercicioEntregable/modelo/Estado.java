package ejercicioEntregable.modelo;

import java.util.*;

public class Estado {

	private HashSet<Alumno> alumnos;
	private int aprobados;
	private int iteraciones;
	private boolean fin;

	public Estado() {
		alumnos = new HashSet<>();
		aprobados = 0;
		iteraciones = 0;
		fin = false;
	}

	public void add(Alumno alumno) {
		if (!alumnos.add(alumno)) {
			this.remove(alumno);
			alumnos.add(alumno);
		}
		this.setAprobados(aprobados);
	}

	public void setAprobados(int aprobados) {
		this.aprobados = aprobados;
	}

	public int aprobados() {
		return this.aprobados;
	}

	public void remove(Alumno alumno) {
		this.alumnos.remove(alumno);
	}

	public void setIteraciones(int c) {
		this.iteraciones = c;
	}

	public String toString() {
		String info = "";
		for (Alumno a : alumnos) {
			info += a + "\nLibros: ";
			info += a.librosLeidos() + "\n\n";
		}
		info += "N° de iteraciones: " + iteraciones + "\n";
		info += "Alumnos aprobados: " + aprobados;
		return info;
	}

	public Estado clone() {
		Estado copy = new Estado();
		for (Alumno a : alumnos) {
			copy.add(a.clone());
		}	
		copy.setAprobados(this.aprobados);
		copy.setIteraciones(iteraciones);
		if (this.esFinal()) {
			copy.fin();
		}
		return copy;
	}

	public void fin() {
		this.fin = true;
	}

	public boolean esFinal() {
		return this.fin;
	}

	public void noEsFinal() {
		this.fin = false;
	}

	public boolean isEmpty() {
		return this.alumnos.isEmpty();
	}

}
