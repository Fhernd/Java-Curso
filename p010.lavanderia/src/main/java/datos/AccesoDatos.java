package datos;

import modelos.Direccion;
import modelos.Rol;
import modelos.TipoAtencion;
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
     * @return Lista de tipos de documentos.
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

    /**
     * Obtiene todos los tipos de atencion.
     * @return Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencion() {
        List<TipoAtencion> tiposAtencion = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM tipo_atencion";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                TipoAtencion tipoAtencion = new TipoAtencion();
                tipoAtencion.setId(resultado.getInt("id"));
                tipoAtencion.setNombre(resultado.getString("nombre"));
                tiposAtencion.add(tipoAtencion);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return tiposAtencion;
    }

    /**
     * Crea una nueva dirección.
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        try {
            final String SQL = "INSERT INTO direccion (descripcion, client_id) VALUES (?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, direccion.getDescripcion());
            sentencia.setInt(2, direccion.getClienteId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                direccion.setId(resultado.getInt(1));

                return direccion;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }
}
