package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GestionInventario {
	
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

}
