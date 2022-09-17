package datos;

import modelos.*;

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

    /**
     * Obtener una dirección a partir del ID del cliente.
     * @param clienteId ID del cliente.
     * @return Direccion Dirección encontrada.
     */
    public Direccion obtenerDireccionPorClientId(int clienteId) {
        try {
            final String SQL = "SELECT * FROM direccion WHERE client_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, clienteId);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                Direccion direccion = new Direccion();
                direccion.setId(resultado.getInt("id"));
                direccion.setDescripcion(resultado.getString("descripcion"));
                direccion.setClienteId(clienteId);

                return direccion;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Obtener todas las direcciones de un cliente a partir de su ID.
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClientId(int clienteId) {
        List<Direccion> direcciones = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM direccion WHERE client_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, clienteId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Direccion direccion = new Direccion();
                direccion.setId(resultado.getInt("id"));
                direccion.setDescripcion(resultado.getString("descripcion"));
                direccion.setClienteId(clienteId);

                direcciones.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return direcciones;
    }

    /**
     * Crea un nuevo cliente.
     * @param cliente Cliente a crear.
     * @return Cliente Cliente creado.
     */
    public Cliente crearCliente(Cliente cliente) {
        try {
            final String SQL = "INSERT INTO cliente (documento, nombres, apellidos, correo, tipo_documento_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, cliente.getDocumento());
            sentencia.setString(2, cliente.getNombres());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setString(4, cliente.getCorreo());
            sentencia.setInt(5, cliente.getTipoDocumentoId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                cliente.setId(resultado.getInt(1));

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Obtiene un cliente a partir de su ID.
     * @param clienteId ID del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorId(int clienteId) {
        try {
            final String SQL = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, clienteId);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(clienteId);
                cliente.setDocumento(resultado.getString("documento"));
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setTipoDocumentoId(resultado.getInt("tipo_documento_id"));

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Obtiene un cliente a partir de su documento.
     * @param documento Documento del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorDocumento(String documento) {
        try {
            final String SQL = "SELECT * FROM cliente WHERE documento = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, documento);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setDocumento(documento);
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setTipoDocumentoId(resultado.getInt("tipo_documento_id"));

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }
}
