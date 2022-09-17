package datos;

import modelos.Rol;
import modelos.TipoDocumento;

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
     *
     * @return Lista de roles.
     */
    public List<Rol> obtenerRoles() {
        List<Rol> roles = new ArrayList<>();
        try {
            final String SQL = "SELECT * FROM rol";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            ResultSet resultado = sentencia.executeQuery();


            while (resultado.next()) {
                Rol rol = new Rol();
                rol.setId(resultado.getInt("id"));
                rol.setNombre(resultado.getString("nombre"));
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return roles;
    }

    /**
     * Obtiene todos los tipos de documentos.
     */
    public List<TipoDocumento> obtenerTiposDocumentos() {
        List<TipoDocumento> tiposDocumentos = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM tipo_documento";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(resultado.getInt("id"));
                tipoDocumento.setNombre(resultado.getString("nombre"));
                tiposDocumentos.add(tipoDocumento);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return tiposDocumentos;
    }
}
