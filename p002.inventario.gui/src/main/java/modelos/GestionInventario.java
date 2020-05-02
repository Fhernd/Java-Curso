package modelos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

public class GestionInventario {

	private static final String ARCHIVO_CSV_CLIENTES = "clientes.csv";
	private static final String ARCHIVO_CSV_PROVEEDORES = "proveedores.csv";
	private static final String ARCHIVO_CSV_PRODUCTOS = "productos.csv";
	private static final String ARCHIVO_CSV_FACTURAS = "facturas.csv";

	private static final char SEPARADOR = ';';

	private List<Cliente> clientes;
	private List<Proveedor> proveedores;
	private List<Producto> productos;
	private List<Factura> facturas;

	public GestionInventario() {
		clientes = new ArrayList<>();
		proveedores = new ArrayList<>();
		productos = new ArrayList<>();
		facturas = new ArrayList<>();
	}

	public Cliente buscarClientePorCedula(String cedula) {
		return clientes.stream().filter(c -> c.getCedula().equals(cedula)).findFirst().orElse(null);
	}

	public void crearCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void actualizarCliente(Cliente cliente) {
		Cliente clienteActualizar = buscarClientePorCedula(cliente.getCedula());

		clienteActualizar.setNombres(cliente.getNombres());
		clienteActualizar.setApellidos(cliente.getApellidos());
		clienteActualizar.setTelefono(cliente.getTelefono());
		clienteActualizar.setDireccion(cliente.getDireccion());
		clienteActualizar.setCorreoElectronico(cliente.getCorreoElectronico());
	}

	public List<Factura> buscarFacturasCliente(String cedula) {
		return facturas.stream().filter(f -> f.getCedulaCliente().equals(cedula)).collect(Collectors.toList());
	}

	public void eliminarClientePorNumeroCedula(String cedula) {
		Cliente cliente = buscarClientePorCedula(cedula);

		clientes.remove(cliente);
	}

	public Proveedor buscarProveedorPorId(Long id) {
		return proveedores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	public void crearProveedor(Proveedor proveedor) {
		proveedores.add(proveedor);
	}

	public void actualizarProveedor(Proveedor proveedor) {
		Proveedor proveedorExistente = buscarProveedorPorId(proveedor.getId());

		proveedorExistente.setNombre(proveedor.getNombre());
		proveedorExistente.setDireccion(proveedor.getDireccion());
		proveedorExistente.setTelefono(proveedor.getTelefono());
	}

	public List<Producto> buscarProductosPorIdProveedor(Long numero) {
		return productos.stream().filter(p -> p.getIdProveedor() == numero).collect(Collectors.toList());
	}

	public void eliminarProveedorPorId(long id) {
		Proveedor proveedor = buscarProveedorPorId(id);
		proveedores.remove(proveedor);
	}

	public Proveedor[] obtenerProveedores() {
		Proveedor[] proveedoresCopia = new Proveedor[proveedores.size()];
		proveedores.toArray(proveedoresCopia);

		return proveedoresCopia;
	}

	public Producto buscarProductoPorId(int id) {
		return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	public void crearProducto(Producto producto) {
		productos.add(producto);
	}

	public void actualizarProducto(Producto producto) {
		Producto productoActualizado = buscarProductoPorId(producto.getId());

		productoActualizado.setNombre(producto.getNombre());
		productoActualizado.setDescripcion(producto.getDescripcion());
		productoActualizado.setPrecioCompra(producto.getPrecioCompra());
		productoActualizado.setPrecioVenta(producto.getPrecioVenta());
		productoActualizado.setCantidad(producto.getCantidad());
		productoActualizado.setCantidadMinimaStock(producto.getCantidadMinimaStock());
		productoActualizado.setIdProveedor(producto.getIdProveedor());
	}

	public boolean productoEnFactura(int idProducto) {
		return facturas.stream().anyMatch(f -> Arrays.asList(f.getIdsProductos()).contains(idProducto));
	}

	public void eliminarProductoPorId(int id) {
		Producto producto = buscarProductoPorId(id);
		productos.remove(producto);
	}

	public Producto[] obtenerProductos() {
		Producto[] productosCopia = new Producto[productos.size()];
		productos.toArray(productosCopia);

		return productosCopia;
	}

	public void crearFactura(Factura nuevaFactura) {
		facturas.add(nuevaFactura);
	}

	public Factura buscarFacturaPorId(int id) {
		return facturas.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
	}

	public void guardarDatosInventario(File carpetaSeleccionada) {

		try {
			BufferedWriter writer = Files.newBufferedWriter(
					Paths.get(carpetaSeleccionada.getAbsolutePath() + File.separator + ARCHIVO_CSV_CLIENTES));

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
				BufferedWriter writer = Files.newBufferedWriter(Paths
						.get(carpetaSeleccionada.getAbsolutePath() + File.separator + ARCHIVO_CSV_PROVEEDORES));

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
				BufferedWriter writer = Files.newBufferedWriter(
						Paths.get(carpetaSeleccionada.getAbsolutePath() + File.separator + ARCHIVO_CSV_PRODUCTOS));

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
				BufferedWriter writer = Files.newBufferedWriter(
						Paths.get(carpetaSeleccionada.getAbsolutePath() + File.separator + ARCHIVO_CSV_FACTURAS));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT
								.withHeader("id", "fecha", "cedulaCliente", "impuesto", "total", "idsProductos")
								.withDelimiter(SEPARADOR));

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				for (Factura f : facturas) {
					csvPrinter.printRecord(f.getId(), simpleDateFormat.format(f.getFecha()), f.getCedulaCliente(),
							f.getImpuesto(), f.getTotal(), StringUtils.join(f.getIdsProductos(), ","));
				}

				csvPrinter.flush();
				csvPrinter.close();

			} catch (IOException e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}
	}
}
