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

import conexion.ConexionBD;

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
	
	private ConexionBD conexionBD;

	public GestionInventario() {
		clientes = new ArrayList<>();
		proveedores = new ArrayList<>();
		productos = new ArrayList<>();
		facturas = new ArrayList<>();
		
		conexionBD = new ConexionBD();
	}

	public Cliente buscarClientePorCedula(String cedula) {
		return conexionBD.buscarClientePorCedula(cedula);
	}

	public void crearCliente(Cliente cliente) {
		conexionBD.crearCliente(cliente);
	}

	public void actualizarCliente(Cliente cliente) {
		conexionBD.actualizarCliente(cliente);
	}

	public List<Factura> buscarFacturasCliente(String cedula) {
		return conexionBD.buscarFacturasCliente(cedula);
	}

	public void eliminarClientePorNumeroCedula(String cedula) {
		conexionBD.eliminarClientePorNumeroCedula(cedula);
	}

	public Proveedor buscarProveedorPorId(Long id) {
		return conexionBD.buscarProveedorPorId(id);
	}

	public void crearProveedor(Proveedor proveedor) {
		conexionBD.crearProveedor(proveedor);
	}

	public void actualizarProveedor(Proveedor proveedor) {
		conexionBD.actualizarProveedor(proveedor);
	}

	public List<Producto> buscarProductosPorIdProveedor(Long id) {
		return conexionBD.buscarProductosPorIdProveedor(id);
	}

	public void eliminarProveedorPorId(long id) {
		conexionBD.eliminarProveedorPorId(id);
	}

	public Proveedor[] obtenerProveedores() {
		List<Proveedor> proveedores = conexionBD.obtenerProveedores();
		
		Proveedor[] proveedoresCopia = new Proveedor[proveedores.size()];
		proveedores.toArray(proveedoresCopia);

		return proveedoresCopia;
	}

	public Producto buscarProductoPorId(int id) {
		return conexionBD.buscarProductoPorId(id);
	}

	public void crearProducto(Producto producto) {
		conexionBD.crearProducto(producto);
	}

	public void actualizarProducto(Producto producto) {
		conexionBD.actualizarProducto(producto);
	}

	public boolean productoEnFactura(int idProducto) {
		return conexionBD.productoEnFactura(idProducto);
	}

	public void eliminarProductoPorId(int id) {
		conexionBD.eliminarProductoPorId(id);
	}

	public Producto[] obtenerProductos() {
		List<Producto> productos = conexionBD.obtenerProductos();
		
		Producto[] productosCopia = new Producto[productos.size()];
		productos.toArray(productosCopia);

		return productosCopia;
	}

	public void crearFactura(Factura nuevaFactura) {
		conexionBD.crearFactura(nuevaFactura);
	}

	public Factura buscarFacturaPorId(int id) {
		return conexionBD.buscarFacturaPorId(id);
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
