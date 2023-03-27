package pruebas;

import java.io.FileNotFoundException;

import alturas.Mundo;

/**
 * 
 * @author srome
 *
 */
public class pruebaMundo {

	public static void main(String[] args) {
		try {
			Mundo mundo = Mundo.createFromFile("src/alturas.txt");
			System.out.println(mundo);
			
			System.out.println("\nPaises por altura");
			Mundo.presentaEnConsola(mundo.paisesPorAltura());
			System.out.println("\nNúmero de Paises por continente");
			Mundo.presentaEnConsola(mundo.numeroDePaisesPorContinente());
			System.out.println("\nPaises por continente");
			Mundo.presentaEnConsola(mundo.paisesPorContinente());
			System.out.println("\nPaises por inciales");
			Mundo.presentaEnConsola(mundo.paisesPorInicial());
			System.out.println("\nMedias de alturas por continente");
			Mundo.presentaEnConsola(mundo.mediaPorContinente());
			System.out.println("\nContinentes con mas paises");
			System.out.println(mundo.continentesConMasPaises());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
