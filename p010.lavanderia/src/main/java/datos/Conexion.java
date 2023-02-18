package datos;

import java.sql.*;

public class Conexion {

    public static Connection getConnection() throws SQLException {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/" + System.getenv("GESTOR_LAVANDERIA_JDBC_DATABASE") + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        final String JDBC_USER = System.getenv("GESTOR_LAVANDERIA_JDBC_USER");
        System.out.println("JDBC_URL: " + JDBC_URL);
        System.out.println("JDBC_USER: " + JDBC_USER);
        String JDBC_PASSWORD = "";
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void close(ResultSet rs) throws java.sql.SQLException {
        rs.close();
    }

    public static void close(PreparedStatement stmt) throws SQLException {
        stmt.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
