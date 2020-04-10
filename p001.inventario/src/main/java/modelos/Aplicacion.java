package modelos;

import java.util.Scanner;

public class Aplicacion {
	
	public static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);
	}

	public static int capturarEntero(String mensaje) {
		while(true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Integer.parseInt(teclado.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("MENSAJE: Digite un valor que corresponda con un número entero.");
			}
		}
	}
}
