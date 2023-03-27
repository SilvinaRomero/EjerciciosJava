/**
 * 
 */
package alturas;

/**
 * Representa un pais
 * 
 * @author srome
 *
 */
public class Pais implements Comparable<Pais> {
	private String nombre;
	private String continente;
	private double altura;

	/**
	 * Este es el constructor del pais
	 * 
	 * @param nombre
	 * @param continente
	 * @param altura
	 */
	public Pais(String nombre, String continente, double altura) {
		this.nombre = nombre;
		this.continente = continente;
		this.altura = altura;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContinente() {
		return continente;
	}

	public double getAltura() {
		return altura;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;

		if ((obj instanceof Pais) && this.nombre.equals(((Pais) obj).nombre)) {
			iguales = true;
		}
		return iguales;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.nombre.hashCode();
	}
	/**
	 * Comparar paises por nombre
	 */

	@Override
	public int compareTo(Pais o) {
		return this.nombre.compareTo(o.nombre);
	}

	/**
	 * Muestra elemento
	 */
	@Override
	public String toString() {
		return "Pais(" + nombre + "," + continente + "," + altura + ")";
	}
}
