package datos;

import modelos.*;
import utilidades.Utilidad;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
     *
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
     *
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
     *
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        try {
            final String SQL = "INSERT INTO direccion (descripcion, cliente_id) VALUES (?, ?)";
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
     *
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
     *
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClienteId(int clienteId) {
        List<Direccion> direcciones = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM direccion WHERE cliente_id = ?";
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
     *
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
     *
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
     *
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
     *
     * @param cliente Cliente a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        try {
            final String SQL = "UPDATE cliente SET nombres = ?, apellidos = ?, correo = ?, tipo_documento_id = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, cliente.getNombres());
            sentencia.setString(2, cliente.getApellidos());
            sentencia.setString(3, cliente.getCorreo());
            sentencia.setInt(4, cliente.getTipoDocumentoId());
            sentencia.setInt(5, cliente.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Elimina un cliente a partir de su documento.
     *
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
     *
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
     *
     * @param correo Correo del usuario.
     * @param clave  Clave del usuario.
     * @return Usuario Usuario encontrado.
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
     *
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
     *
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
     *
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
     *
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
     *
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

    /**
     * Crea una nueva atención.
     *
     * @param atencion Atención a crear.
     * @return Atencion Atención creada.
     */
    public Atencion crearAtencion(Atencion atencion) {
        try {
            final String SQL = "INSERT INTO atencion (cantidad, precio, tipo_atencion_id, servicio_id) VALUES (?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, atencion.getCantidad());
            sentencia.setDouble(2, atencion.getPrecio());
            sentencia.setInt(3, atencion.getTipoAtencionId());
            sentencia.setInt(4, atencion.getServicioId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                atencion.setId(resultado.getInt(1));

                return atencion;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Lista atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return List<Atencion> Lista de atenciones.
     */
    public List<Atencion> obtenerAtencionesPorServicioId(int servicioId) {
        List<Atencion> atenciones = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM atencion WHERE servicio_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, servicioId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Atencion atencion = new Atencion();
                atencion.setId(resultado.getInt("id"));
                atencion.setCantidad(resultado.getInt("cantidad"));
                atencion.setPrecio(resultado.getDouble("precio"));
                atencion.setTipoAtencionId(resultado.getInt("tipo_atencion_id"));
                atencion.setServicioId(resultado.getInt("servicio_id"));

                atenciones.add(atencion);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return atenciones;
    }

    /**
     * Listar atenciones a partir del ID de un tipo de atención.
     *
     * @param tipoAtencionId ID del tipo de atención.
     * @return List<Atencion> Lista de atenciones.
     */
    public List<Atencion> obtenerAtencionesPorTipoAtencionId(int tipoAtencionId) {
        List<Atencion> atenciones = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM atencion WHERE tipo_atencion_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, tipoAtencionId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Atencion atencion = new Atencion();
                atencion.setId(resultado.getInt("id"));
                atencion.setCantidad(resultado.getInt("cantidad"));
                atencion.setPrecio(resultado.getDouble("precio"));
                atencion.setTipoAtencionId(resultado.getInt("tipo_atencion_id"));
                atencion.setServicioId(resultado.getInt("servicio_id"));

                atenciones.add(atencion);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return atenciones;
    }

    /**
     * Crea un nuevo servicio.
     *
     * @param servicio Servicio a crear.
     * @return Servicio Servicio creado.
     */
    public Servicio crearServicio(Servicio servicio) {
        try {
            final String SQL = "INSERT INTO servicio (descripcion, fecha_hora_recepcion, fecha_hora_entrega, empleado_id, cliente_id, direccion_id) VALUES (?, DEFAULT, ?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, servicio.getDescripcion());
            sentencia.setString(2, Utilidad.fechaToString(servicio.getFechaHoraEntrega(), "yyyy-MM-dd HH:mm:ss"));
            sentencia.setInt(3, servicio.getEmpleadoId());
            sentencia.setInt(4, servicio.getClienteId());
            sentencia.setInt(5, servicio.getDireccionId());

            sentencia.executeUpdate();

            ResultSet resultado = sentencia.getGeneratedKeys();

            if (resultado.next()) {
                servicio.setId(resultado.getInt(1));

                return servicio;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Lista servicios a partir del ID de un cliente.
     *
     * @param clienteId ID del cliente.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorClienteId(int clienteId) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM servicio WHERE cliente_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, clienteId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(resultado.getInt("id"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                servicio.setFechaHoraRecepcion(resultado.getTimestamp("fecha_hora_recepcion").toLocalDateTime());
                servicio.setFechaHoraEntrega(resultado.getTimestamp("fecha_hora_entrega").toLocalDateTime());
                servicio.setEmpleadoId(resultado.getInt("empleado_id"));
                servicio.setClienteId(clienteId);
                servicio.setDireccionId(resultado.getInt("direccion_id"));

                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return servicios;
    }

    /**
     * Lista servicios a partir del ID de un empleado.
     *
     * @param empleadoId ID del empleado.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorEmpleadoId(int empleadoId) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM servicio WHERE empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, empleadoId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(resultado.getInt("id"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                servicio.setFechaHoraRecepcion(resultado.getTimestamp("fecha_hora_recepcion").toLocalDateTime());
                servicio.setFechaHoraEntrega(resultado.getTimestamp("fecha_hora_entrega").toLocalDateTime());
                servicio.setEmpleadoId(empleadoId);
                servicio.setClienteId(resultado.getInt("cliente_id"));
                servicio.setDireccionId(resultado.getInt("direccion_id"));

                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return servicios;
    }

    /**
     * Actualiza la fecha de entrega de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return boolean Verdadero si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarFechaEntregaServicio(int servicioId) {
        try {
            final String SQL = "UPDATE servicio SET fecha_hora_entrega = DEFAULT WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, servicioId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Busca un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return Servicio Servicio encontrado.
     */
    public Servicio obtenerServicioPorId(int servicioId) {
        try {
            final String SQL = "SELECT * FROM servicio WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, servicioId);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(servicioId);
                servicio.setDescripcion(resultado.getString("descripcion"));
                servicio.setFechaHoraRecepcion(Utilidad.fechaALocalDateTime(resultado.getString("fecha_hora_recepcion")));
                servicio.setFechaHoraEntrega(Utilidad.fechaALocalDateTime(resultado.getString("fecha_hora_entrega")));
                servicio.setEmpleadoId(resultado.getInt("empleado_id"));
                servicio.setClienteId(resultado.getInt("cliente_id"));
                servicio.setDireccionId(resultado.getInt("direccion_id"));

                return servicio;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Obtener los clientes que más han solicitado servicios.
     *
     * @return List<Cliente> Lista de clientes.
     */
    public List<Cliente> obtenerClientesQueMasHanSolicitadoServicios(int cantidadClientes) {
        List<Cliente> clientes = new ArrayList<>();

        try {
            final String SQL = "SELECT cliente.id, cliente.documento, cliente.nombres, cliente.apellidos, cliente.correo, cliente.tipo_documento_id, COUNT(servicio.id) AS cantidad_servicios FROM cliente INNER JOIN servicio ON cliente.id = servicio.cliente_id GROUP BY cliente.id ORDER BY cantidad_servicios DESC LIMIT ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, cantidadClientes);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setTipoDocumentoId(resultado.getInt("tipo_documento_id"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return clientes;
    }

    /**
     * Obtener los tipos de atención más solicitados.
     *
     * @return List<TipoAtencion> Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencionMasSolicitados() {
        List<TipoAtencion> tiposAtencion = new ArrayList<>();

        try {
            final String SQL = "SELECT tipo_atencion.id, tipo_atencion.nombre, COUNT(servicio.id) AS cantidad_servicios FROM tipo_atencion INNER JOIN servicio ON tipo_atencion.id = servicio.tipo_atencion_id GROUP BY tipo_atencion.id ORDER BY cantidad_servicios DESC LIMIT 10";
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
     * Obtener los 10 empleados que más han atendido servicios.
     *
     * @return List<Empleado> Lista de empleados.
     */
    public List<Empleado> obtenerEmpleadosQueMasHanAtendidoServicios() {
        List<Empleado> empleados = new ArrayList<>();

        try {
            final String SQL = "SELECT empleado.id, empleado.nombres, empleado.apellidos, COUNT(servicio.id) AS cantidad_servicios FROM empleado INNER JOIN servicio ON empleado.id = servicio.empleado_id GROUP BY empleado.id ORDER BY cantidad_servicios DESC LIMIT 10";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("id"));
                empleado.setNombres(resultado.getString("nombres"));
                empleado.setApellidos(resultado.getString("apellidos"));

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return empleados;
    }

    /**
     * Obtener el total de ganancias por los servicios realizados en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin    Fecha de fin.
     * @return BigDecimal Total de ganancias.
     */
    public BigDecimal obtenerTotalGananciasPorServiciosRealizadosEnRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        BigDecimal totalGanancias = BigDecimal.ZERO;

        try {
            final String SQL = "SSELECT SUM(cantidad * precio) FROM atencion AS A INNER JOIN servicio AS S ON A.servicio_id = S.id  WHERE fecha_hora_atencion BETWEEN ? AND ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            sentencia.setTimestamp(2, Timestamp.valueOf(fechaFin));

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                totalGanancias = resultado.getBigDecimal("total_ganancias");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return totalGanancias;
    }

    /**
     * Obtener usuario a partir de su correo.
     *
     * @param correo Correo del usuario.
     * @return Usuario Usuario.
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = null;

        try {
            final String SQL = "SELECT * FROM usuario WHERE correo = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, correo);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setEmpleadoId(resultado.getInt("empleado_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return usuario;
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return List<Cliente> Lista de clientes.
     */
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM cliente";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setDocumento(resultado.getString("documento"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setTipoDocumentoId(resultado.getInt("tipo_documento_id"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return clientes;
    }

    /**
     * Buscar un documento a partir de su ID.
     *
     * @param id ID del documento.
     * @return Documento Documento.
     */
    public TipoDocumento obtenerDocumentoPorId(int id) {
        try {
            final String SQL = "SELECT * FROM tipo_documento WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, id);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(id);
                tipoDocumento.setNombre(resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Obtiene todas las direcciones.
     *
     * @return List<Direccion> Lista de direcciones.
     */
    public List<Direccion> obtenerDirecciones() {
        List<Direccion> direcciones = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM direccion";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Direccion direccion = new Direccion();
                direccion.setId(resultado.getInt("id"));
                direccion.setDescripcion(resultado.getString("descripcion"));
                direccion.setClienteId(resultado.getInt("cliente_id"));

                direcciones.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return direcciones;
    }

    /**
     * Obtener una dirección a partir de su ID.
     *
     * @param id ID de la dirección.
     * @return Direccion Dirección.
     */
    public Direccion obtenerDireccionPorId(int id) {
        Direccion direccion = null;

        try {
            final String SQL = "SELECT * FROM direccion WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, id);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                direccion = new Direccion();
                direccion.setId(id);
                direccion.setDescripcion(resultado.getString("descripcion"));
                direccion.setClienteId(resultado.getInt("cliente_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return direccion;
    }

    /**
     * Actualizar los datos de una dirección a partir de su ID
     *
     * @param direccion Dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarDireccion(Direccion direccion) {
        try {
            final String SQL = "UPDATE direccion SET descripcion = ?, cliente_id = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, direccion.getDescripcion());
            sentencia.setInt(2, direccion.getClienteId());
            sentencia.setInt(3, direccion.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Eliminar una dirección a partir de su ID.
     *
     * @param id ID de la dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean eliminarDireccionPorId(int id) {
        try {
            final String SQL = "DELETE FROM direccion WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Obtener todos los usuarios.
     *
     * @return List<Usuario> Lista de usuarios.
     */
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM usuario";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setEmpleadoId(resultado.getInt("empleado_id"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return usuarios;
    }

    /**
     * Buscar un empleado y usuario por correo.
     *
     * @param correo Correo del usuario.
     * @return Empleado Empleado.
     */
    public Empleado obtenerEmpleadoPorCorreo(String correo) {
        Empleado empleado = null;

        try {
            final String SQL = "SELECT * FROM empleado e INNER JOIN usuario u ON e.id = u.empleado_id WHERE u.correo = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, correo);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                empleado = new Empleado();
                empleado.setId(resultado.getInt("id"));
                empleado.setNombres(resultado.getString("nombres"));
                empleado.setApellidos(resultado.getString("apellidos"));
                empleado.setSueldo(resultado.getDouble("sueldo"));
                empleado.setRolId(resultado.getInt("rol_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return empleado;
    }

    /**
     * Obtener un usuario a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return Usuario Usuario.
     */
    public Usuario obtenerUsuarioPorEmpleadoId(int empleadoId) {
        Usuario usuario = null;

        try {
            final String SQL = "SELECT * FROM usuario WHERE empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, empleadoId);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setEmpleadoId(empleadoId);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return usuario;
    }

    /**
     * Buscar empleados por el ID de rol.
     *
     * @param rolId ID del rol.
     * @return Lista de roles.
     */
    public List<Empleado> obtenerEmpleadosPorRolId(int rolId) {
        List<Empleado> empleados = new ArrayList<>();

        try {
            final String SQL = "SELECT * FROM empleado WHERE rol_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, rolId);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultado.getInt("id"));
                empleado.setNombres(resultado.getString("nombres"));
                empleado.setApellidos(resultado.getString("apellidos"));
                empleado.setSueldo(resultado.getDouble("sueldo"));
                empleado.setRolId(rolId);

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return empleados;
    }

    /**
     * Actualizar los datos de un empleado.
     *
     * @param empleado Empleado.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarEmpleado(Empleado empleado) {
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
     * Actualizar los datos de un usuario.
     *
     * @param idEmpleado ID del empleado.
     * @param correo     Correo del usuario.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarUsuarioCorreo(int idEmpleado, String correo) {
        try {
            final String SQL = "UPDATE usuario SET correo = ? WHERE empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, correo);
            sentencia.setInt(2, idEmpleado);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Actualizar la clave de un usuario.
     *
     * @param empleadoId ID del empleado.
     * @param clave      Clave del usuario.
     */
    public boolean actualizarUsuarioClave(int empleadoId, String clave) {
        try {
            final String SQL = "UPDATE usuario SET clave = ? WHERE empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, clave);
            sentencia.setInt(2, empleadoId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id ID del empleado.
     * @return Empleado.
     */
    public Empleado obtenerEmpleadoPorId(int id) {
        Empleado empleado = null;

        try {
            final String SQL = "SELECT * FROM empleado WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, id);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                empleado = new Empleado();
                empleado.setId(id);
                empleado.setNombres(resultado.getString("nombres"));
                empleado.setApellidos(resultado.getString("apellidos"));
                empleado.setSueldo(resultado.getDouble("sueldo"));
                empleado.setRolId(resultado.getInt("rol_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return empleado;
    }

    /**
     * Eliminar un usuario a partir del ID del empleado.
     *
     * @param empleadoId ID del empleado.
     * @return boolean Resultado de la operación.
     */
    public boolean eliminarUsuarioPorEmpleadoId(int empleadoId) {
        try {
            final String SQL = "DELETE FROM usuario WHERE empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, empleadoId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServicios() {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT s.id, s.fecha_hora_entrega, s.descripcion, s.cliente_id, s.empleado_id, s.direccion_id, e.nombres AS empleado_nombres, e.apellidos AS empleado_apellidos, c.nombres AS cliente_nombres, c.apellidos AS cliente_apellidos, d.descripcion AS direccion_descripcion FROM servicio s INNER JOIN empleado e ON s.empleado_id = e.id INNER JOIN cliente c ON s.cliente_id = c.id INNER JOIN direccion d ON s.direccion_id = d.id";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);

            ResultSet resultado = sentencia.executeQuery();

            return llenarDatosServicios(servicios, resultado);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @param documento Documento del cliente.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorClienteDocumento(String documento) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT s.id, s.fecha_hora_entrega, s.descripcion, s.cliente_id, s.empleado_id, s.direccion_id, e.nombres AS empleado_nombres, e.apellidos AS empleado_apellidos, c.nombres AS cliente_nombres, c.apellidos AS cliente_apellidos, d.descripcion AS direccion_descripcion FROM servicio s INNER JOIN empleado e ON s.empleado_id = e.id INNER JOIN cliente c ON s.cliente_id = c.id INNER JOIN direccion d ON s.direccion_id = d.id WHERE c.documento = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, documento);

            ResultSet resultado = sentencia.executeQuery();

            return llenarDatosServicios(servicios, resultado);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }

    private List<Servicio> llenarDatosServicios(List<Servicio> servicios, ResultSet resultado) throws SQLException {
        while (resultado.next()) {
            Servicio servicio = new Servicio();
            servicio.setId(resultado.getInt("id"));
            servicio.setFechaHoraEntrega(convertirTextoAFecha(resultado.getString("fecha_hora_entrega")));
            servicio.setDescripcion(resultado.getString("descripcion"));

            Empleado empleado = new Empleado();
            empleado.setId(resultado.getInt("empleado_id"));
            empleado.setNombres(resultado.getString("empleado_nombres"));
            empleado.setApellidos(resultado.getString("empleado_apellidos"));

            Cliente cliente = new Cliente();
            cliente.setId(resultado.getInt("cliente_id"));
            cliente.setNombres(resultado.getString("cliente_nombres"));
            cliente.setApellidos(resultado.getString("cliente_apellidos"));

            Direccion direccion = new Direccion();
            direccion.setId(resultado.getInt("direccion_id"));
            direccion.setDescripcion(resultado.getString("direccion_descripcion"));

            servicio.setEmpleado(empleado);
            servicio.setCliente(cliente);
            servicio.setDireccion(direccion);

            servicios.add(servicio);
        }

        return servicios;
    }

    /**
     * Buscar todos los servicios que tiene un empleado a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorEmpleadoIdConClienteDireccion(int empleadoId) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT s.id, s.fecha_hora_entrega, s.descripcion, s.cliente_id, s.empleado_id, s.direccion_id, e.nombres AS empleado_nombres, e.apellidos AS empleado_apellidos, c.nombres AS cliente_nombres, c.apellidos AS cliente_apellidos, d.descripcion AS direccion_descripcion FROM servicio s INNER JOIN empleado e ON s.empleado_id = e.id INNER JOIN cliente c ON s.cliente_id = c.id INNER JOIN direccion d ON s.direccion_id = d.id WHERE s.empleado_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, empleadoId);

            ResultSet resultado = sentencia.executeQuery();

            return llenarDatosServicios(servicios, resultado);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }

    /**
     * Busca todos los servicios en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin    Fecha de fin.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorRangoFechas(String fechaInicio, String fechaFin) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            final String SQL = "SELECT s.id, s.fecha_hora_entrega, s.descripcion, s.cliente_id, s.empleado_id, s.direccion_id, e.nombres AS empleado_nombres, e.apellidos AS empleado_apellidos, c.nombres AS cliente_nombres, c.apellidos AS cliente_apellidos, d.descripcion AS direccion_descripcion FROM servicio s INNER JOIN empleado e ON s.empleado_id = e.id INNER JOIN cliente c ON s.cliente_id = c.id INNER JOIN direccion d ON s.direccion_id = d.id WHERE s.fecha_hora_recepcion BETWEEN ? AND ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, fechaInicio);
            sentencia.setString(2, fechaFin);

            ResultSet resultado = sentencia.executeQuery();

            return llenarDatosServicios(servicios, resultado);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }

    /**
     * Actualiza todos los datos de un servicio.
     *
     * @param servicio Servicio a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarServicio(Servicio servicio) {
        try {
            final String SQL = "UPDATE servicio SET fecha_hora_entrega = ?, descripcion = ?, cliente_id = ?, empleado_id = ?, direccion_id = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, servicio.getFechaHoraEntrega().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            sentencia.setString(2, servicio.getDescripcion());
            sentencia.setInt(3, servicio.getClienteId());
            sentencia.setInt(4, servicio.getEmpleadoId());
            sentencia.setInt(5, servicio.getDireccionId());
            sentencia.setInt(6, servicio.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarServicioPorId(int servicioId) {
        try {
            final String SQL = "DELETE FROM servicio WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, servicioId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Eliminar todas las atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionesPorServicioId(int servicioId) {
        try {
            final String SQL = "DELETE FROM atencion WHERE servicio_id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, servicioId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Convierte un texto a fecha.
     *
     * @param fechaHoraEntrega Texto con la fecha.
     * @return LocalDateTime Fecha convertida.
     */
    private LocalDateTime convertirTextoAFecha(String fechaHoraEntrega) {
        try {
            return LocalDateTime.parse(fechaHoraEntrega, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Eliminar una atención a partir de su ID.
     *
     * @param atencionId ID de la atención.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionPorId(int atencionId) {
        try {
            final String SQL = "DELETE FROM atencion WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setInt(1, atencionId);

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Crea un nuevo tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a crear.
     * @return TipoAtencion Tipo de atención creado.
     */
    public TipoAtencion crearTipoAtencion(TipoAtencion tipoAtencion) {
        try {
            final String SQL = "INSERT INTO tipo_atencion (nombre) VALUES (?)";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, tipoAtencion.getNombre());

            if (sentencia.executeUpdate() > 0) {
                ResultSet resultado = sentencia.getGeneratedKeys();

                if (resultado.next()) {
                    tipoAtencion.setId(resultado.getInt(1));

                    return tipoAtencion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Actualiza un tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarTipoAtencion(TipoAtencion tipoAtencion) {
        try {
            final String SQL = "UPDATE tipo_atencion SET nombre = ? WHERE id = ?";
            PreparedStatement sentencia = conexion.getConnection().prepareStatement(SQL);
            sentencia.setString(1, tipoAtencion.getNombre());
            sentencia.setInt(2, tipoAtencion.getId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return Conexion Conexión a la base de datos.
     */
    public Connection getConexion() throws SQLException {
        return conexion.getConnection();
    }
}
