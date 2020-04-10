package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
	private static final int SALIR = 0;
	private static final int GESTION_CLIENTES = 1;
	private static final int GESTION_PROVEEDORES = 2;
	private static final int GESTION_PRODUCTOS = 3;
	private static final int GESTION_FACTURACION = 4;
	
	public static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);
		
		List<Cliente> clientes = new ArrayList<>();
		List<Proveedor> proveedores = new ArrayList<>();
		List<Producto> productos = new ArrayList<>();
		List<Factura> facturas = new ArrayList<>();
		
		int opcion;
		
		do {
			do {
				mostrarMenuPrincipal();
				opcion = capturarNumeroEntero("Digite la operación a realizar: ");
				
				if (opcion < SALIR || opcion > GESTION_FACTURACION) {
					System.out.println("MENSAJE: Debe digitar un valor entre 0 y 4.");
				}
			while (opcion < SALIR || opcion > GESTION_FACTURACION);
			
		} while (opcion != SALIR);
		
		
	}
	
	public static void mostrarMenuPrincipal() {
		System.out.println("=== MENÚ PRINCIPAL ===");
		System.out.println("1. Gestión Clientes");
		System.out.println("2. Gestión Proveedores");
		System.out.println("3. Gestión Productos");
		System.out.println("4. Gestión Facturación");
		System.out.println("0. Salir");
	}
	
	public static void mostrarSubmenu(String tipoMenu) {
		System.out.printf("*** Menú Gestión %s***\n", tipoMenu);
		System.out.println("1. Crear");
		System.out.println("2. Buscar");
		System.out.println("3. Actualizar");
		System.out.println("4. Eliminar");
		System.out.println("0. Salir");
	}
	
	public static void mostrarSubmenuFacturacion() {
		System.out.println("*** Menú Gestión Facturación ***");
		System.out.println("1. Crear");
		System.out.println("2. Buscar");
		System.out.println("0. Salir");
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
