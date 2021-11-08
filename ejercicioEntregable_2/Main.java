package ejercicioEntregable;

import ejercicioEntregable.modelo.*;
import ejercicioEntregable.greedy.*;
import ejercicioEntregable.backtracking.*;

public class Main {

	public static void main(String[] args) {
		float notaAsignatura = 50;
		Asignatura asignatura = new Asignatura(notaAsignatura, "./datos/alumnos.csv");
		Biblioteca biblioteca = new Biblioteca("./datos/dataset2.csv");

//		Greedy greedy = new Greedy();
//		Solucion solucionGreedy = greedy.asignarLibros(biblioteca, asignatura);
//		System.out.println("GREEDY\n");
//		System.out.println(solucionGreedy);
//		System.out.println("\n");
		
		Backtracking backtracking = new Backtracking();
		Solucion solucionBack = backtracking.asignarLibros(biblioteca, asignatura);
		System.out.println("BACKTRACKING -> nota asignatura: "+notaAsignatura+"\n");
		System.out.println(solucionBack);

	}
}
