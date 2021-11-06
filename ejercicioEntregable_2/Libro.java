package ejercicioEntregable;

public class Libro implements Comparable<Libro> {

	private String titulo, autor, genero;
	private Integer idLibro,paginas, puntaje, nroEjemplares;

	public Libro(Integer idLibro, String titulo, String autor, String genero, Integer paginas, Integer puntaje,
			Integer nroEjemplares) {
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.paginas = paginas;
		this.puntaje = puntaje;
		this.nroEjemplares = nroEjemplares;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer puntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

	public Integer id() {
		return idLibro;
	}
	
	@Override
	public Libro clone() {
		return new Libro(idLibro, titulo, autor, genero,paginas, puntaje, nroEjemplares);
	}

	@Override
	public String toString() {
		return this.idLibro + " -> " + this.puntaje;
	}

	@Override
	public int compareTo(Libro o) {
		return o.puntaje() - this.puntaje;
	}

	public void restarEjemplar() {
		nroEjemplares--;
	}

	public int ejemplares() {
		return nroEjemplares;
	}

}
