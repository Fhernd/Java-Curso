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

    /**
     * Actualiza los datos de un cliente.
     * @param cliente Cliente a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        try {
            final String SQL = "UPDATE cliente SET documento = ?, nombres = ?, apellidos = ?, correo = ?, tipo_documento_id = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, cliente.getDocumento());
            sentencia.setString(2, cliente.getNombres());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setString(4, cliente.getCorreo());
            sentencia.setInt(5, cliente.getTipoDocumentoId());
            sentencia.setInt(6, cliente.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Elimina un cliente a partir de su documento.
     * @param documento Documento del cliente.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarClientePorDocumento(String documento) {
        try {
            final String SQL = "DELETE FROM cliente WHERE documento = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, documento);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Crea un nuevo usuario.
     * @param usuario Usuario a crear.
     * @return Usuario Usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        try {
            final String SQL = "INSERT INTO usuario (correo, clave, empleado_id) VALUES (?, ?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, usuario.getCorreo());
            sentencia.setString(2, usuario.getClave());
            sentencia.setInt(3, usuario.getEmpleadoId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                usuario.setId(resultado.getInt(1));

                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Inicia sesión a partir de las credenciales de un usuario.
     * @param correo Correo del usuario.
     * @param clave Clave del usuario.
     *@return Usuario Usuario encontrado.
     */
    public Usuario iniciarSesion(String correo, String clave) {
        try {
            final String SQL = "SELECT * FROM usuario WHERE correo = ? AND clave = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, correo);
            sentencia.setString(2, clave);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setCorreo(correo);
                usuario.setClave(clave);
                usuario.setEmpleadoId(resultado.getInt("empleado_id"));

                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Elimina un usuario a partir de su correo.
     * @param correo Correo del usuario.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarUsuarioPorCorreo(String correo) {
        try {
            final String SQL = "DELETE FROM usuario WHERE correo = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, correo);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Crear un nuevo empleado.
     * @param empleado Empleado a crear.
     * @return Empleado Empleado creado.
     */
    public Empleado crearEmpleado(Empleado empleado) {
        try {
            final String SQL = "INSERT INTO empleado (nombres, apellidos, sueldo, rol_id) VALUES (?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, empleado.getNombres());
            sentencia.setString(2, empleado.getApellidos());
            sentencia.setDouble(3, empleado.getSueldo());
            sentencia.setInt(4, empleado.getRolId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                empleado.setId(resultado.getInt(1));

                return empleado;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Modifica los datos de un empleado.
     * @param empleado Empleado a modificar.
     * @return true si se modificó correctamente, false en caso contrario.
     */
    public boolean modificarEmpleado(Empleado empleado) {
        try {
            final String SQL = "UPDATE empleado SET nombres = ?, apellidos = ?, sueldo = ?, rol_id = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, empleado.getNombres());
            sentencia.setString(2, empleado.getApellidos());
            sentencia.setDouble(3, empleado.getSueldo());
            sentencia.setInt(4, empleado.getRolId());
            sentencia.setInt(5, empleado.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Recupera todos los empleados.
     * @return List<Empleado> Lista de empleados.
     */
    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM empleado";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("id"));
                empleado.setNombres(resultado.getString("nombres"));
                empleado.setApellidos(resultado.getString("apellidos"));
                empleado.setSueldo(resultado.getDouble("sueldo"));
                empleado.setRolId(resultado.getInt("rol_id"));

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return empleados;
    }

    /**
     * Elimina un empleado a partir de su ID.
     * @param id ID del empleado.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarEmpleadoPorId(int id) {
        try {
            final String SQL = "DELETE FROM empleado WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }
}
