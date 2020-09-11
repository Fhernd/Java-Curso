package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelos.Cliente;
import modelos.Factura;
import modelos.Producto;
import modelos.Proveedor;

/**
 * Represente la conexión a la base de datos y los métodos de CRUD (Create, Read, Update y Delete), es 
 * decir manipulación de datos.
 * 
 * 
 * @author John Ortiz Ordoñez.
 *
 */
public class ConexionBD {

	/**
	 * Establece la conexión con la base de datos Apache Derby.
	 * 
	 * @return Conexión a la base de datos.
	 */
	private Connection conectar() {
		final String URL = "jdbc:derby://localhost:3307/inventario";
		final String USUARIO = "gestor";
		final String CLAVE = "_";
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	/**
	 * Busca un cliente a partir de su número de cédula.
	 * 
	 * @param cedula Número de cédula.
	 * @return Cliente o null (cuando existe un cliente con en número de cédula especificado).
	 */
	public Cliente buscarClientePorCedula(String cedula) {
		final String SQL = "SELECT * FROM cliente WHERE cedula = ?";
		
		Connection conexion = conectar();

		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
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
				
				conexion.close();

				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Crea un nuevo cliente en la base de datos.
	 * 
	 * @param cliente Cliente a crear.
	 */
	public void crearCliente(Cliente cliente) {
		final String SQL = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?)";
		
		Connection conexion = conectar();

		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, cliente.getCedula());
			pstmt.setString(2, cliente.getNombres());
			pstmt.setString(3, cliente.getApellidos());
			pstmt.setString(4, cliente.getTelefono());
			pstmt.setString(5, cliente.getDireccion());
			pstmt.setString(6, cliente.getCorreoElectronico());

			pstmt.executeUpdate();
			
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza los datos de un cliente.
	 * 
	 * @param cliente Datos del cliente a actualizar.
	 * @return true si el cliente se actualizó de forma satisfactoria, false en caso contrario.
	 */
	public boolean actualizarCliente(Cliente cliente) {
		final String SQL = "UPDATE cliente SET nombres = ?, apellidos = ?, telefono = ?, direccion = ?, correoe = ? WHERE cedula = ?";
		
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, cliente.getNombres());
			pstmt.setString(2, cliente.getApellidos());
			pstmt.setString(3, cliente.getTelefono());
			pstmt.setString(4, cliente.getDireccion());
			pstmt.setString(5, cliente.getCorreoElectronico());
			pstmt.setString(6, cliente.getCedula());
			
			int registrosModificados = pstmt.executeUpdate();
			
			conexion.close();
			
			return registrosModificados > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Busca las facturas de un cliente a partir de su número de cédula.
	 * 
	 * @param cedula Número de cédula.
	 * @return Listado de facturas del cliente.
	 */
	public List<Factura> buscarFacturasCliente(String cedula) {
		List<Factura> facturas = new ArrayList<>();
		
		final String SQL = "SELECT * FROM factura WHERE cliente_cedula = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return facturas;
	}

	/**
	 * Eliminar un cliente a partir de su número de cédula.
	 * 
	 * @param cedula Número de cédula.
	 */
	public void eliminarClientePorNumeroCedula(String cedula) {
		final String SQL = "DELETE FROM cliente WHERE cedula = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, cedula);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Busca un proveedor a partir de su número de ID.
	 * 
	 * @param id ID del proveedor.
	 * @return Proveedor o null (cuando no existe un proveedor con el ID especificado).
	 */
	public Proveedor buscarProveedorPorId(Long id) {
		final String SQL = "SELECT * FROM proveedor WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setLong(1, id);
			
			ResultSet rst = pstmt.executeQuery();
			
			if (rst.next()) {
				Proveedor proveedor = new Proveedor();
				proveedor.setId(id);
				proveedor.setNombre(rst.getString("nombre"));
				proveedor.setDireccion(rst.getString("direccion"));
				proveedor.setTelefono(rst.getString("telefono"));
				
				conexion.close();
				
				return proveedor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Crea un nuevo proveedor en la base de datos.
	 * 
	 * @param proveedor Proveedor a crear.
	 */
	public void crearProveedor(Proveedor proveedor) {
		final String SQL = "INSERT INTO proveedor (id, nombre, direccion, telefono) VALUES(?, ?, ?, ?)";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setLong(1, proveedor.getId());
			pstmt.setString(2, proveedor.getNombre());
			pstmt.setString(3, proveedor.getDireccion());
			pstmt.setString(4, proveedor.getTelefono());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza los datos de un proveedor.
	 * 
	 * @param proveedor Datos del proveedor a actualizar.
	 */
	public void actualizarProveedor(Proveedor proveedor) {
		final String SQL = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ? WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, proveedor.getNombre());
			pstmt.setString(2, proveedor.getDireccion());
			pstmt.setString(3, proveedor.getTelefono());
			pstmt.setLong(4, proveedor.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Busca todos los productos de un proveedor a partir de su ID.
	 * 
	 * @param id ID del proveedor.
	 * @return Listado de productos.
	 */
	public List<Producto> buscarProductosPorIdProveedor(Long id) {
		final String SQL = "SELECT * FROM producto WHERE proveedor_id = ?";
		List<Producto> productos = new ArrayList<>();
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setLong(1, id);
			
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
				producto.setIdProveedor(id);
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return productos;
	}

	/**
	 * Eliminar un proveedor a partir de su ID.
	 * 
	 * @param id ID del proveedor.
	 */
	public void eliminarProveedorPorId(long id) {
		final String SQL = "DELETE FROM proveedor WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setLong(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene el listado de proveedores desde la base de datos.
	 * 
	 * @return Listado de proveedores.
	 */
	public List<Proveedor> obtenerProveedores() {
		final String SQL = "SELECT * FROM proveedor";
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return proveedores;
	}

	/**
	 * Busca un producto a partir de su ID.
	 * @param id ID del producto.
	 * @return Producto null (si no hay un producto con el ID especificado).
	 */
	public Producto buscarProductoPorId(int id) {
		final String SQL = "SELECT * FROM producto WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
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
				producto.setIdProveedor(rst.getLong("proveedor_id"));
				
				conexion.close();
				
				return producto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Crea un nuevo producto.
	 * 
	 * @param producto Datos del producto a crear.
	 */
	public void crearProducto(Producto producto) {
		final String SQL = "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setInt(1, producto.getId());
			pstmt.setString(2, producto.getNombre());
			pstmt.setString(3, producto.getDescripcion());
			pstmt.setDouble(4, producto.getPrecioCompra());
			pstmt.setDouble(5, producto.getPrecioVenta());
			pstmt.setInt(6, producto.getCantidad());
			pstmt.setInt(7, producto.getCantidadMinimaStock());
			pstmt.setLong(8, producto.getIdProveedor());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza un producto a partir de nuevos datos.
	 * 
	 * @param producto Datos del producto.
	 */
	public void actualizarProducto(Producto producto) {
		final String SQL = "UPDATE producto SET nombre = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, cantidad = ?, cantidad_minima_stock = ?, proveedor_id = ? WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, producto.getNombre());
			pstmt.setString(2, producto.getDescripcion());
			pstmt.setDouble(3, producto.getPrecioCompra());
			pstmt.setDouble(4, producto.getPrecioVenta());
			pstmt.setInt(5, producto.getCantidad());
			pstmt.setInt(6, producto.getCantidadMinimaStock());
			pstmt.setLong(7, producto.getIdProveedor());
			pstmt.setInt(8, producto.getId());
			
			pstmt.executeUpdate();
			
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba si un producto está presente al menos en una factura.
	 * 
	 * @param idProducto ID del producto a buscar sobre las facturas.
	 * 
	 * @return true si existe el ID de producto en alguna factura, false en caso contrario.
	 */
	public boolean productoEnFactura(int idProducto) {
		final String SQL = "SELECT * FROM factura_producto WHERE producto_id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setInt(1, idProducto);
			
			boolean resultado = pstmt.executeQuery().next();
			
			conexion.close();
			
			return resultado;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Eliminar un producto a partir de su ID.
	 * 
	 * @param id ID del producto.
	 */
	public void eliminarProductoPorId(int id) {
		final String SQL = "DELETE FROM producto WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene el listado de productos almacenados en la base de datos.
	 * 
	 * @return Listado de productos.
	 */
	public List<Producto> obtenerProductos() {
		final String SQL = "SELECT * FROM producto";
		List<Producto> productos = new ArrayList<>();
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return productos;
	}

	/**
	 * Crea una nueva factura en la base de datos.
	 * 
	 * @param nuevaFactura Datos de la nueva factura a crear.
	 */
	public void crearFactura(Factura nuevaFactura) {
		String sql = "INSERT INTO factura (fecha, cliente_cedula, impuesto, valor_total) VALUES (?, ?, ?, ?)";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechaActual = new Date();
			
			pstmt.setString(1, sdf.format(fechaActual));
			pstmt.setString(2, nuevaFactura.getCedulaCliente());
			pstmt.setDouble(3, nuevaFactura.getImpuesto());
			pstmt.setDouble(4, nuevaFactura.getTotal());
			
			pstmt.executeUpdate();
			
			ResultSet rst = pstmt.getGeneratedKeys();
			
			if (rst.next()) {
				nuevaFactura.setId(rst.getInt(1));
				
				sql = "INSERT INTO factura_producto VALUES (?, ?, ?)";
				
				pstmt = conexion.prepareStatement(sql);
				
				for (int idProducto : nuevaFactura.getIdsProductos()) {
					pstmt.setInt(1, nuevaFactura.getId());
					pstmt.setInt(2, idProducto);
					pstmt.setInt(3, 1);
					
					pstmt.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Busca una factura a partir de su ID.
	 * 
	 * @param id ID de la factura.
	 * @return Factura o null (cuando no existe una factura con el ID especificado).
	 */
	public Factura buscarFacturaPorId(int id) {
		String sql = "SELECT * FROM factura WHERE id = ?";
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rst = pstmt.executeQuery();
			
			if (rst.next()) {
				Factura factura = new Factura();
				factura.setId(id);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				factura.setFecha(sdf.parse(rst.getString("fecha")));
				factura.setCedulaCliente(rst.getString("cliente_cedula"));
				factura.setImpuesto(rst.getDouble("impuesto"));
				factura.setTotal(rst.getDouble("valor_total"));
				
				sql = "SELECT * FROM factura_producto WHERE factura_id = ?";
				pstmt = conexion.prepareStatement(sql);
				pstmt.setInt(1, id);
				
				ResultSet resultado = pstmt.executeQuery();
				
				while(resultado.next()) {
					factura.agregarIdProducto(resultado.getInt("producto_id"));
				}
				
				conexion.close();
				
				return factura;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
