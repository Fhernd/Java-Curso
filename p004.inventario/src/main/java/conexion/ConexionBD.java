package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {
	public Connection conectar() {
		final String URL= "jdbc:sqlite::resource:base_datos/inventario_facturacion.db";
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	public static void main(String[] args) {
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.conectar();
	}
}
