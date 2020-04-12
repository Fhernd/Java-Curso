package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
					mostrarSubmenu("Clientes");
					opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar: ");

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
					cliente = buscarCliente(clientes);

					if (cliente != null) {
						mostrarDatosCliente(cliente);
					} else {
						mostrarMensaje("No se encontró un cliente con el número de cédula especificado.");
					}

					break;
				case ACTUALIZAR:
					cliente = buscarCliente(clientes);

					if (cliente != null) {
						actualizarCliente(cliente);
						mostrarDatosCliente(cliente);
					} else {
						mostrarMensaje("No se encontró un cliente con el número de cédula especificado.");
					}
					break;
				case ELIMINAR:
					eliminarCliente(clientes, facturas);
					break;
				}

				break;
			case GESTION_PROVEEDORES:
				do {
					mostrarSubmenu("Proveedores");
					opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar: ");

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
					proveedor = buscarProveedor(proveedores);

					if (proveedor != null) {
						mostrarDatosProveedor(proveedor);
					} else {
						mostrarMensaje("No se encontró un proveedor con el ID especificado.");
					}

					break;
				case ACTUALIZAR:
					proveedor = buscarProveedor(proveedores);

					if (proveedor != null) {
						actualizarProveedor(proveedor);
						mostrarDatosProveedor(proveedor);
					} else {
						mostrarMensaje("MENSAJE: No existe un proveedor con el ID suministrado.");
					}
					break;
				case ELIMINAR:
					eliminarProveedor(proveedores, productos);
					break;
				}
				break;
			case GESTION_PRODUCTOS:
				do {
					mostrarSubmenu("Productos");
					opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar: ");

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
					producto = crearProducto(productos, proveedores);

					productos.add(producto);

					break;
				case BUSCAR:
					producto = buscarProducto(productos);

					if (producto != null) {
						mostrarDatosProducto(producto);
					} else {
						mostrarMensaje("No se encontró un producto con el ID especificado.");
					}

					break;
				case ACTUALIZAR:
					producto = buscarProducto(productos);

					if (producto != null) {
						actualizarProducto(producto, proveedores);
						mostrarDatosProducto(producto);
					} else {
						mostrarMensaje("MENSAJE: No existe un proveedor con el ID especificado.");
					}
					break;
				case ELIMINAR:
					eliminarProducto(productos, facturas);
					break;
				}
				break;
			case GESTION_FACTURACION:
				do {
					mostrarSubmenuFacturacion();
					opcionSubmenu = capturarNumeroEntero("Digite la operación a realizar: ");

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
					factura = crearFactura(clientes, productos, facturas);

					facturas.add(factura);

					break;
				case BUSCAR:
					factura = buscarFactura(facturas);

					if (factura != null) {
						 mostrarDatosFactura(factura, clientes, productos);
					} else {
						mostrarMensaje("No se encontró una factura con el ID especificado.");
					}

					break;
				}
				break;
			}

		} while (opcion != SALIR);

	}

	private static void mostrarDatosFactura(Factura factura, List<Cliente> clientes, List<Producto> productos) {
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
				mostrarMensaje("No se ha encontrado una factura con el ID especificado.");
			}
		} while(true);
		
		return factura;
	}

	private static Factura buscarFacturaPorId(List<Factura> facturas, int idFactura) {
		return facturas.stream().filter(f -> f.getId() == idFactura).findFirst().get();
	}

	private static Factura crearFactura(List<Cliente> clientes, List<Producto> productos, List<Factura> facturas) {
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
				System.out.println("MENSAJE: El número de cédula no puede ser negativo.");
				continue;
			}

			cedula = String.valueOf(numeroCedula);

			cliente = buscarClientePorCedula(clientes, cedula);

			if (cliente != null) {
				break;
			} else {
				System.out.println("MENSAJE: No existe un cliente con el número de cédula especificado.");
			}

		} while (true);

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
				System.out.println("MENSAJE: El ID del producto debe ser un número positivo.");
				continue;
			}

			producto = buscarProductoPorId(productos, idProducto);

			if (producto != null) {
				if (producto.getCantidad() > 0) {
					do {
						cantidad = capturarNumeroEntero("Digite la cantidad a comprar de este producto");

						if (cantidad <= 0) {
							System.out.println("MENSAJE: Debe digitar una cantidad positiva.");
							continue;
						}

						if (cantidad > producto.getCantidad()) {
							System.out.println(
									"MENSAJE: No hay cantidad suficiente de este producto. Cantidad disponible: "
											+ producto.getCantidad());
							continue;
						}

						break;
					} while (true);
					
					producto.setCantidad(producto.getCantidad() - cantidad);

					total += producto.getPrecioVenta() * cantidad;

					idsProductos.add(idProducto);
				} else {
					System.out.println("MENSAJE: Este producto no cuenta con existencias.");
				}
			} else {
				System.out.println("MENSAJE: No existe un producto con el ID especificado.");
			}

		} while (true);
		
		do {
			impuesto = capturarNumeroEntero("Digite el impuesto para esta factura");
			
			if (impuesto <= 0) {
				System.out.println("El impuesto debe ser un número positivo.");
				continue;
			}
			
			if (impuesto > 100) {
				System.out.println("MENSAJE: El impuesto especificado no es un valor válido. Debe estar entre 1 y 100.");
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
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
				continue;
			}
		} while (id <= 0);

		producto = buscarProductoPorId(productos, id);

		if (producto != null) {

			if (!productoEnAlgunaFactura(id, facturas)) {

				productos.remove(producto);

				System.out.printf("Se ha eliminado el producto con el ID %d.\n", id);

			} else {
				System.out.println("MENSAJE: No se puede eliminar el producto porque está asociado a una factura.");
			}

		} else {
			System.out.println("MENSAJE: No se ha encontrado ningún producto con el ID especificado.");
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

		nombre = capturarCadenaCaracteres("Digite el nombre para el producto");
		descripcion = capturarCadenaCaracteres("Digite la descripción del producto");

		do {
			precioCompra = capturarNumeroReal("Digite el precio de compra para el producto");

			if (precioCompra <= 0) {
				System.out.println("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
			}
		} while (precioCompra <= 0);

		do {
			precioVenta = capturarNumeroReal("Digite el precio de compra para el producto");

			if (precioVenta <= 0) {
				System.out.println("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
				continue;
			}

			if (precioVenta <= precioCompra) {
				System.out.println("MENSAJE: El precio de ventana no puede ser menor o igual al precio de compra.");
				precioVenta = 0;
			}
		} while (precioVenta <= 0);

		do {
			cantidad = capturarNumeroEntero("Digite la cantidad para el producto");

			if (cantidad <= 0) {
				System.out.println("MENSAJE: Debe escribir una cantidad positiva.");
			}
		} while (cantidad <= 0);

		do {
			cantidadMinimaStock = capturarNumeroEntero("Digite la cantidad mínima de stock para el producto");

			if (cantidadMinimaStock <= 0) {
				System.out.println("MENSAJE: Debe escribir una cantidad mínima de stock positiva.");
			}
		} while (cantidadMinimaStock <= 0);

		do {
			System.out.println("Listado de Proveedores Disponibles");
			for (Proveedor proveedor : proveedores) {
				System.out.printf("%d. %s\n", proveedor.getId(), proveedor.getNombre());
			}
			idProveedor = capturarNumeroEntero("Digite el ID del proveedor: ");

			if (buscarProveedorPorId(idProveedor) != null) {
				break;
			} else {
				System.out.printf("MENSAJE: No existe un proveedor con el %d especificado.\n", idProveedor);
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
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
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
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
				continue;
			}

			producto = buscarProductoPorId(productos, id);

			if (producto != null) {
				System.out.println("MENSAJE: Ya existe un producto con el ID especificado");
				id = 0;
			}
		} while (id <= 0);

		nombre = capturarCadenaCaracteres("Digite el nombre para el nuevo producto");
		descripcion = capturarCadenaCaracteres("Digite la descripción del nuevo producto");

		do {
			precioCompra = capturarNumeroReal("Digite el precio de compra para el nuevo producto");

			if (precioCompra <= 0) {
				System.out.println("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
			}
		} while (precioCompra <= 0);

		do {
			precioVenta = capturarNumeroReal("Digite el precio de compra para el nuevo producto");

			if (precioVenta <= 0) {
				System.out.println("MENSAJE: El precio de compra no puede ser menor o igual a 0.");
				continue;
			}

			if (precioVenta <= precioCompra) {
				System.out.println("MENSAJE: El precio de ventana no puede ser menor o igual al precio de compra.");
				precioVenta = 0;
			}
		} while (precioVenta <= 0);

		do {
			cantidad = capturarNumeroEntero("Digite la cantidad para el nuevo producto");

			if (cantidad <= 0) {
				System.out.println("MENSAJE: Debe escribir una cantidad positiva.");
			}
		} while (cantidad <= 0);

		do {
			cantidadMinimaStock = capturarNumeroEntero("Digite la cantidad mínima de stock para el nuevo producto");

			if (cantidadMinimaStock <= 0) {
				System.out.println("MENSAJE: Debe escribir una cantidad mínima de stock positiva.");
			}
		} while (cantidadMinimaStock <= 0);

		do {
			System.out.println("Listado de Proveedores Disponibles");
			for (Proveedor proveedor : proveedores) {
				System.out.printf("%d. %s\n", proveedor.getId(), proveedor.getNombre());
			}
			idProveedor = capturarNumeroEntero("Digite el ID del proveedor: ");

			if (buscarProveedorPorId(idProveedor) != null) {
				break;
			} else {
				System.out.printf("MENSAJE: No existe un proveedor con el %d especificado.\n", idProveedor);
			}

		} while (true);

		return new Producto(id, nombre, descripcion, precioCompra, precioVenta, cantidad, cantidadMinimaStock,
				idProveedor);
	}

	private static Producto buscarProductoPorId(List<Producto> productos, int id) {
		return productos.stream().filter(p -> p.getId() == id).findFirst().get();
	}

	private static void eliminarProveedor(List<Proveedor> proveedores, List<Producto> productos) {

		int id;
		Proveedor proveedor;

		do {
			id = capturarNumeroEntero("Digite el número de ID del proveedor");

			if (id <= 0) {
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
				continue;
			}
		} while (id <= 0);

		proveedor = buscarProveedorPorId(id);

		if (proveedor != null) {
			List<Producto> productosProveedor = buscarProductosPorIdProveedor(productos, id);

			if (productosProveedor.isEmpty()) {
				proveedores.remove(proveedor);

				System.out.printf("MENSAJE: Se ha eliminado el proveedor con ID %d.\n", id);
			} else {
				System.out.println("MENSAJE: No puede eliminar este proveedor. Tiene asociado uno o más productos.");
			}
		} else {
			System.out.println("MENSAJE: No existe un proveedor con el número de ID suministrado.");
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
				System.out.println("MENSAJE: El número de teléfono debe ser un valor positivo.");
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
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
				continue;
			}
		} while (id <= 0);

		return buscarProveedorPorId(id);
	}

	private static Proveedor crearProveedor(List<Proveedor> proveedores) {
		System.out.println("--- 1. Crear Proveedor ---");

		int id;
		Proveedor proveedor;
		String nombre;
		int telefono;
		String direccion;

		do {
			id = capturarNumeroEntero("Digite el número ID del nuevo proveedor");

			if (id <= 0) {
				System.out.println("MENSAJE: Debe digitar un número de ID positivo.");
				continue;
			}

			proveedor = buscarProveedorPorId(id);

			if (proveedor != null) {
				System.out.println("MENSAJE: Ya existe un proveedor con el ID especificado");
				id = 0;
			}
		} while (id <= 0);

		nombre = capturarCadenaCaracteres("Digite el nombre para el nuevo proveedor");

		do {
			telefono = capturarNumeroEntero("Digite el número de teléfono del nuevo proveedor");

			if (telefono <= 0) {
				System.out.println("MENSAJE: El número de teléfono debe ser un valor positivo.");
			}
		} while (telefono <= 0);

		direccion = capturarCadenaCaracteres("Digite la dirección del nuevo proveedor");

		return new Proveedor(id, nombre, String.valueOf(telefono), direccion);
	}

	private static Proveedor buscarProveedorPorId(int id) {

		return null;
	}

	private static void eliminarCliente(List<Cliente> clientes, List<Factura> facturas) {
		int numeroCedula;
		String cedula;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente");

			if (numeroCedula <= 0) {
				System.out.println("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continue;
			}
		} while (numeroCedula <= 0);

		cedula = String.valueOf(numeroCedula);

		Cliente cliente = buscarClientePorCedula(clientes, cedula);

		if (cliente != null) {
			Factura factura = buscarFacturaPorCedula(facturas, cedula);

			if (factura == null) {

				clientes.remove(cliente);

				System.out.printf("MENSAJE: Se ha eliminado el cliente con cédula: %s\n", cedula);

			} else {
				System.out.println("No se puede eliminar el cliente. Tiene una o más facturas asignadas.");
			}
		} else {
			System.out.println("MENSAJE: No se encontró ningún cliente con el número de cédula especificado.");
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
				System.out.println("MENSAJE: El número de teléfono debe ser un valor positivo.");
			}
		} while (telefono <= 0);

		String direccion = capturarCadenaCaracteres("Digite la nueva dirección del cliente");
		String correoElectronico;

		while (true) {
			correoElectronico = capturarCadenaCaracteres("Digite el nuevo correo electrónico del cliente");

			if (!correoElectronicoValido(correoElectronico)) {
				System.out.println("MENSAJE: Ha digito un valor que no corresponde con un correo electrónico.");
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
		int numeroCedula;
		String cedula;

		do {
			numeroCedula = capturarNumeroEntero("Digite la cédula del cliente");

			if (numeroCedula <= 0) {
				System.out.println("MENSAJE: La cédula debe ser un número entero positivo.");
				numeroCedula = 0;
				continue;
			}
		} while (numeroCedula <= 0);

		cedula = String.valueOf(numeroCedula);

		return buscarClientePorCedula(clientes, cedula);
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

		while (true) {
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
	}
}
