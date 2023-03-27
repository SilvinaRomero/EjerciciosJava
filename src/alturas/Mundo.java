package alturas;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 
 * @author sromero
 *
 */

public class Mundo {
	List<Pais> paises;

	/**
	 * Constructor privado que recibe listado de paises
	 * 
	 * @param paises
	 */
	private Mundo(List<Pais> paises) {
		this.paises = paises;
	}

	public static Mundo createFromFile(String file) throws FileNotFoundException {
//		Albania,Europe,1.74
		List<Pais> paisesFich = new LinkedList<Pais>();
		Pais paisTmp;
		String linea;
		String[] datosPais;

		// leemos el archivo cn Scanner
		Scanner sc = new Scanner(new File(file));

		while (sc.hasNextLine()) {
			linea = sc.nextLine();
			try {
//				System.out.println("como sale el pais " + linea);
				datosPais = linea.split(",");
				paisTmp = new Pais(datosPais[0], datosPais[1], Double.parseDouble(datosPais[2]));
				// añadimos el elemento a la lista LinkedList
				paisesFich.add(paisTmp);
			} catch (Exception e) {
				System.out.println("Linea incorrecta: " + linea);
			}
		}
		/**
		 * Como el constructor es privado, cuando llamamos a esta clase, usamos la
		 * funcion createFromFile y cuando hacemos el return creamos el nuevo objeto
		 * Mundo, que solo puede ser creado desde dentro de la clase
		 */
		return new Mundo(paisesFich);
	}

	/**
	 * Mostramos el objeto mundo por la consola
	 */
	/**
	 * Recibe una correspondencia y la lmuestra por pantalla con el formato clave,
	 * valor
	 * 
	 * @param <K> clase de clave
	 * @param <V> clase del valor
	 * @param map correspondencia
	 */
	public static <K, V> void presentaEnConsola(Map<K, V> map) {
		// Iterator<K> para iterar las keys
		Iterator<K> it = map.keySet().iterator();
		K elem;

		while (it.hasNext()) {
			elem = it.next();
			System.out.println(elem + " \t" + map.get(elem));
		}
		System.out.println("\n_________________________________________________________________________________\n");
	}

	/**
	 * 
	 * @return
	 */
	public SortedMap<String, Integer> numeroDePaisesPorContinente() {
		SortedMap<String, Integer> paisesPorContinente = new TreeMap();
		// recorer los paises
		Iterator<Pais> it = paises.iterator();
		// objeto Pais(de los que rellena Mundo)
		Pais pais;
		Integer valor;

		while (it.hasNext()) {
			pais = it.next();
			// valor tendra el numero de paises para ese continente
			// o valdrá null si no esta el continente añadido
			valor = paisesPorContinente.get(pais.getContinente());

			if (valor == null) {
				paisesPorContinente.put(pais.getContinente(), 1);
			} else {
				valor++; // número de paises del continente
				paisesPorContinente.put(pais.getContinente(), valor);
			}
		}
		return paisesPorContinente;
	}

	/**
	 * 
	 * @return
	 */
	public SortedMap<Double, List<Pais>> paisesPorAltura() {
		SortedMap<Double, List<Pais>> paisesPorAltura = new TreeMap();
		// Iterador
		Iterator<Pais> it = paises.iterator();
		Pais pais;
		List<Pais> listaPaises;
		Double altu;

		while (it.hasNext()) {
			pais = it.next();
			altu = (Math.round(pais.getAltura() * 100.0) / 100.0);
			listaPaises = paisesPorAltura.get(altu);
			if (listaPaises == null) {
				listaPaises = new LinkedList<Pais>();
			}
			paisesPorAltura.put(altu, listaPaises);
			listaPaises.add(pais);
		}
		return paisesPorAltura;
	}

	/**
	 * 
	 * @return
	 */
	public SortedMap<String, List<Pais>> paisesPorContinente() {
		SortedMap<String, List<Pais>> paisesPorContinente = new TreeMap();

		Iterator<Pais> it = paises.iterator();
		Pais pais;
		List<Pais> listaPaises;
		String continente;

		while (it.hasNext()) {
			pais = it.next();
			continente = pais.getContinente();
			listaPaises = paisesPorContinente.get(continente);
			if (listaPaises == null) {
				listaPaises = new LinkedList<Pais>();
				paisesPorContinente.put(continente, listaPaises);
			}
			listaPaises.add(pais);
		}
		return paisesPorContinente;
	}

//	String palabra = "arbol";
//	char primeraLetra = palabra.charAt(0);
	public SortedMap<Character, List<Pais>> paisesPorInicial() {
		SortedMap<Character, List<Pais>> paisesPorLetra = new TreeMap();
		// Iterador
		Iterator<Pais> it = paises.iterator();
		Pais pais;
		List<Pais> listaPaises;
		Character letra;

		while (it.hasNext()) {
			pais = it.next();
			letra = (pais.getNombre()).charAt(0);
			listaPaises = paisesPorLetra.get(letra);
			if (listaPaises == null) {
				listaPaises = new LinkedList<Pais>();
			}
			paisesPorLetra.put(letra, listaPaises);
			listaPaises.add(pais);
		}
		return paisesPorLetra;
	}

	public SortedMap<String, Double> mediaPorContinente() {
		SortedMap<String, Double> mediaPorContinente = new TreeMap();
		Double total = 0.0;
		SortedMap<String, List<Pais>> Paises = this.paisesPorContinente();
		Iterator<Map.Entry<String, List<Pais>>> iter = Paises.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, List<Pais>> entry = iter.next();
			String continente = entry.getKey();
			List<Pais> listaPaises = entry.getValue();
			double totalAlturas = 0.0;
			int numPaises = 0;
			for (Pais pais : listaPaises) {
			    totalAlturas += pais.getAltura();
			    numPaises++;
			}
			double media = totalAlturas / numPaises;

			if (listaPaises == null) {
				listaPaises = new LinkedList<Pais>();
			}
			mediaPorContinente.put(continente, media);
		}
		return mediaPorContinente;
	}
	
	public List<String> continentesConMasPaises() {
		List<String> continentesConMasPaises = new ArrayList<>();
		Integer total_actual,total = 0;
		String continente_actual,continente="";
		SortedMap<String, Integer> Paises = this.numeroDePaisesPorContinente();
		Iterator<Map.Entry<String, Integer>> iter = Paises.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry<String, Integer> actual = iter.next();
			continente_actual = actual.getKey();
			total_actual = actual.getValue();
			if(total_actual > total) {
				total = total_actual;
				continente = continente_actual;
			}
		}
		continentesConMasPaises.add(continente);
		return continentesConMasPaises;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		Iterator<Pais> it = paises.iterator();

		while (it.hasNext()) {
			res.append(it.next() + " - ");
		}

		return res.toString();
	}
}
