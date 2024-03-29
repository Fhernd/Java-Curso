package modelos;

import datos.AccesoDatos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GestorLavanderia {
    private AccesoDatos accesoDatos;
    private Empleado empleadoActual;

    public GestorLavanderia() {
        accesoDatos = new AccesoDatos();
    }

    /**
     * Obtiene el listado de roles.
     *
     * @return Listado de roles.
     */
    public List<Rol> getRoles() {
        return accesoDatos.obtenerRoles();
    }

    /**
     * Obtiene el listado de tipos de documento.
     *
     * @return Listado de tipos de documento.
     */
    public List<TipoDocumento> obtenerTiposDocumentos() {
        return accesoDatos.obtenerTiposDocumentos();
    }

    /**
     * Obtiene todos los tipos de atencion.
     *
     * @return Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencion() {
        return accesoDatos.obtenerTiposAtencion();
    }

    /**
     * Crea una nueva dirección.
     *
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        return accesoDatos.crearDireccion(direccion);
    }

    /**
     * Obtener una dirección a partir del ID del cliente.
     *
     * @param clienteId ID del cliente.
     * @return Direccion Dirección encontrada.
     */
    public Direccion obtenerDireccionPorClienteId(int clienteId) {
        return accesoDatos.obtenerDireccionPorClientId(clienteId);
    }

    /**
     * Obtener todas las direcciones de un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClientId(int clienteId) {
        return accesoDatos.obtenerDireccionesPorClienteId(clienteId);
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Cliente a crear.
     * @return Cliente Cliente creado.
     */
    public Cliente crearCliente(Cliente cliente) {
        return accesoDatos.crearCliente(cliente);
    }

    /**
     * Obtiene un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorId(int clienteId) {
        return accesoDatos.obtenerClientePorId(clienteId);
    }

    /**
     * Obtiene un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorDocumento(String documento) {
        return accesoDatos.obtenerClientePorDocumento(documento);
    }

    /**
     * Actualiza los datos de un cliente.
     *
     * @param cliente Cliente a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        return accesoDatos.actualizarCliente(cliente);
    }

    /**
     * Elimina un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarClientePorDocumento(String documento) {
        return accesoDatos.eliminarClientePorDocumento(documento);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario Usuario a crear.
     * @return Usuario Usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return accesoDatos.crearUsuario(usuario);
    }

    /**
     * Inicia sesión a partir de las credenciales de un usuario.
     *
     * @param correo Correo del usuario.
     * @param clave  Clave del usuario.
     * @return Usuario Usuario encontrado.
     */
    public Usuario iniciarSesion(String correo, String clave) {
        return accesoDatos.iniciarSesion(correo, clave);
    }

    /**
     * Elimina un usuario a partir de su correo.
     *
     * @param correo Correo del usuario.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarUsuarioPorCorreo(String correo) {
        return accesoDatos.eliminarUsuarioPorCorreo(correo);
    }

    /**
     * Crear un nuevo empleado.
     *
     * @param empleado Empleado a crear.
     * @return Empleado Empleado creado.
     */
    public Empleado crearEmpleado(Empleado empleado) {
        return accesoDatos.crearEmpleado(empleado);
    }

    /**
     * Modifica los datos de un empleado.
     *
     * @param empleado Empleado a modificar.
     * @return true si se modificó correctamente, false en caso contrario.
     */
    public boolean modificarEmpleado(Empleado empleado) {
        return accesoDatos.modificarEmpleado(empleado);
    }

    /**
     * Recupera todos los empleados.
     *
     * @return List<Empleado> Lista de empleados.
     */
    public List<Empleado> obtenerEmpleados() {
        return accesoDatos.obtenerEmpleados();
    }

    /**
     * Elimina un empleado a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarEmpleadoPorId(int empleadoId) {
        accesoDatos.eliminarUsuarioPorEmpleadoId(empleadoId);
        return accesoDatos.eliminarEmpleadoPorId(empleadoId);
    }

    /**
     * Crea una nueva atención.
     *
     * @param atencion Atención a crear.
     * @return Atencion Atención creada.
     */
    public Atencion crearAtencion(Atencion atencion) {
        return accesoDatos.crearAtencion(atencion);
    }

    /**
     * Lista atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return List<Atencion> Lista de atenciones.
     */
    public List<Atencion> obtenerAtencionesPorServicioId(int servicioId) {
        return accesoDatos.obtenerAtencionesPorServicioId(servicioId);
    }

    /**
     * Listar atenciones a partir del ID de un tipo de atención.
     *
     * @param tipoAtencionId ID del tipo de atención.
     * @return List<Atencion> Lista de atenciones.
     */
    public List<Atencion> obtenerAtencionesPorTipoAtencionId(int tipoAtencionId) {
        return accesoDatos.obtenerAtencionesPorTipoAtencionId(tipoAtencionId);
    }

    /**
     * Crea un nuevo servicio.
     *
     * @param servicio Servicio a crear.
     * @return Servicio Servicio creado.
     */
    public Servicio crearServicio(Servicio servicio) {
        return accesoDatos.crearServicio(servicio);
    }

    /**
     * Lista servicios a partir del ID de un cliente.
     *
     * @param clienteId ID del cliente.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorClienteId(int clienteId) {
        return accesoDatos.obtenerServiciosPorClienteId(clienteId);
    }

    /**
     * Lista servicios a partir del ID de un empleado.
     *
     * @param empleadoId ID del empleado.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorEmpleadoId(int empleadoId) {
        return accesoDatos.obtenerServiciosPorEmpleadoId(empleadoId);
    }

    /**
     * Actualiza la fecha de entrega de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return boolean Verdadero si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarFechaEntregaServicio(int servicioId) {
        return accesoDatos.actualizarFechaEntregaServicio(servicioId);
    }

    /**
     * Busca un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return Servicio Servicio encontrado.
     */
    public Servicio obtenerServicioPorId(int servicioId) {
        return accesoDatos.obtenerServicioPorId(servicioId);
    }

    /**
     * Obtener los clientes que más han solicitado servicios.
     *
     * @return List<Cliente> Lista de clientes.
     */
    public List<Cliente> obtenerClientesQueMasHanSolicitadoServicios(int cantidadClientes) {
        return accesoDatos.obtenerClientesQueMasHanSolicitadoServicios(cantidadClientes);
    }

    /**
     * Obtener los tipos de atención más solicitados.
     *
     * @return List<TipoAtencion> Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencionMasSolicitados() {
        return accesoDatos.obtenerTiposAtencionMasSolicitados();
    }

    /**
     * Obtener los 10 empleados que más han atendido servicios.
     *
     * @return List<Empleado> Lista de empleados.
     */
    public List<Empleado> obtenerEmpleadosQueMasHanAtendidoServicios() {
        return accesoDatos.obtenerEmpleadosQueMasHanAtendidoServicios();
    }

    /**
     * Obtener el total de ganancias por los servicios realizados en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin    Fecha de fin.
     * @return BigDecimal Total de ganancias.
     */
    public BigDecimal obtenerTotalGananciasPorServiciosRealizadosEnRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return accesoDatos.obtenerTotalGananciasPorServiciosRealizadosEnRangoFechas(fechaInicio, fechaFin);
    }

    /**
     * Obtener usuario a partir de su correo.
     *
     * @param correo Correo del usuario.
     * @return Usuario Usuario.
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return accesoDatos.obtenerUsuarioPorCorreo(correo);
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return List<Cliente> Lista de clientes.
     */
    public List<Cliente> obtenerClientes() {
        return accesoDatos.obtenerClientes();
    }

    /**
     * Obtiene todas las direcciones.
     *
     * @return List<Direccion> Lista de direcciones.
     */
    public List<Direccion> obtenerDirecciones() {
        return accesoDatos.obtenerDirecciones();
    }

    /**
     * Obtener todas las direcciones de un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClienteId(int clienteId) {
        return accesoDatos.obtenerDireccionesPorClienteId(clienteId);
    }

    /**
     * Obtener una dirección a partir de su ID.
     *
     * @param direccionId ID de la dirección.
     * @return Direccion Dirección.
     */
    public Direccion obtenerDireccionPorId(int direccionId) {
        return accesoDatos.obtenerDireccionPorId(direccionId);
    }

    /**
     * Actualizar los datos de una dirección a partir de su ID
     *
     * @param direccion Dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarDireccion(Direccion direccion) {
        return accesoDatos.actualizarDireccion(direccion);
    }

    /**
     * Eliminar una dirección a partir de su ID.
     *
     * @param direccionId ID de la dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean eliminarDireccionPorId(int direccionId) {
        return accesoDatos.eliminarDireccionPorId(direccionId);
    }

    /**
     * Obtiene todos los roles.
     *
     * @return Lista de roles.
     */
    public List<Rol> obtenerRoles() {
        return accesoDatos.obtenerRoles();
    }

    /**
     * Obtener todos los usuarios.
     *
     * @return List<Usuario> Lista de usuarios.
     */
    public List<Usuario> obtenerUsuarios() {
        return accesoDatos.obtenerUsuarios();
    }

    /**
     * Buscar un empleado y usuario por correo.
     *
     * @param correo Correo del usuario.
     * @return Empleado Empleado.
     */
    public Empleado obtenerEmpleadoPorCorreo(String correo) {
        return accesoDatos.obtenerEmpleadoPorCorreo(correo);
    }

    /**
     * Obtener un usuario a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return Usuario Usuario.
     */
    public Usuario obtenerUsuarioPorEmpleadoId(int empleadoId) {
        return accesoDatos.obtenerUsuarioPorEmpleadoId(empleadoId);
    }

    /**
     * Buscar empleados por el ID de rol.
     *
     * @param rolId ID del rol.
     * @return Lista de roles.
     */
    public List<Empleado> obtenerEmpleadosPorRolId(int rolId) {
        return accesoDatos.obtenerEmpleadosPorRolId(rolId);
    }

    /**
     * Actualizar los datos de un empleado.
     *
     * @param empleado Empleado.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarEmpleado(Empleado empleado) {
        return accesoDatos.actualizarEmpleado(empleado);
    }

    /**
     * Actualizar los datos de un usuario.
     *
     * @param empleadoId ID del empleado.
     * @param correo     Correo del usuario.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarUsuarioCorreo(int empleadoId, String correo) {
        return accesoDatos.actualizarUsuarioCorreo(empleadoId, correo);
    }

    /**
     * Actualizar la clave de un usuario.
     *
     * @param empleadoId ID del empleado.
     * @param clave      Clave del usuario.
     */
    public boolean actualizarUsuarioClave(int empleadoId, String clave) {
        return accesoDatos.actualizarUsuarioClave(empleadoId, clave);
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param empleadoId ID del empleado.
     * @return Empleado.
     */
    public Empleado obtenerEmpleadoPorId(int empleadoId) {
        return accesoDatos.obtenerEmpleadoPorId(empleadoId);
    }

    /**
     * Obtiene el empleado actual.
     *
     * @return Empleado.
     */
    public Empleado getEmpleadoActual() {
        return empleadoActual;
    }

    /**
     * Establece el empleado actual.
     *
     * @param empleadoActual Empleado.
     */
    public void setEmpleadoActual(Empleado empleadoActual) {
        this.empleadoActual = empleadoActual;
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServicios() {
        return accesoDatos.obtenerServicios();
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @param documento Documento del cliente.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorClienteDocumento(String documento) {
        return accesoDatos.obtenerServiciosPorClienteDocumento(documento);
    }

    /**
     * Buscar todos los servicios que tiene un empleado a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorEmpleadoIdConClienteDireccion(int empleadoId) {
        return accesoDatos.obtenerServiciosPorEmpleadoIdConClienteDireccion(empleadoId);
    }

    /**
     * Busca todos los servicios en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin    Fecha de fin.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorRangoFechas(String fechaInicio, String fechaFin) {
        return accesoDatos.obtenerServiciosPorRangoFechas(fechaInicio, fechaFin);
    }

    /**
     * Actualiza todos los datos de un servicio.
     *
     * @param servicio Servicio a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarServicio(Servicio servicio) {
        return accesoDatos.actualizarServicio(servicio);
    }

    /**
     * Eliminar todas las atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionesPorServicioId(int servicioId) {
        return accesoDatos.eliminarAtencionesPorServicioId(servicioId);
    }

    /**
     * Elimina un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarServicioPorId(int servicioId) {
        return accesoDatos.eliminarServicioPorId(servicioId);
    }

    /**
     * Eliminar una atención a partir de su ID.
     *
     * @param atencionId ID de la atención.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionPorId(int atencionId) {
        return accesoDatos.eliminarAtencionPorId(atencionId);
    }

    /**
     * Crea un nuevo tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a crear.
     * @return TipoAtencion Tipo de atención creado.
     */
    public TipoAtencion crearTipoAtencion(TipoAtencion tipoAtencion) {
        return accesoDatos.crearTipoAtencion(tipoAtencion);
    }

    /**
     * Actualiza un tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarTipoAtencion(TipoAtencion tipoAtencion) {
        return accesoDatos.actualizarTipoAtencion(tipoAtencion);
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return Conexion Conexión a la base de datos.
     */
    public Connection getConexion() throws SQLException {
        return accesoDatos.getConexion();
    }
}
