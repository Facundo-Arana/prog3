package ejercicioEntregable.greedy;

import ejercicioEntregable.modelo.*;
import java.util.*;

public class Greedy {

	// contador de iteraciones el alumnos y libros
	private int cA;
	private int cL;
	private float nota;

	public Greedy() {
		cA = 0;
		cL = 0;
	}

	/**
	 * Informar asignacion de libros a alumnos
	 * 
	 * @param biblioteca : aporta la lista de libros
	 * @param asignatura : aporta la lista de alumnos y la condicion de aprobacion
	 */
	public Solucion asignarLibros(Biblioteca biblioteca, Asignatura asignatura) {
		ArrayList<Libro> libros = biblioteca.getLibros();
		ArrayList<Alumno> alumnos = asignatura.getAlumnos();
		this.nota = asignatura.nota();

		// se ordenan primeros los que estan mas cerca de aprobar
		Collections.sort(alumnos);

		// se ordenan primeros los de mayor puntaje
		Collections.sort(libros);

//		asignatura.imprimirAlumnos("estado inicial de alumnos");
		Solucion solucion = this.asignarLibros(alumnos, libros);

		return solucion;
	}

	/**
	 * O(a x l) siendo 'a' el numero de alumnos y 'l' el numero de libros
	 * 
	 * En el peor de los casos se encuentra el mejor libro para cada alumno en la
	 * ultima posicion de la lista de libros
	 * 
	 * @param alumnos        : lista de alumnos
	 * @param libros         : lista de libros
	 */
	private Solucion asignarLibros(ArrayList<Alumno> alumnos, ArrayList<Libro> libros) {
		int indiceL = 0;
		int indiceA = 0;
		int alumnosAprobados = 0;

		Solucion solucion = new Solucion();

		while (indiceA != -1 && indiceL != -1 ) {

			// buscar alumno (el alumno es el candidato)
			indiceA = seleccionarAlumno(indiceA, alumnos);

			if (indiceA != -1) {
				Alumno alumno = alumnos.get(indiceA);
				
				// buscar libros para el alumno
				while (alumno.puntaje() < this.nota && indiceL != -1) {
					indiceL = seleccionarLibro(alumno, libros, indiceL);
					if (indiceL != -1) {

						// asignar libro al alumno
						Libro libro = libros.get(indiceL);
						alumno.leer(libro.clone());

						libro.restarEjemplar();
						if (!libro.tieneEjemplares()) {
							libros.remove(indiceL);
						}
					}

				}
				solucion.add(alumno);
				if (alumno.puntaje() >= this.nota) {
					alumnosAprobados++;
					solucion.setAprobados(alumnosAprobados);
				}
				if (!libros.isEmpty()) {
					indiceL = 0;
				}
				indiceA++;
			}
		}
		solucion.setIteraciones(cA + cL);
		cA = 0;
		cL = 0;
		return solucion;
	}

	/**
	 * O(a) siendo 'a' el número de alumnos
	 * 
	 * @param alumnos : lista de alumnos
	 * @param indice  : indice desde donde inicia la busqueda
	 * @return posicion del alumno encontrado o -1
	 */
	private int seleccionarAlumno(int indice, ArrayList<Alumno> alumnos) {
		if (indice == -1) {
			return indice;
		}
		boolean find = false;
		while (indice < alumnos.size() && !find) {
			cA++;
			find = alumnos.get(indice).puntaje() < this.nota;
			if (!find) {
				indice++;
			}
		}
		return (indice < alumnos.size()) ? indice : -1;
	}

	/**
	 * O(l) siendo 'l' el número de libros disponibles
	 * 
	 * @param alumno : candidato dado
	 * @param libros : lista de libros sin asignar
	 * @param indice : posicion desde donde inicia la busqueda
	 * @return posicion del libro encontrado o -1
	 */
	private int seleccionarLibro(Alumno alumno, ArrayList<Libro> libros, int indice) {

		int salto = (int) Math.floor(libros.size() / 10);
		if (salto == 0) {
			salto = 1;
		}
		int p = alumno.puntaje();
		int sum = Integer.MAX_VALUE;
		int ultimoPosible = -1;
		int primeroPosible = -1;
		while (indice < libros.size() && sum > this.nota) {
			cL++;

			// se puede pensar esta comprobacion como el factible()
			if (alumno.puedeLeer(libros.get(indice)) && libros.get(indice).tieneEjemplares()) {

				sum = libros.get(indice).puntaje() + p;

				// el primer libro posible de leer
				// no importa si se alcanza la nota de aprobacion
				if (primeroPosible == -1) {
					primeroPosible = indice;
				}

				// ultimo libro posible de leer
				// solo si se alcanza la nota de aprobacion
				if (sum >= this.nota) {
					ultimoPosible = indice;
				}

				// optimizacion del salto (el indice salta 1/10 del total de libros sin asignar)
				indice += salto;
			} 
			else {
				indice++;
			}
		}

		// ultimoPosible tiene puntaje mas cercano a la nota de aprobacion
		// si nunca se alcanzo la nota de aprobacion se retorna el primero posible
		return (ultimoPosible == -1) ? primeroPosible : ultimoPosible;
	}

}
