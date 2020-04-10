package modelos;

import java.util.Scanner;

public class Aplicacion {
	
	public static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);
	}
	
	public static String capturarCadenaCaracteres(String mensaje) {
		String resultado;
		
		while(true) {
			resultado = teclado.nextLine().strip();
			
			if(!resultado.isEmpty()) {
				return resultado;
			}
			
			System.out.println("MENSAJE: Ha escrito una cadena vacía. Específique un valor concreto.");
		}
	}

	public static int capturarNumeroEntero(String mensaje) {
		while(true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Integer.parseInt(teclado.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("MENSAJE: Digite un valor que corresponda con un número entero.");
			}
		}
	}
	
	public static double capturarNumeroReal(String mensaje) {
		while(true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Double.parseDouble(teclado.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("MENSAJE: Digite un valor que corresponda con un número real.");
			}
		}
	}
}
