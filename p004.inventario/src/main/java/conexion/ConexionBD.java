package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelos.Cliente;
import modelos.Factura;
import modelos.Producto;
import modelos.Proveedor;

public class ConexionBD {

	private Connection conectar() {
		final String URL = "jdbc:sqlite::resource:base_datos/inventario_facturacion.db";
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	public Cliente buscarClientePorCedula(String cedula) {
		final String SQL = "SELECT * FROM cliente WHERE cedula = ?";

		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, cedula);

			ResultSet rst = pstmt.executeQuery();

			if (rst.next()) {
				Cliente cliente = new Cliente();
				cliente.setCedula(cedula);
				cliente.setNombres(rst.getString("nombres"));
				cliente.setApellidos(rst.getString("apellidos"));
				cliente.setTelefono(rst.getString("telefono"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setCorreoElectronico(rst.getString("correoe"));

				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void crearCliente(Cliente cliente) {
		final String SQL = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, cliente.getCedula());
			pstmt.setString(2, cliente.getNombres());
			pstmt.setString(3, cliente.getApellidos());
			pstmt.setString(4, cliente.getTelefono());
			pstmt.setString(5, cliente.getDireccion());
			pstmt.setString(6, cliente.getCorreoElectronico());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean actualizarCliente(Cliente cliente) {
		final String SQL = "UPDATE cliente SET nombres = ?, apellidos = ?, telefono = ?, direccion = ?, correoe = ? WHERE cedula = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, cliente.getNombres());
			pstmt.setString(2, cliente.getApellidos());
			pstmt.setString(3, cliente.getTelefono());
			pstmt.setString(4, cliente.getDireccion());
			pstmt.setString(5, cliente.getCorreoElectronico());
			pstmt.setString(6, cliente.getCedula());
			
			int registrosModificados = pstmt.executeUpdate();
			
			return registrosModificados > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<Factura> buscarFacturasCliente(String cedula) {
		List<Factura> facturas = new ArrayList<>();
		
		final String SQL = "SELECT * FROM factura WHERE cliente_cedula = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, cedula);
			
			ResultSet rst = pstmt.executeQuery();
			Factura factura;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rst.next()) {
				factura = new Factura();
				
				factura.setId(rst.getInt("id"));
				factura.setFecha(sdf.parse(rst.getString("fecha")));
				factura.setImpuesto(rst.getDouble("impuesto"));
				factura.setTotal(rst.getDouble("valor_total"));
				factura.setCedulaCliente(cedula);
				
				facturas.add(factura);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return facturas;
	}

	public void eliminarClientePorNumeroCedula(String cedula) {
		final String SQL = "DELETE FROM cliente WHERE cedula = ? LIMIT 1";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, cedula);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Proveedor buscarProveedorPorId(Long id) {
		final String SQL = "SELECT * FROM proveedor WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setLong(1, id);
			
			ResultSet rst = pstmt.executeQuery();
			
			if (rst.next()) {
				Proveedor proveedor = new Proveedor();
				proveedor.setId(id);
				proveedor.setNombre(rst.getString("nombre"));
				proveedor.setDireccion(rst.getString("direccion"));
				proveedor.setTelefono(rst.getString("telefono"));
				
				return proveedor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void crearProveedor(Proveedor proveedor) {
		final String SQL = "INSERT INTO proveedor VALUES(DEFAULT, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, proveedor.getNombre());
			pstmt.setString(2, proveedor.getDireccion());
			pstmt.setString(3, proveedor.getTelefono());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizarProveedor(Proveedor proveedor) {
		final String SQL = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, proveedor.getNombre());
			pstmt.setString(2, proveedor.getDireccion());
			pstmt.setString(3, proveedor.getTelefono());
			pstmt.setLong(4, proveedor.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Producto> buscarProductosPorIdProveedor(Long id) {
		final String SQL = "SELECT * FROM producto WHERE proveedor_id = ?";
		List<Producto> productos = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setLong(1, id);
			
			ResultSet rst = pstmt.executeQuery(SQL);
			Producto producto;
			
			while (rst.next()) {
				producto = new Producto();
				producto.setId(rst.getInt("id"));
				producto.setNombre(rst.getString("nombre"));
				producto.setDescripcion(rst.getString("descripcion"));
				producto.setPrecioCompra(rst.getDouble("precio_compra"));
				producto.setPrecioVenta(rst.getDouble("precio_venta"));
				producto.setCantidad(rst.getInt("cantidad"));
				producto.setCantidadMinimaStock(rst.getInt("cantidad_minima_stock"));
				producto.setIdProveedor(id);
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}

	public void eliminarProveedorPorId(long id) {
		final String SQL = "DELETE FROM proveedor WHERE id = ? LIMIT 1";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setLong(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Proveedor> obtenerProveedores() {
		final String SQL = "SELECT * FROM proveedor";
		List<Proveedor> proveedores = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			
			ResultSet rst = pstmt.executeQuery();
			Proveedor proveedor;
			
			while(rst.next()) {
				proveedor = new Proveedor();
				proveedor.setId(rst.getLong("id"));
				proveedor.setNombre(rst.getString("nombre"));
				proveedor.setDireccion(rst.getString("direccion"));
				proveedor.setTelefono(rst.getString("telefono"));
				
				proveedores.add(proveedor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return proveedores;
	}

	public Producto buscarProductoPorId(int id) {
		final String SQL = "SELECT * FROM producto WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			ResultSet rst = pstmt.executeQuery();
			
			if (rst.next()) {
				Producto producto = new Producto();
				producto.setId(id);
				producto.setNombre(rst.getString("nombre"));
				producto.setDescripcion(rst.getString("descripcion"));
				producto.setPrecioCompra(rst.getDouble("precio_compra"));
				producto.setPrecioVenta(rst.getDouble("precio_venta"));
				producto.setCantidad(rst.getInt("cantidad"));
				producto.setCantidadMinimaStock(rst.getInt("cantidad_minima_stock"));
				producto.setIdProveedor(id);
				
				return producto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void crearProducto(Producto producto) {
		final String SQL = "INSERT INTO producto VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, producto.getNombre());
			pstmt.setString(2, producto.getDescripcion());
			pstmt.setDouble(3, producto.getPrecioCompra());
			pstmt.setDouble(4, producto.getPrecioVenta());
			pstmt.setInt(5, producto.getCantidad());
			pstmt.setInt(6, producto.getCantidadMinimaStock());
			pstmt.setLong(7, producto.getIdProveedor());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarProducto(Producto producto) {
		final String SQL = "UPDATE producto SET nombre = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, cantidad = ?, cantidad_minima_stock = ?, proveedor_id = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setString(1, producto.getNombre());
			pstmt.setString(2, producto.getDescripcion());
			pstmt.setDouble(3, producto.getPrecioCompra());
			pstmt.setDouble(4, producto.getPrecioVenta());
			pstmt.setInt(5, producto.getCantidad());
			pstmt.setInt(6, producto.getCantidadMinimaStock());
			pstmt.setLong(7, producto.getIdProveedor());
			pstmt.setInt(8, producto.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean productoEnFactura(int idProducto) {
		final String SQL = "SELECT * FROM factura_producto WHERE producto_id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setInt(1, idProducto);
			
			return pstmt.executeQuery().next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void eliminarProductoPorId(int id) {
		final String SQL = "DELETE FROM producto WHERE id = ? LIMIT 1";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Producto> obtenerProductos() {
		final String SQL = "SELECT * FROM producto";
		List<Producto> productos = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			
			ResultSet rst = pstmt.executeQuery();
			Producto producto;
			
			while (rst.next()) {
				producto = new Producto();
				producto.setId(rst.getInt("id"));
				producto.setNombre(rst.getString("nombre"));
				producto.setDescripcion(rst.getString("descripcion"));
				producto.setPrecioCompra(rst.getDouble("precio_compra"));
				producto.setPrecioVenta(rst.getDouble("precio_venta"));
				producto.setCantidad(rst.getInt("cantidad"));
				producto.setCantidadMinimaStock(rst.getInt("cantidad_minima_stock"));
				producto.setIdProveedor(rst.getLong("proveedor_id"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}

	public void crearFactura(Factura nuevaFactura) {
		final String SQL = "INSERT INTO factura VALUES (DEFAULT, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechaActual = new Date();
			
			pstmt.setString(1, sdf.format(fechaActual));
			pstmt.setString(2, nuevaFactura.getCedulaCliente());
			pstmt.setDouble(3, nuevaFactura.getImpuesto());
			pstmt.setDouble(4, nuevaFactura.getTotal());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Factura buscarFacturaPorId(int id) {
		final String SQL = "SELECT * FROM factura WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conectar().prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			ResultSet rst = pstmt.executeQuery();
			
			if (rst.next()) {
				Factura factura = new Factura();
				factura.setId(id);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				factura.setFecha(sdf.parse(rst.getString("fecha")));
				factura.setCedulaCliente(rst.getString("cedula_cliente"));
				factura.setImpuesto(rst.getDouble("impuesto"));
				factura.setTotal(rst.getDouble("valor_total"));
				
				return factura;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}











