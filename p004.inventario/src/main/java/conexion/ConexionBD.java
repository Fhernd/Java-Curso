package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelos.Cliente;

public class ConexionBD {
	private Connection conectar() {
		final String URL= "jdbc:sqlite::resource:base_datos/inventario_facturacion.db";
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL);
		} catch(SQLException e) {
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
}
