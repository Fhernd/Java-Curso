package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelos.Cliente;

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
}








