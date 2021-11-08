package ejercicioEntregable.backtracking;

import ejercicioEntregable.modelo.*;

public class Backtracking {

	private Solucion solucion;
	private int c;

	public Backtracking() {
		c = 0;
	}

	public Solucion asignarLibros(Biblioteca biblioteca, Asignatura asignatura) {
		this.solucion = new Solucion();
		Estado e = new Estado(biblioteca.getLibros(), asignatura.getAlumnos(), asignatura.nota());
		this.asignarLibros(e);
		solucion.setIteraciones(c);
		c = 0;
		return solucion;
	}

	private void asignarLibros(Estado estado) {
		c++;
		if (estado.aprobados() > this.solucion.aprobados()) {
			this.solucion = estado.solucion();
//			System.out.println(solucion);
//			System.out.println();
//			System.out.println("---------------------------");
		} else {
			Libro libro = estado.libroSiguiente();
			if (libro != null) {
				boolean asignacion = false;
				for (Alumno alumno : estado.alumnos()) {
					if (!poda(estado, alumno, libro)) {
						asignacion = true;
						estado.asignar(alumno, libro);
						this.asignarLibros(estado);
						estado.restaurar(alumno,libro);
					}
				}	
				if(!asignacion) {
					estado.seguir();
					this.asignarLibros(estado);
					estado.volver();
				}
			}
			
		}
	}

	private boolean poda(Estado estado, Alumno alumno, Libro libro) {
		return !estado.posible(alumno, libro);
	}

}
