package ejercicioEntregable;

import ejercicioEntregable.greedy.Greedy;
import ejercicioEntregable.modelo.Asignatura;
import ejercicioEntregable.modelo.Biblioteca;
import ejercicioEntregable.modelo.Estado;

public class MainGreedy {

	public static void main(String[] args) {
		float notaAsignatura = 50;
		Greedy greedy = new Greedy();

		/**
		 * dataset1
		 */
		Asignatura a1 = new Asignatura(notaAsignatura, "./datos/alumnos.csv");
		Biblioteca b1 = new Biblioteca("./datos/dataset1.csv");

		Estado s1_1 = greedy.asignarLibros(b1, a1);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset1\n");
		System.out.println(s1_1);
		System.out.println("\n\n");

		notaAsignatura = 85;
		a1.setNota(notaAsignatura);
		Estado s1_2 = greedy.asignarLibros(b1, a1);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset1\n");
		System.out.println(s1_2);
		System.out.println("\n\n");

		notaAsignatura = 200;
		a1.setNota(notaAsignatura);
		Estado s1_3 = greedy.asignarLibros(b1, a1);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset1\n");
		System.out.println(s1_3);
		System.out.println("\n\n");

		/**
		 * dataset2
		 */
		notaAsignatura = 100;
		Asignatura a2 = new Asignatura(notaAsignatura, "./datos/alumnos.csv");
		Biblioteca b2 = new Biblioteca("./datos/dataset2.csv");
		
		Estado s2_1 = greedy.asignarLibros(b2, a2);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s2_1);
		System.out.println("\n\n");
		
		
		notaAsignatura = 200;
		a2.setNota(notaAsignatura);
		Estado s2_2 = greedy.asignarLibros(b2, a2);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s2_2);
		System.out.println("\n\n");

		
		notaAsignatura = 500;
		a2.setNota(notaAsignatura);
		Estado s2_3 = greedy.asignarLibros(b2, a2);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s2_3);
		System.out.println("\n\n");
			
		/**
		 * dataset3 
		 */
		notaAsignatura = 500;
		Asignatura a3 = new Asignatura(notaAsignatura, "./datos/alumnos.csv");
		Biblioteca b3 = new Biblioteca("./datos/dataset3.csv");

		Estado s3_1 = greedy.asignarLibros(b3, a3);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s3_1);
		System.out.println("\n\n");
		

		notaAsignatura = 2000;
		a3.setNota(notaAsignatura);
		Estado s3_2 = greedy.asignarLibros(b3, a3);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s3_2);
		System.out.println("\n\n");
		

		notaAsignatura = 20000;
		a3.setNota(notaAsignatura);
		Estado s3_3 = greedy.asignarLibros(b3, a3);
		System.out.println("GREEDY -> nota asignatura: " + notaAsignatura);
		System.out.println("Libros Entregados: dataset2\n");
		System.out.println(s3_3);
		System.out.println("\n\n");
	}

}
