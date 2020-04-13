package modelos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

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

	private static final String ARCHIVO_CSV_CLIENTES = "./clientes.csv";
	private static final String ARCHIVO_CSV_PROVEEDORES = "./proveedores.csv";
	private static final String ARCHIVO_CSV_PRODUCTOS = "./productos.csv";
	private static final String ARCHIVO_CSV_FACTURAS = "./facturas.csv";

	private static final char SEPARADOR = ';';

	public static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);

		cargarDatos();

		List<Cliente> clientes = new ArrayList<>();
		List<Proveedor> proveedores = new ArrayList<>();
		List<Producto> productos = new ArrayList<>();
		List<Factura> facturas = new ArrayList<>();

		int opcion;
		int opcionSubmenu;

		Cliente cliente;
		Proveedor proveedor;
		Producto producto;
		Factura factura;

		do {
			do {
				mostrarMenuPrincipal();
				opcion = capturarNumeroEntero("Digite la operación a realizar");

				if (opcion < SALIR || opcion > GESTION_FACTURACION) {
					mostrarMensaje("MENSAJE: Debe digitar un valor entre 0 y 4.");
				}
			} while (opcion < SALIR || opcion > GESTION_FACTURACION);

			if (opcion == SALIR) {
				break;
			}

			System.out.println();

			switch (opcion) {
			case GESTION_CLIENTES:
				do {
					do {
						mostrarSubmenu("Clientes");
						opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar");

						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("MENSAJE: Debe digitar un valor entre 0 y 4.");
							continuar();
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
						if (!clientes.isEmpty()) {
							cliente = buscarCliente(clientes);

							if (cliente != null) {
								mostrarDatosCliente(cliente);
							} else {
								mostrarMensaje("No se encontró un cliente con el número de cédula especificado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un cliente. La búsqueda no se puede efectuar.");
						}

						break;
					case ACTUALIZAR:
						if (!clientes.isEmpty()) {
							cliente = buscarCliente(clientes);

							if (cliente != null) {
								actualizarCliente(cliente);
								mostrarDatosCliente(cliente);
							} else {
								mostrarMensaje("No se encontró un cliente con el número de cédula especificado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un cliente. La actualización no se puede efectuar.");
						}
						break;
					case ELIMINAR:
						if (!clientes.isEmpty()) {
							eliminarCliente(clientes, facturas);
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un cliente. La eliminación no se puede efectuar.");
						}
						break;
					}

					continuar();
				} while (opcionSubmenu != SALIR);

				break;
			case GESTION_PROVEEDORES:
				do {
					do {
						mostrarSubmenu("Proveedores");
						opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar");

						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("MENSAJE: Debe digitar un valor entre 0 y 4.");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);

					if (opcionSubmenu == SALIR) {
						break;
					}

					switch (opcionSubmenu) {
					case CREAR:
						proveedor = crearProveedor(proveedores);

						proveedores.add(proveedor);

						break;
					case BUSCAR:
						if (!proveedores.isEmpty()) {
							proveedor = buscarProveedor(proveedores);

							if (proveedor != null) {
								mostrarDatosProveedor(proveedor);
							} else {
								mostrarMensaje("No se encontró un proveedor con el ID especificado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un proveedor. La búsqueda no se puede efectuar.");
						}

						break;
					case ACTUALIZAR:
						if (!proveedores.isEmpty()) {
							proveedor = buscarProveedor(proveedores);

							if (proveedor != null) {
								actualizarProveedor(proveedor);
								mostrarDatosProveedor(proveedor);
							} else {
								mostrarMensaje("MENSAJE: No existe un proveedor con el ID suministrado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un proveedor. La actualización no se puede efectuar.");
						}
						break;
					case ELIMINAR:
						if (!proveedores.isEmpty()) {
							eliminarProveedor(proveedores, productos);
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un proveedor. La eliminación no se puede efectuar.");
						}
						break;
					}

					continuar();
				} while (opcionSubmenu != SALIR);
				break;
			case GESTION_PRODUCTOS:
				do {
					do {
						mostrarSubmenu("Productos");
						opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar");

						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("MENSAJE: Debe digitar un valor entre 0 y 4.");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);

					if (opcionSubmenu == SALIR) {
						break;
					}

					switch (opcionSubmenu) {
					case CREAR:
						if (!proveedores.isEmpty()) {
							producto = crearProducto(productos, proveedores);

							productos.add(producto);

							mostrarMensaje("MENSAJE: Se ha creado el nuevo producto.");
						} else {
							mostrarMensaje("MENSAJE: Antes de crear un producto, debe crear un proveedor.");
						}

						break;
					case BUSCAR:
						if (!productos.isEmpty()) {
							producto = buscarProducto(productos);

							if (producto != null) {
								mostrarDatosProducto(producto);
							} else {
								mostrarMensaje("MENSAJE: No se encontró un producto con el ID especificado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un producto. La búsqueda no se puede efectuar.");
						}

						break;
					case ACTUALIZAR:
						if (!productos.isEmpty()) {
							producto = buscarProducto(productos);

							if (producto != null) {
								actualizarProducto(producto, proveedores);
								mostrarDatosProducto(producto);
							} else {
								mostrarMensaje("MENSAJE: No existe un proveedor con el ID especificado.");
							}
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un producto. La actualización no se puede efectuar.");
						}
						break;
					case ELIMINAR:
						if (!productos.isEmpty()) {
							eliminarProducto(productos, facturas);
						} else {
							mostrarMensaje(
									"MENSAJE: Aún no se ha creado un producto. La eliminación no se puede efectuar.");
						}
						break;
					}

					continuar();
				} while (opcionSubmenu != SALIR);
				break;
			case GESTION_FACTURACION:
				do {
					do {
						mostrarSubmenuFacturacion();
						opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar");

						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("MENSAJE: Debe digitar un valor entre 0 y 2.");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > BUSCAR);

					if (opcionSubmenu == SALIR) {
						break;
					}

					switch (opcionSubmenu) {
					case CREAR:
						if (!clientes.isEmpty()) {
							if (!productos.isEmpty()) {
								factura = crearFactura(clientes, productos, facturas);

								facturas.add(factura);

								mostrarDatosFactura(factura, clientes, productos);
							} else {
								mostrarMensaje(
										"MENSAJE: No se puede crear una factura mientras que no hallan productos.");
							}
						} else {
							mostrarMensaje("MENSAJE: No se puede crear una factura mientras que no hallan clientes.");
						}

						break;
					case BUSCAR:
						if (!facturas.isEmpty()) {
							factura = buscarFactura(facturas);

							if (factura != null) {
								mostrarDatosFactura(factura, clientes, productos);
							} else {
								mostrarMensaje("MENSAJE: No se encontró una factura con el ID especificado.");
							}
						} else {
							mostrarMensaje("MENSAJE: Aún no se han creado facturas.");
						}

						break;
					}

					continuar();
				} while (opcionSubmenu != SALIR);
				break;
			}

			continuar();

		} while (opcion != SALIR);

		System.out.println();

		mostrarMensaje("El programa ha terminado.");

		continuar();

		if (!clientes.isEmpty()) {
			guardarDatosInventario(clientes, proveedores, productos, facturas);
		}
	}

	private static Map<String, Object> cargarDatos() {
		List<Cliente> clientes = null;
		List<Proveedor> proveedores = null;
		List<Producto> productos = null;
		List<Factura> facturas = null;

		Path rutaArchivo = Paths.get(ARCHIVO_CSV_CLIENTES);

		if (Files.exists(rutaArchivo)) {
			clientes = new ArrayList<>();

			try {
				Reader reader = Files.newBufferedReader(rutaArchivo);
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
								.withHeader("cedula", "nombres", "apellidos", "telefono", "direccion", "correo")
								.withDelimiter(SEPARADOR));

				Cliente cliente;

				for (CSVRecord r : csvParser) {
					cliente = new Cliente(r.get("cedula"), r.get("nombres"), r.get("apellidos"), r.get("telefono"),
							r.get("direccion"), r.get("correo"));

					clientes.add(cliente);
				}

				csvParser.close();
			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		rutaArchivo = Paths.get(ARCHIVO_CSV_PROVEEDORES);

		if (Files.exists(rutaArchivo)) {
			proveedores = new ArrayList<>();

			try {
				Reader reader = Files.newBufferedReader(rutaArchivo);
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withHeader("id", "nombre", "telefono", "direccion").withDelimiter(SEPARADOR));

				Proveedor proveedor;

				for (CSVRecord r : csvParser) {
					proveedor = new Proveedor(Integer.parseInt(r.get("id")), r.get("nombre"), r.get("telefono"),
							r.get("direccion"));

					proveedores.add(proveedor);
				}

				csvParser.close();
			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		rutaArchivo = Paths.get(ARCHIVO_CSV_PRODUCTOS);

		if (Files.exists(rutaArchivo)) {
			productos = new ArrayList<>();

			try {
				Reader reader = Files.newBufferedReader(rutaArchivo);
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withHeader("id", "nombre", "descripcion", "precioCompra", "precioVenta",
								"cantidad", "cantidadMinimaStock", "idProveedor").withDelimiter(SEPARADOR));

				Producto producto;

				for (CSVRecord r : csvParser) {
					producto = new Producto();

					producto.setId(Integer.parseInt(r.get("id")));
					producto.setNombre(r.get("nombre"));
					producto.setDescripcion(r.get("descripcion"));
					producto.setPrecioCompra(Double.parseDouble(r.get("precioCompra")));
					producto.setPrecioVenta(Double.parseDouble(r.get("precioVenta")));
					producto.setCantidad(Integer.parseInt(r.get("cantidad")));
					producto.setCantidadMinimaStock(Integer.parseInt(r.get("cantidadMinimaStock")));
					producto.setIdProveedor(Integer.parseInt(r.get("idProveedor")));

					productos.add(producto);
				}

				csvParser.close();
			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		rutaArchivo = Paths.get(ARCHIVO_CSV_FACTURAS);

		if (Files.exists(rutaArchivo)) {
			facturas = new ArrayList<>();

			try {
				Reader reader = Files.newBufferedReader(rutaArchivo);
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
								.withHeader("id", "fecha", "cedulaCliente", "impuesto", "total", "idsProductos")
								.withDelimiter(SEPARADOR));

				Factura factura;

				for (CSVRecord r : csvParser) {
					factura = new Factura();
					factura.setId(Integer.parseInt(r.get("id")));
					factura.setFecha(new Date(r.get("fecha")));
					factura.setCedulaCliente(r.get("cedulaCliente"));
					factura.setImpuesto(Double.parseDouble(r.get("impuesto")));
					factura.setTotal(Double.parseDouble(r.get("total")));
					
					String[] idsProductos = StringUtils.split(r.get("idsProductos"), ",");
					factura.setIdsProductosDesdeArregloCadenas(idsProductos);
					
					facturas.add(factura);
				}

				csvParser.close();
			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}
		
		Map<String, Object> inventario = new HashMap<>();
		inventario.put("clientes", clientes);
		inventario.put("proveedores", proveedores);
		inventario.put("productos", productos);
		inventario.put("facturas", facturas);
		
		return inventario;
	}

	private static void guardarDatosInventario(List<Cliente> clientes, List<Proveedor> proveedores,
			List<Producto> productos, List<Factura> facturas) {

		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(ARCHIVO_CSV_CLIENTES));

			CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader("cedula", "nombres", "apellidos", "telefono", "direccion", "correo")
							.withDelimiter(SEPARADOR));

			for (Cliente c : clientes) {
				csvPrinter.printRecord(c.getCedula(), c.getNombres(), c.getApellidos(), c.getTelefono(),
						c.getDireccion(), c.getCorreoElectronico());
			}

			csvPrinter.flush();
			csvPrinter.close();

		} catch (IOException e) {
			System.err.println("ERROR: " + e.getMessage());
		}

		if (!proveedores.isEmpty()) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(ARCHIVO_CSV_PROVEEDORES));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("id", "nombre", "telefono", "direccion").withDelimiter(SEPARADOR));

				for (Proveedor p : proveedores) {
					csvPrinter.printRecord(p.getId(), p.getNombre(), p.getTelefono(), p.getDireccion());
				}

				csvPrinter.flush();
				csvPrinter.close();

			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		if (!productos.isEmpty()) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(ARCHIVO_CSV_PRODUCTOS));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("id", "nombre", "descripcion", "precioCompra", "precioVenta",
								"cantidad", "cantidadMinimaStock", "idProveedor").withDelimiter(SEPARADOR));

				for (Producto p : productos) {
					csvPrinter.printRecord(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecioCompra(),
							p.getPrecioVenta(), p.getCantidad(), p.getCantidadMinimaStock(), p.getIdProveedor());
				}

				csvPrinter.flush();
				csvPrinter.close();

			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		if (!facturas.isEmpty()) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(ARCHIVO_CSV_FACTURAS));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT
								.withHeader("id", "fecha", "cedulaCliente", "impuesto", "total", "idsProductos")
								.withDelimiter(SEPARADOR));

				for (Factura f : facturas) {
					csvPrinter.printRecord(f.getId(), f.getFecha(), f.getCedulaCliente(), f.getImpuesto(), f.getTotal(),
							StringUtils.join(f.getIdsProductos(), ","));
				}

				csvPrinter.flush();
				csvPrinter.close();

			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		System.out.println();
		System.out.println("Se han guardado todos los datos del inventario.");
		System.out.println();
	}

	private static void mostrarDatosFactura(Factura factura, List<Cliente> clientes, List<Producto> productos) {
		System.out.println();
		System.out.println("Datos de la factura");

		System.out.println("ID: " + factura.getId());
		System.out.println("Fecha: " + factura.getFecha().toString());
		System.out.println("Total factura: $" + factura.getTotal());

		Cliente cliente = buscarClientePorCedula(clientes, factura.getCedulaCliente());

		System.out.println("Datos del cliente:");
		System.out.println("Cédula: " + cliente.getCedula());
		System.out.printf("Nombre completo: %s %s\n", cliente.getNombres(), cliente.getApellidos());

		System.out.println();

		System.out.println("Productos comprados:");

		Producto producto;

		for (Integer id : factura.getIdsProductos()) {
			producto = buscarProductoPorId(productos, id);

			System.out.println("ID: " + id);
			System.out.println("Nombre: " + producto.getNombre());
			System.out.println("Precio: $" + producto.getPrecioVenta());

			System.out.println();
		}
	}

	private static Factura buscarFactura(List<Factura> facturas) {
		System.out.println();
		System.out.println("--- 2. Buscar Factura ---");

		int idFactura;
		Factura factura;

		do {
			System.out.println("Listado de facturas");

			for (Factura f : facturas) {
				System.out.printf("%d. %s - %s\n", f.getId(), f.getCedulaCliente(), f.getFecha().toString());
			}

			idFactura = capturarNumeroEntero("Digite el ID de la factura");

			if (idFactura <= 0) {
				mostrarMensaje("MENSAJE: El ID de la factura debe ser un número positivo.");
				continuar();
				continue;
			}

			factura = buscarFacturaPorId(facturas, idFactura);

			if (factura != null) {
				break;
			} else {
				mostrarMensaje("MENSAJE: No se ha encontrado una factura con el ID especificado.");
				continuar();
			}
		} while (true);

		return factura;
	}

	private static Factura buscarFacturaPorId(List<Factura> facturas, int idFactura) {
		return facturas.stream().filter(f -> f.getId() == idFactura).findFirst().orElse(null);
	}

	private static Factura crearFactura(List<Cliente> clientes, List<Producto> productos, List<Factura> facturas) {
		System.out.println();
		System.out.println("--- 1. Crear Factura ---");

		String cedula;
		int numeroCedula;
		Cliente cliente;
		List<Integer> idsProductos = new ArrayList<>();
		int idProducto;
		double total = 0;
		Producto producto;
		int cantidad;
		int impuesto;

		do {
			System.out.println("Listado de clientes");
			for (Cliente c : clientes) {
				System.out.printf("%s. %s %s\n", c.getCedula(), c.getNombres(), c.getApellidos());
			}
			numeroCedula = capturarNumeroEntero("Digite el número de cédula del cliente");

			if (numeroCedula <= 0) {
				mostrarMensaje("MENSAJE: El número de cédula no puede ser negativo.");
				continuar();
				continue;
			}

			cedula = String.valueOf(numeroCedula);

			cliente = buscarClientePorCedula(clientes, cedula);

			if (cliente != null) {
				break;
			} else {
				mostrarMensaje("MENSAJE: No existe un cliente con el número de cédula especificado.");
				continuar();
			}

		} while (true);

		System.out.println();

		do {
			System.out.println("Listado de productos:");
			for (Producto p : productos) {
				System.out.printf("%d. %s - $%.2f - %d unidades\n", p.getId(), p.getNombre(), p.getPrecioVenta(),
						p.getCantidad());
			}
			System.out.println("0. Salir");
			idProducto = capturarNumeroEntero("Digite el ID del producto");

			if (idProducto == SALIR && !idsProductos.isEmpty()) {
				break;
			}

			if (idProducto <= 0) {
				mostrarMensaje("MENSAJE: El ID del producto debe ser un número positivo.");
				continue;
			}

			producto = buscarProductoPorId(productos, idProducto);

			if (producto != null) {
				if (producto.getCantidad() > 0) {
					do {
						cantidad = capturarNumeroEntero("Digite la cantidad a comprar de este producto");

						if (cantidad <= 0) {
							mostrarMensaje("MENSAJE: Debe digitar una cantidad positiva.");
							continuar();
							continue;
						}

						if (cantidad > producto.getCantidad()) {
							mostrarMensaje("MENSAJE: No hay cantidad suficiente de este producto. Cantidad disponible: "
									+ producto.getCantidad());
							continuar();
							continue;
						}

						break;
					} while (true);

					producto.setCantidad(producto.getCantidad() - cantidad);

					total += producto.getPrecioVenta() * cantidad;

					idsProductos.add(idProducto);
				} else {
					mostrarMensaje("MENSAJE: Este producto no cuenta con existencias.");
					continuar();
				}
			} else {
				mostrarMensaje("MENSAJE: No existe un producto con el ID especificado.");
				continuar();
			}

		} while (true);

		do {
			impuesto = capturarNumeroEntero("Digite el impuesto para esta factura");

			if (impuesto <= 0) {
				mostrarMensaje("MENSAJE: El impuesto debe ser un número positivo.");
				continuar();
				continue;
			}

			if (impuesto > 100) {
				mostrarMensaje("MENSAJE: El impuesto especificado no es un valor válido. Debe estar entre 1 y 100.");
				continuar();
				continue;
			}

			break;
		} while (true);

		Factura nuevaFactura = new Factura(cedula, impuesto / 100.0);
		idsProductos.forEach(id -> nuevaFactura.agregarIdProducto(id));
		nuevaFactura.setTotal(total);

		return nuevaFactura;
	}

	private static void eliminarProducto(List<Producto> productos, List<Factura> facturas) {
		System.out.println("--- 4. Eliminar Producto ---");

		int id;
		Producto producto;

		do {
			id = capturarNumeroEntero("Digite el número de ID del producto");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}
		} while (id <= 0);

		producto = buscarProductoPorId(productos, id);

		if (producto != null) {

			if (!productoEnAlgunaFactura(id, facturas)) {

				productos.remove(producto);

				mostrarMensaje(String.format("Se ha eliminado el producto con el ID %d.\n", id));

			} else {
				mostrarMensaje("MENSAJE: No se puede eliminar el producto porque está asociado a una factura.");
				continuar();
			}

		} else {
			mostrarMensaje("MENSAJE: No se ha encontrado ningún producto con el ID especificado.");
			continuar();
		}
	}

	private static boolean productoEnAlgunaFactura(int id, List<Factura> facturas) {
		Integer[] idsProductos;

		for (Factura factura : facturas) {
			idsProductos = factura.getIdsProductos();

			Arrays.sort(idsProductos);
			if (Arrays.binarySearch(idsProductos, id) != -1) {
				return true;
			}
		}

		return false;
	}

	private static void actualizarProducto(Producto producto, List<Proveedor> proveedores) {
		System.out.println("--- 3. Actualizar Producto ---");

		String nombre;
		String descripcion;
		double precioCompra;
		double precioVenta;
		int cantidad;
		int cantidadMinimaStock;
		int idProveedor;

		nombre = capturarCadenaCaracteres("Digite el nuevo nombre para el producto");
		descripcion = capturarCadenaCaracteres("Digite la nueva descripción del producto");

		do {
			precioCompra = capturarNumeroReal("Digite el nuevo precio de compra para el producto");

			if (precioCompra <= 0) {
				mostrarMensaje("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
				continuar();
			}
		} while (precioCompra <= 0);

		do {
			precioVenta = capturarNumeroReal("Digite el nuevo precio de venta para el producto");

			if (precioVenta <= 0) {
				mostrarMensaje("MENSAJE: El precio de venta no puede ser menor o igual a 0.");
				continuar();
				continue;
			}

			if (precioVenta <= precioCompra) {
				mostrarMensaje("MENSAJE: El precio de venta no puede ser menor o igual al precio de compra.");
				precioVenta = 0;
			}
		} while (precioVenta <= 0);

		do {
			cantidad = capturarNumeroEntero("Digite la nueva cantidad para el producto");

			if (cantidad <= 0) {
				mostrarMensaje("MENSAJE: Debe escribir una cantidad positiva.");
				continuar();
			}
		} while (cantidad <= 0);

		do {
			cantidadMinimaStock = capturarNumeroEntero("Digite la neva cantidad mínima de stock para el producto");

			if (cantidadMinimaStock <= 0) {
				mostrarMensaje("MENSAJE: Debe escribir una cantidad mínima de stock positiva.");
				continuar();
			}
		} while (cantidadMinimaStock <= 0);

		do {
			System.out.println("Listado de Proveedores Disponibles");
			for (Proveedor proveedor : proveedores) {
				System.out.printf("%d. %s\n", proveedor.getId(), proveedor.getNombre());
			}
			idProveedor = capturarNumeroEntero("Digite el ID del proveedor: ");

			if (buscarProveedorPorId(proveedores, idProveedor) != null) {
				break;
			} else {
				mostrarMensaje(String.format("MENSAJE: No existe un proveedor con el %d especificado.\n", idProveedor));

			}

		} while (true);

		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecioCompra(precioCompra);
		producto.setPrecioVenta(precioVenta);
		producto.setCantidad(cantidad);
		producto.setCantidadMinimaStock(cantidadMinimaStock);
		producto.setIdProveedor(idProveedor);
	}

	private static void mostrarDatosProducto(Producto producto) {
		System.out.println();
		System.out.println("Datos del producto");
		System.out.println("ID: " + producto.getId());
		System.out.println("Nombre: " + producto.getNombre());
		System.out.println("Descripción: " + producto.getDescripcion());
		System.out.println("Precio de compra: " + producto.getPrecioCompra());
		System.out.println("Precio de venta: " + producto.getPrecioVenta());
		System.out.println("Cantidad: " + producto.getCantidad());
		System.out.println("Cantidad mínima de stock: " + producto.getCantidadMinimaStock());
		System.out.println("ID Proveedor: " + producto.getIdProveedor());
	}

	private static Producto buscarProducto(List<Producto> productos) {
		System.out.println("--- 2. Buscar Producto ---");

		int id;

		do {
			id = capturarNumeroEntero("Digite el número de ID del producto");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}
		} while (id <= 0);

		return buscarProductoPorId(productos, id);
	}

	private static Producto crearProducto(List<Producto> productos, List<Proveedor> proveedores) {
		System.out.println("--- 1. Crear Producto ---");

		int id;
		String nombre;
		String descripcion;
		Producto producto;
		double precioCompra;
		double precioVenta;
		int cantidad;
		int cantidadMinimaStock;
		int idProveedor;

		do {
			id = capturarNumeroEntero("Digite el número de ID del nuevo producto");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}

			producto = buscarProductoPorId(productos, id);

			if (producto != null) {
				mostrarMensaje("MENSAJE: Ya existe un producto con el ID especificado");
				continuar();
				id = 0;
			}
		} while (id <= 0);

		nombre = capturarCadenaCaracteres("Digite el nombre para el nuevo producto");
		descripcion = capturarCadenaCaracteres("Digite la descripción del nuevo producto");

		do {
			precioCompra = capturarNumeroReal("Digite el precio de compra para el nuevo producto");

			if (precioCompra <= 0) {
				mostrarMensaje("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
				continuar();
			}
		} while (precioCompra <= 0);

		do {
			precioVenta = capturarNumeroReal("Digite el precio de compra para el nuevo producto");

			if (precioVenta <= 0) {
				mostrarMensaje("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
				continuar();
				continue;
			}

			if (precioVenta <= precioCompra) {
				mostrarMensaje("MENSAJE: El precio de ventana no puede ser menor o igual al precio de compra.");
				continuar();
				precioVenta = 0;
			}
		} while (precioVenta <= 0);

		do {
			cantidad = capturarNumeroEntero("Digite la cantidad para el nuevo producto");

			if (cantidad <= 0) {
				mostrarMensaje("MENSAJE: Debe escribir una cantidad positiva.");
				continuar();
			}
		} while (cantidad <= 0);

		do {
			cantidadMinimaStock = capturarNumeroEntero("Digite la cantidad mínima de stock para el nuevo producto");

			if (cantidadMinimaStock <= 0) {
				mostrarMensaje("MENSAJE: Debe escribir una cantidad mínima de stock positiva.");
				continuar();
			}
		} while (cantidadMinimaStock <= 0);

		do {
			System.out.println("Listado de Proveedores Disponibles");
			for (Proveedor proveedor : proveedores) {
				System.out.printf("%d. %s\n", proveedor.getId(), proveedor.getNombre());
			}
			idProveedor = capturarNumeroEntero("Digite el ID del proveedor");

			if (idProveedor <= 0) {
				mostrarMensaje("MENSAJE: El ID de producto no puede ser un número negativo o igual a cero.");
				continuar();
				continue;
			}

			if (buscarProveedorPorId(proveedores, idProveedor) != null) {
				break;
			} else {
				mostrarMensaje(
						String.format("MENSAJE: No existe un proveedor con el ID %d especificado.\n", idProveedor));
				continuar();
			}

		} while (true);

		return new Producto(id, nombre, descripcion, precioCompra, precioVenta, cantidad, cantidadMinimaStock,
				idProveedor);
	}

	private static Producto buscarProductoPorId(List<Producto> productos, int id) {
		return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	private static void eliminarProveedor(List<Proveedor> proveedores, List<Producto> productos) {

		int id;
		Proveedor proveedor;

		do {
			id = capturarNumeroEntero("Digite el número de ID del proveedor");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}
		} while (id <= 0);

		proveedor = buscarProveedorPorId(proveedores, id);

		if (proveedor != null) {
			List<Producto> productosProveedor = buscarProductosPorIdProveedor(productos, id);

			if (productosProveedor.isEmpty()) {
				proveedores.remove(proveedor);

				mostrarMensaje(String.format("MENSAJE: Se ha eliminado el proveedor con ID %d.\n", id));
			} else {
				mostrarMensaje("MENSAJE: No puede eliminar este proveedor. Tiene asociado uno o más productos.");
				continuar();
			}
		} else {
			mostrarMensaje("MENSAJE: No existe un proveedor con el número de ID especificado.");
			continuar();
		}
	}

	private static List<Producto> buscarProductosPorIdProveedor(List<Producto> productos, int idProveedor) {
		return productos.stream().filter(p -> p.getIdProveedor() == idProveedor).collect(Collectors.toList());
	}

	private static void actualizarProveedor(Proveedor proveedor) {
		System.out.println("--- 3. Actualizar Proveedor ---");

		String nombre;
		int telefono;
		String direccion;

		nombre = capturarCadenaCaracteres("Digite el nuevo nombre del proveedor");

		do {
			telefono = capturarNumeroEntero("Digite el nuevo número de teléfono proveedor");

			if (telefono <= 0) {
				mostrarMensaje("MENSAJE: El número de teléfono debe ser un valor positivo.");
				continuar();
			}
		} while (telefono <= 0);

		direccion = capturarCadenaCaracteres("Digite la nueva dirección del proveedor");

		proveedor.setNombre(nombre);
		proveedor.setTelefono(String.valueOf(telefono));
		proveedor.setDireccion(direccion);
	}

	private static void mostrarDatosProveedor(Proveedor proveedor) {
		System.out.println("Datos del proveedor");
		System.out.println("ID: " + proveedor.getId());
		System.out.println("Nombre: " + proveedor.getNombre());
		System.out.println("Teléfono: " + proveedor.getTelefono());
		System.out.println("Dirección: " + proveedor.getDireccion());
	}

	private static Proveedor buscarProveedor(List<Proveedor> proveedores) {
		System.out.println("--- 2. Buscar Proveedor ---");

		int id;

		do {
			id = capturarNumeroEntero("Digite el número ID del proveedor");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}
		} while (id <= 0);

		return buscarProveedorPorId(proveedores, id);
	}

	private static Proveedor crearProveedor(List<Proveedor> proveedores) {
		System.out.println();
		System.out.println("--- 1. Crear Proveedor ---");

		int id;
		Proveedor proveedor;
		String nombre;
		int telefono;
		String direccion;

		do {
			id = capturarNumeroEntero("Digite el número ID del nuevo proveedor");

			if (id <= 0) {
				mostrarMensaje("MENSAJE: Debe digitar un número de ID positivo.");
				continuar();
				continue;
			}

			proveedor = buscarProveedorPorId(proveedores, id);

			if (proveedor != null) {
				mostrarMensaje("MENSAJE: Ya existe un proveedor con el ID especificado");
				continuar();
				id = 0;
			}
		} while (id <= 0);

		nombre = capturarCadenaCaracteres("Digite el nombre para el nuevo proveedor");

		do {
			telefono = capturarNumeroEntero("Digite el número de teléfono del nuevo proveedor");

			if (telefono <= 0) {
				mostrarMensaje("MENSAJE: El número de teléfono debe ser un valor positivo.");
				continuar();
			}
		} while (telefono <= 0);

		direccion = capturarCadenaCaracteres("Digite la dirección del nuevo proveedor");

		return new Proveedor(id, nombre, String.valueOf(telefono), direccion);
	}

	private static Proveedor buscarProveedorPorId(List<Proveedor> proveedores, int id) {
		return proveedores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	private static void eliminarCliente(List<Cliente> clientes, List<Factura> facturas) {
		int numeroCedula;
		String cedula;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente");

			if (numeroCedula <= 0) {
				mostrarMensaje("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continuar();
				continue;
			}
		} while (numeroCedula <= 0);

		cedula = String.valueOf(numeroCedula);

		Cliente cliente = buscarClientePorCedula(clientes, cedula);

		if (cliente != null) {
			Factura factura = buscarFacturaPorCedula(facturas, cedula);

			if (factura == null) {

				clientes.remove(cliente);

				mostrarMensaje(String.format("MENSAJE: Se ha eliminado el cliente con cédula: %s\n", cedula));

			} else {
				mostrarMensaje("No se puede eliminar el cliente. Tiene una o más facturas asignadas.");
				continuar();
			}
		} else {
			mostrarMensaje("MENSAJE: No se encontró ningún cliente con el número de cédula especificado.");
			continuar();
		}
	}

	private static Factura buscarFacturaPorCedula(List<Factura> facturas, String cedula) {
		for (Factura factura : facturas) {
			if (factura.getCedulaCliente().equals(cedula)) {
				return factura;
			}
		}

		return null;
	}

	private static void actualizarCliente(Cliente cliente) {
		System.out.println("--- 3. Actualizar Cliente ---");

		String nombres = capturarCadenaCaracteres("Digite los nuevos nombres del cliente");
		String apellidos = capturarCadenaCaracteres("Digite los nuevos apellidos del cliente");

		int telefono;

		do {
			telefono = capturarNumeroEntero("Digite el nuevo número de teléfono del cliente");

			if (telefono <= 0) {
				mostrarMensaje("MENSAJE: El número de teléfono debe ser un valor positivo.");
				continuar();
			}
		} while (telefono <= 0);

		String direccion = capturarCadenaCaracteres("Digite la nueva dirección del cliente");
		String correoElectronico;

		while (true) {
			correoElectronico = capturarCadenaCaracteres("Digite el nuevo correo electrónico del cliente");

			if (!correoElectronicoValido(correoElectronico)) {
				mostrarMensaje("MENSAJE: Ha digito un valor que no corresponde con un correo electrónico.");
				continuar();
				continue;
			}

			break;
		}

		cliente.setNombres(nombres);
		cliente.setApellidos(apellidos);
		cliente.setTelefono(String.valueOf(telefono));
		cliente.setDireccion(direccion);
		cliente.setCorreoElectronico(correoElectronico);
	}

	private static void mostrarDatosCliente(Cliente cliente) {
		System.out.println("Datos del cliente");
		System.out.println("Cédula: " + cliente.getCedula());
		System.out.println("Nombres: " + cliente.getNombres());
		System.out.println("Apellidos: " + cliente.getApellidos());
		System.out.println("Teléfono: " + cliente.getTelefono());
		System.out.println("Dirección: " + cliente.getDireccion());
		System.out.println("Correo electrónico: " + cliente.getCorreoElectronico());
	}

	private static Cliente buscarCliente(List<Cliente> clientes) {
		System.out.println();
		System.out.println("--- 2. Buscar Cliente ---");

		int numeroCedula;
		String cedula;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente");

			if (numeroCedula <= 0) {
				mostrarMensaje("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continuar();
				continue;
			}
		} while (numeroCedula <= 0);

		cedula = String.valueOf(numeroCedula);

		return buscarClientePorCedula(clientes, cedula);
	}

	private static Cliente crearCliente(List<Cliente> clientes) {
		System.out.println();
		System.out.println("--- 1. Crear Cliente ---");
		int numeroCedula;
		int telefono;
		String cedula = "";
		Cliente cliente;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente nuevo");

			if (numeroCedula <= 0) {
				mostrarMensaje("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continuar();
				continue;
			}

			cedula = String.valueOf(numeroCedula);

			cliente = buscarClientePorCedula(clientes, cedula);

			if (cliente != null) {
				mostrarMensaje(String.format("MENSAJE: Ya existe otro con el número de cédula: %s.\n", cedula));
				numeroCedula = 0;
			}
		} while (numeroCedula <= 0);

		String nombres = capturarCadenaCaracteres("Digite los nombres del cliente nuevo");
		String apellidos = capturarCadenaCaracteres("Digite los apellidos del cliente nuevo");

		do {
			telefono = capturarNumeroEntero("Digite el número de teléfono del cliente nuevo");

			if (telefono <= 0) {
				mostrarMensaje("MENSAJE: El número de teléfono debe ser un valor positivo.");
				continuar();
			}
		} while (telefono <= 0);

		String direccion = capturarCadenaCaracteres("Digite la dirección del cliente nuevo");
		String correoElectronico;

		while (true) {
			correoElectronico = capturarCadenaCaracteres("Digite el correo electrónico del cliente nuevo");

			if (!correoElectronicoValido(correoElectronico)) {
				mostrarMensaje("MENSAJE: Ha digito un valor que no corresponde con un correo electrónico.");
				continuar();
				continue;
			}

			break;
		}

		return new Cliente(cedula, nombres, apellidos, String.valueOf(telefono), direccion, correoElectronico);
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
			System.out.printf("%s: ", mensaje);
			resultado = teclado.nextLine().strip();

			if (!resultado.isEmpty()) {
				return resultado;
			}

			System.out.println("\nMENSAJE: Ha escrito una cadena vacía. Específique un valor concreto.");

			continuar();
		}
	}

	public static int capturarNumeroEntero(String mensaje) {
		while (true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMENSAJE: Digite un valor que corresponda con un número entero.");
			}

			continuar();
		}
	}

	public static double capturarNumeroReal(String mensaje) {
		while (true) {
			try {
				System.out.printf("%s: ", mensaje);
				return Double.parseDouble(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMENSAJE: Digite un valor que corresponda con un número real.");
			}

			continuar();
		}
	}

	static boolean correoElectronicoValido(String correo) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return correo.matches(regex);
	}

	static void continuar() {
		System.out.println();
		System.out.print("Presione Enter para continuar...");
		teclado.nextLine();
		System.out.println();
	}

	static void mostrarMensaje(String mensaje) {
		System.out.println();
		System.out.printf(mensaje);
		System.out.println();
	}
}
