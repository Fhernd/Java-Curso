package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
	private static final int SALIR = 0;
	private static final int GESTION_CLIENTES = 1;
	private static final int GESTION_PROVEEDORES = 2;
	private static final int GESTION_PRODUCTOS = 3;
	private static final int GESTION_FACTURACION = 4;

	private static final int CREAR = 1;
	private static final int BUSCAR = 2;
	private static final int ACTUALIZAR = 3;
	private static final int ELIMINAR = 4;

	public static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);

		List<Cliente> clientes = new ArrayList<>();
		List<Proveedor> proveedores = new ArrayList<>();
		List<Producto> productos = new ArrayList<>();
		List<Factura> facturas = new ArrayList<>();

		int opcion;
		int opcionSubmenu;
		
		Cliente cliente;

		do {
			do {
				mostrarMenuPrincipal();
				opcion = capturarNumeroEntero("Digite la operación a realizar: ");

				if (opcion < SALIR || opcion > GESTION_FACTURACION) {
					System.out.println("MENSAJE: Debe digitar un valor entre 0 y 4.");
				}
			} while (opcion < SALIR || opcion > GESTION_FACTURACION);

			if (opcion == SALIR) {
				break;
			}

			switch (opcion) {
			case GESTION_CLIENTES:

				do {
					mostrarSubmenu("Clientes");
					opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar: ");

					if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
						System.out.println("MENSAJE: Debe digitar un valor entre 0 y 4.");
					}
				} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);

				if (opcionSubmenu == SALIR) {
					break;
				}

				switch (opcionSubmenu) {
				case CREAR:
					cliente = crearCliente(clientes);
					
					clientes.add(cliente);
					
					break;
				case BUSCAR:
					cliente = buscarCliente(clientes);
					break;
				case ACTUALIZAR:

					break;
				case ELIMINAR:

					break;
				}

				break;
			case GESTION_PROVEEDORES:

				break;
			case GESTION_PRODUCTOS:

				break;
			case GESTION_FACTURACION:

				break;
			}

		} while (opcion != SALIR);

	}

	private static Cliente buscarCliente(List<Cliente> clientes) {
		int numeroCedula;
		String cedula;
		Cliente cliente;
		
		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente nuevo");

			if (numeroCedula <= 0) {
				System.out.println("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continue;
			}

			cedula = String.valueOf(numeroCedula);

			cliente = buscarClientePorCedula(clientes, cedula);

			if (cliente != null) {
				System.out.printf("MENSAJE: Ya existe otro con el número de cédula: %s.\n", cedula);
				numeroCedula = 0;
			}
		} while (numeroCedula <= 0);
		
		return null;
	}

	private static Cliente crearCliente(List<Cliente> clientes) {
		System.out.println("--- 1. Crear Cliente ---");
		int numeroCedula;
		int telefono;
		String cedula = "";
		Cliente cliente;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente nuevo");

			if (numeroCedula <= 0) {
				System.out.println("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continue;
			}

			cedula = String.valueOf(numeroCedula);

			cliente = buscarClientePorCedula(clientes, cedula);

			if (cliente != null) {
				System.out.printf("MENSAJE: Ya existe otro con el número de cédula: %s.\n", cedula);
				numeroCedula = 0;
			}
		} while (numeroCedula <= 0);

		String nombres = capturarCadenaCaracteres("Digite los nombres del cliente nuevo");
		String apellidos = capturarCadenaCaracteres("Digite los apellidos del cliente nuevo");

		do {
			telefono = capturarNumeroEntero("Digite el número de teléfono del cliente nuevo");

			if (telefono <= 0) {
				System.out.println("MENSAJE: El número de teléfono debe ser un valor positivo.");
			}
		} while (telefono <= 0);

		String direccion = capturarCadenaCaracteres("Digite la dirección del cliente nuevo");
		String correoElectronico;
		
		while(true) {
			correoElectronico = capturarCadenaCaracteres("Digite el correo electrónico del cliente nuevo");
			
			if (!correoElectronicoValido(correoElectronico)) {
				System.out.println("MENSAJE: Ha digito un valor que no corresponde con un correo electrónico.");
				continue;
			}
			
			break;
		} 
		
		cliente = new Cliente(cedula, nombres, apellidos, String.valueOf(telefono), direccion, correoElectronico);
		
		return cliente;
	}

	private static Cliente buscarClientePorCedula(List<Cliente> clientes, String cedula) {
		for (Cliente cliente : clientes) {
			if (cliente.getCedula().equals(cedula)) {
				return cliente;
			}
		}

		return null;
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

		while (true) {
			resultado = teclado.nextLine().strip();

			if (!resultado.isEmpty()) {
				return resultado;
			}

			System.out.println("MENSAJE: Ha escrito una cadena vacía. Específique un valor concreto.");
		}
	}

	public static int capturarNumeroEntero(String mensaje) {
		while (true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("MENSAJE: Digite un valor que corresponda con un número entero.");
			}
		}
	}

	public static double capturarNumeroReal(String mensaje) {
		while (true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Double.parseDouble(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("MENSAJE: Digite un valor que corresponda con un número real.");
			}
		}
	}

	static boolean correoElectronicoValido(String correo) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return correo.matches(regex);
	}
}
