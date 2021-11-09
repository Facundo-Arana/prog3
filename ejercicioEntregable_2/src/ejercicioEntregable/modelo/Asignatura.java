package ejercicioEntregable.modelo;

import ejercicioEntregable.reader.CSVReaderAlumnos;
import java.util.ArrayList;


public class Asignatura {

	private float nota;
	private ArrayList<Alumno> alumnos;

	private CSVReaderAlumnos alumnoReader;

	public Asignatura(float nota, String path) {
		this.nota = nota;
		alumnoReader = new CSVReaderAlumnos();
		alumnoReader.setPath(path);
		alumnos = alumnoReader.read();
	}

	public ArrayList<Alumno> getAlumnos() {
		ArrayList<Alumno> copy = new ArrayList<>();
		for (Alumno a : alumnos) {
			copy.add(a.clone());
		}
		return copy;
	}

	public float nota() {
		return nota;
	}

	/**
	 * @print
	 */
	public void imprimirAlumnos(String info) {
		System.out.println(info);
		for (int i = 0; i < alumnos.size(); i++) {
			System.out.println(alumnos.get(i));
		}
		System.out.println();
	}
	
	/**
	 * @print
	 */
	public void imprimirAlumnos(String info, ArrayList<Alumno> alumnos) {
		System.out.println(info);
		for (int i = 0; i < alumnos.size(); i++) {
			System.out.println(alumnos.get(i));
		}
		System.out.println();
	}
}
