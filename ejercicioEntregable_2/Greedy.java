package ejercicioEntregable;

import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

	// contador de iteraciones el alumnos y libros
	private int cA;
	private int cL;

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
	public String asignarLibros(Biblioteca biblioteca, Asignatura asignatura) {
		ArrayList<Libro> libros = biblioteca.getLibros();
		ArrayList<Alumno> alumnos = asignatura.getAlumnos();
		float nota = asignatura.nota();

		// se ordenan primeros los que estan mas cerca de aprobar
		Collections.sort(alumnos);

		// se ordenan primeros los de mayor puntaje
		Collections.sort(libros);
		
		System.out.println(libros);
		String info = "";
		info += "cantidad alumnos: " + alumnos.size() + "\n";
		info += "cantidad libros: " + libros.size() + "\n\n";

		asignatura.imprimirAlumnos("estado inicial de alumnos");
		info += this.asignarLibros(alumnos, nota, libros);

		asignatura.imprimirAlumnos("estado final alumnos: ", alumnos);
		return info;
	}

	/**
	 * O(a x l) siendo 'a' el numero de alumnos y 'l' el numero de libros
	 * 
	 * En el peor de los casos se encuentra el mejor libro para cada alumno en la
	 * ultima posicion de la lista de libros
	 * 
	 * @param alumnos        : lista de alumnos
	 * @param libros         : lista de libros
	 * @param notaAsignatura : condicion de aprobacion
	 */
	private String asignarLibros(ArrayList<Alumno> alumnos, float nota, ArrayList<Libro> libros) {
		String info = "nota para aprobar asignatura : " + nota + "\n\n";
		int indiceL = 0;
		int indiceA = 0;
		int alumnosAprobados = 0;

		while (indiceA != -1 && indiceL != -1) {

			// buscar alumno (el alumno es el candidato)
			indiceA = seleccionarAlumno(indiceA, alumnos, nota);
			
			
			if (indiceA != -1) {
				Alumno alumno = alumnos.get(indiceA);

				info += alumno.getId() + "\n";
				info += "puntaje inicial: " + alumno.puntaje() + "\n";
				info += "libros asignados: ";

				// buscar libros para el alumno
				while (alumnos.get(indiceA).puntaje() < nota && indiceL != -1) {
					indiceL = seleccionarLibro(alumno, libros, nota, indiceL);
					if (indiceL != -1) {

						// asignar libro al alumno
						Libro libro = libros.get(indiceL);
						info += "\n		" + libro.getTitulo();
						alumno.leer(libro.id(), libro.puntaje());

						// eliminar libro cuando ya no tiene mas ejemplares
						libro.restarEjemplar();
						if (libro.ejemplares() == 0) {
							libros.remove(indiceL);
						}
					}

				}
				if (alumno.puntaje() >= nota) {
					alumnosAprobados++;
				}
				if(!libros.isEmpty()) {
					indiceL = 0;
				}
				indiceA++;
				info += "\npuntaje final: " + alumno.puntaje() + "\n \n";
			}
		}
		info += "el bucle termino porque ";
		info += (indiceA != -1 || !libros.isEmpty()) ? "se entregaron todos los libros" : "aprobaron todos los alumnos";
		info += "!!!!!\n\n";
		info += ("iteraciones totales de alumnos: " + cA + "\n");
		info += ("iteraciones totales de libros: " + cL+ "\n");
		info += "Total aprobados: " + alumnosAprobados ;

		cA = 0;
		cL = 0;
		return info;
	}

	/**
	 * O(a) siendo 'a' el número de alumnos
	 * 
	 * @param alumnos : lista de alumnos
	 * @param indice  : indice desde donde inicia la busqueda
	 * @return posicion del alumno encontrado o -1
	 */
	private int seleccionarAlumno(int indice, ArrayList<Alumno> alumnos, float nota) {
		if (indice == -1) {
			return indice;
		}
		boolean find = false;
		while (indice < alumnos.size() && !find) {
			cA++;
			find = alumnos.get(indice).puntaje() < nota;
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
	private int seleccionarLibro(Alumno alumno, ArrayList<Libro> libros, float nota, int indice) {

		int salto = (int) Math.floor(libros.size() / 10);
		if (salto == 0) {
			salto = 1;
		}
		int p = alumno.puntaje();
		int sum = Integer.MAX_VALUE;
		int ultimoPosible = -1;
		int primeroPosible = -1;
		while (indice < libros.size() && sum > nota) {
			cL++;
			if (alumno.puedeLeer(libros.get(indice))) {
				sum = libros.get(indice).puntaje() + p;

				// el primer libro posible de leer
				// no importa si se alcanza la nota de aprobacion
				if (primeroPosible == -1) {
					primeroPosible = indice;
				}

				// ultimo libro posible de leer
				// solo si se alcanza la nota de aprobacion
				if (sum >= nota) {
					ultimoPosible = indice;
				}
				
				// optimizacion salto
				indice += salto;
			}else {
				indice++;
			}
		}

		// ultimoPosible tiene puntaje mas cercano a la nota de aprobacion
		// si nunca se alcanzo la nota de aprobacion se retorna el primero posible
		return (ultimoPosible == -1) ? primeroPosible : ultimoPosible;
	}

}
