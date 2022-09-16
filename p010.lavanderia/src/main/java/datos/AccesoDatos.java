package datos;

import modelos.Rol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatos {

    private Conexion conexion;

    public AccesoDatos() {
        conexion = new Conexion();
    }

    /**
     * Obtiene todos los roles.
     * @return Lista de roles.
     */
    public List<Rol> obtenerRoles() {
        try {
            final String SQL = "SELECT * FROM rol";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Rol> roles = new ArrayList<>();

            while (resultado.next()) {
                Rol rol = new Rol();
                rol.setId(resultado.getInt("id"));
                rol.setNombre(resultado.getString("nombre"));
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
