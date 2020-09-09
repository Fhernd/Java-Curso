package modelos;

import java.util.List;

import conexion.ConexionBD;

public class GestionInventario {
	
	private ConexionBD conexionBD;

	public GestionInventario() {
		
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
}
