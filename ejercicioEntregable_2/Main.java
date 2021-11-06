package ejercicioEntregable;

public class Main {

	public static void main(String[] args) {
		float notaAsignatura = 20000;
		Asignatura asignatura = new Asignatura(notaAsignatura, "./datos/alumnos.csv");
		Biblioteca biblioteca = new Biblioteca("./datos/dataset3.csv");

		Greedy greedy = new Greedy();

		String info = greedy.asignarLibros(biblioteca, asignatura);
		System.out.println(info);
	}
}
