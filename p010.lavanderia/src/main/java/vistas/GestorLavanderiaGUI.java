package vistas;

import modelos.*;
import net.sf.jasperreports.engine.JRDataSource;
import utilidades.Utilidad;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorLavanderiaGUI {

    private JFrame frmGestorLavanderiaGUI;
    private JMenu mnuEmpleados;
    private GestorLavanderia gestorLavanderia;
    private JMenu mnuDirecciones;
    private JMenu mnuServicios;
    private JMenu mnuOtros;
    private JMenu mnuClientes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestorLavanderiaGUI window = new GestorLavanderiaGUI();
                    window.frmGestorLavanderiaGUI.setVisible(true);
                    window.frmGestorLavanderiaGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestorLavanderiaGUI() {
        initialize();
        gestorLavanderia = new GestorLavanderia();

        // Asignación temporal del empleado actual:
        Empleado empleado = gestorLavanderia.obtenerEmpleadoPorId(3);
        gestorLavanderia.setEmpleadoActual(empleado);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmGestorLavanderiaGUI = new JFrame();
        frmGestorLavanderiaGUI.setTitle("Gestor Lavandería");
        frmGestorLavanderiaGUI.setBounds(100, 100, 800, 600);
        frmGestorLavanderiaGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGestorLavanderiaGUI.getContentPane().setLayout(new BorderLayout(0, 0));

        JDesktopPane dtpPrincipal = new JDesktopPane();
        frmGestorLavanderiaGUI.getContentPane().add(dtpPrincipal, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frmGestorLavanderiaGUI.setJMenuBar(menuBar);

        JMenu mnuArchivo = new JMenu("Archivo");
        menuBar.add(mnuArchivo);

        JMenuItem mniIniciarSesion = new JMenuItem("Iniciar sesión...");
        mniIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFrame login = new LoginFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(login);
                login.setVisible(true);
            }
        });
        mnuArchivo.add(mniIniciarSesion);

        JMenuItem mniSalir = new JMenuItem("Salir");
        mniSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(frmGestorLavanderiaGUI, "¿Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JMenuItem mniEmpleadoUsuarioCrear = new JMenuItem("Crear nuevo empleado");
        mniEmpleadoUsuarioCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmpleadoCrearFrame empleadoCrear = new EmpleadoCrearFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(empleadoCrear);
                empleadoCrear.setVisible(true);
            }
        });
        mnuArchivo.add(mniEmpleadoUsuarioCrear);
        mnuArchivo.add(mniSalir);

        mnuEmpleados = new JMenu("Empleados");
        mnuEmpleados.setEnabled(false);
        menuBar.add(mnuEmpleados);

        JMenuItem mniEmpleadosCrear = new JMenuItem("Crear");
        mnuEmpleados.add(mniEmpleadosCrear);

        JMenuItem mniEmpleadosOperaciones = new JMenuItem("Operaciones");
        mniEmpleadosOperaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmpleadoUsuarioFrame empleadoUsuario = new EmpleadoUsuarioFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(empleadoUsuario);
                empleadoUsuario.setVisible(true);
            }
        });
        mnuEmpleados.add(mniEmpleadosOperaciones);

        mnuClientes = new JMenu("Clientes");
        mnuClientes.setEnabled(false);
        menuBar.add(mnuClientes);

        JMenuItem mniClientesOperaciones = new JMenuItem("Operaciones");
        mniClientesOperaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesFrame clientesFrame = new ClientesFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(clientesFrame);
                clientesFrame.setVisible(true);
            }
        });
        mnuClientes.add(mniClientesOperaciones);

        mnuDirecciones = new JMenu("Direcciones");
        mnuDirecciones.setEnabled(false);
        mnuDirecciones.setMnemonic('D');
        menuBar.add(mnuDirecciones);

        JMenuItem mniDireccionesOperaciones = new JMenuItem("Operaciones");
        mniDireccionesOperaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DireccionesFrame direccionesFrame = new DireccionesFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(direccionesFrame);
                direccionesFrame.setVisible(true);
            }
        });
        mnuDirecciones.add(mniDireccionesOperaciones);

        mnuServicios = new JMenu("Servicios");
        mnuServicios.setEnabled(false);
        menuBar.add(mnuServicios);

        JMenuItem mniServiciosGestiones = new JMenuItem("Gestiones");
        mniServiciosGestiones.addActionListener(e -> {
            ServicioFrame serviciosFrame = new ServicioFrame(this);
            dtpPrincipal.add(serviciosFrame);
            serviciosFrame.setVisible(true);
        });
        mnuServicios.add(mniServiciosGestiones);

        mnuOtros = new JMenu("Otros");
        mnuOtros.setEnabled(false);
        menuBar.add(mnuOtros);

        JMenuItem mniTipoAtencion = new JMenuItem("Gestionar tipos atención");
        mniTipoAtencion.addActionListener(e -> {
            TipoAtencionFrame tipoAtencionFrame = new TipoAtencionFrame(this);
            dtpPrincipal.add(tipoAtencionFrame);
            tipoAtencionFrame.setVisible(true);
        });
        mnuOtros.add(mniTipoAtencion);

        JMenuItem mniGenerarReportes = new JMenuItem("Generar reportes...");
        mniGenerarReportes.addActionListener(e -> {
            GeneracionReportesFrame reportesFrame = new GeneracionReportesFrame(this);
            dtpPrincipal.add(reportesFrame);
            reportesFrame.setVisible(true);
        });
        mnuOtros.add(mniGenerarReportes);
    }

    public Usuario iniciarSesion(String email, String password) {
        Usuario usuario = gestorLavanderia.obtenerUsuarioPorCorreo(email);
        boolean resultadoValidacion = Utilidad.comprobarPassword(usuario.getClave(), password);

        if (resultadoValidacion) {
            Empleado empleado = gestorLavanderia.obtenerEmpleadoPorId(usuario.getEmpleadoId());

            gestorLavanderia.setEmpleadoActual(empleado);
        }

        return resultadoValidacion ? usuario : null;
    }

    public void mostrarMenus() {
        mnuEmpleados.setEnabled(true);
        mnuClientes.setEnabled(true);
        mnuDirecciones.setEnabled(true);
        mnuServicios.setEnabled(true);
        mnuOtros.setEnabled(true);
    }

    public List<Rol> getRoles() {
        return gestorLavanderia.getRoles();
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return gestorLavanderia.obtenerUsuarioPorCorreo(correo);
    }

    /**
     * Crear un nuevo empleado.
     *
     * @param empleado Empleado a crear.
     * @return Empleado Empleado creado.
     */
    public Empleado crearEmpleado(Empleado empleado) {
        return gestorLavanderia.crearEmpleado(empleado);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario Usuario a crear.
     * @return Usuario Usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return gestorLavanderia.crearUsuario(usuario);
    }

    /**
     * Elimina un empleado a partir de su ID.
     *
     * @param id ID del empleado.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarEmpleadoPorId(int id) {
        return gestorLavanderia.eliminarEmpleadoPorId(id);
    }

    /**
     * Obtiene el listado de tipos de documento.
     *
     * @return Listado de tipos de documento.
     */
    public TipoDocumento[] obtenerTiposDocumentos() {
        List<TipoDocumento> tiposDocumento = gestorLavanderia.obtenerTiposDocumentos();

        return tiposDocumento.toArray(new TipoDocumento[tiposDocumento.size()]);
    }

    /**
     * Obtiene un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorDocumento(String documento) {
        return gestorLavanderia.obtenerClientePorDocumento(documento);
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Cliente a crear.
     * @return Cliente Cliente creado.
     */
    public Cliente crearCliente(Cliente cliente) {
        return gestorLavanderia.crearCliente(cliente);
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return List<Cliente> Lista de clientes.
     */
    public List<Cliente> obtenerClientes() {
        return gestorLavanderia.obtenerClientes();
    }

    /**
     * Actualiza los datos de un cliente.
     *
     * @param cliente Cliente a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        return gestorLavanderia.actualizarCliente(cliente);
    }

    /**
     * Obtiene un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorId(int clienteId) {
        return gestorLavanderia.obtenerClientePorId(clienteId);
    }

    /**
     * Elimina un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarClientePorDocumento(String documento) {
        return gestorLavanderia.eliminarClientePorDocumento(documento);
    }

    /**
     * Crea una nueva dirección.
     *
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        return gestorLavanderia.crearDireccion(direccion);
    }

    /**
     * Obtiene todas las direcciones.
     *
     * @return List<Direccion> Lista de direcciones.
     */
    public List<Direccion> obtenerDirecciones() {
        return gestorLavanderia.obtenerDirecciones();
    }

    /**
     * Obtener todas las direcciones de un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClienteId(int clienteId) {
        return gestorLavanderia.obtenerDireccionesPorClienteId(clienteId);
    }

    /**
     * Obtener una dirección a partir de su ID.
     *
     * @param direccionId ID de la dirección.
     * @return Direccion Dirección.
     */
    public Direccion obtenerDireccionPorId(int direccionId) {
        return gestorLavanderia.obtenerDireccionPorId(direccionId);
    }

    /**
     * Actualizar los datos de una dirección a partir de su ID
     *
     * @param direccion Dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarDireccion(Direccion direccion) {
        return gestorLavanderia.actualizarDireccion(direccion);
    }

    /**
     * Eliminar una dirección a partir de su ID.
     *
     * @param direccionId ID de la dirección.
     * @return boolean Resultado de la operación.
     */
    public boolean eliminarDireccionPorId(int direccionId) {
        return gestorLavanderia.eliminarDireccionPorId(direccionId);
    }

    /**
     * Obtiene todos los roles.
     *
     * @return Lista de roles.
     */
    public List<Rol> obtenerRoles() {
        return gestorLavanderia.obtenerRoles();
    }

    /**
     * Recupera todos los empleados.
     *
     * @return List<Empleado> Lista de empleados.
     */
    public List<Empleado> obtenerEmpleados() {
        return gestorLavanderia.obtenerEmpleados();
    }

    /**
     * Obtener todos los usuarios.
     *
     * @return List<Usuario> Lista de usuarios.
     */
    public List<Usuario> obtenerUsuarios() {
        return gestorLavanderia.obtenerUsuarios();
    }

    /**
     * Buscar un empleado y usuario por correo.
     *
     * @param correo Correo del usuario.
     * @return Empleado Empleado.
     */
    public Empleado obtenerEmpleadoPorCorreo(String correo) {
        return gestorLavanderia.obtenerEmpleadoPorCorreo(correo);
    }

    /**
     * Obtener un usuario a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return Usuario Usuario.
     */
    public Usuario obtenerUsuarioPorEmpleadoId(int empleadoId) {
        return gestorLavanderia.obtenerUsuarioPorEmpleadoId(empleadoId);
    }

    /**
     * Buscar empleados por el ID de rol.
     *
     * @param rolId ID del rol.
     * @return Lista de roles.
     */
    public List<Empleado> obtenerEmpleadosPorRolId(int rolId) {
        return gestorLavanderia.obtenerEmpleadosPorRolId(rolId);
    }

    /**
     * Actualizar los datos de un empleado.
     *
     * @param empleado Empleado.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarEmpleado(Empleado empleado) {
        return gestorLavanderia.actualizarEmpleado(empleado);
    }

    /**
     * Actualizar los datos de un usuario.
     *
     * @param empleadoId ID del empleado.
     * @param correo     Correo del usuario.
     * @return boolean Resultado de la operación.
     */
    public boolean actualizarUsuarioCorreo(int empleadoId, String correo) {
        return gestorLavanderia.actualizarUsuarioCorreo(empleadoId, correo);
    }

    /**
     * Actualizar la clave de un usuario.
     *
     * @param empleadoId ID del empleado.
     * @param clave      Clave del usuario.
     */
    public boolean actualizarUsuarioClave(int empleadoId, String clave) {
        return gestorLavanderia.actualizarUsuarioClave(empleadoId, clave);
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param empleadoId ID del empleado.
     * @return Empleado.
     */
    public Empleado obtenerEmpleadoPorId(int empleadoId) {
        return gestorLavanderia.obtenerEmpleadoPorId(empleadoId);
    }

    /**
     * Obtiene el empleado actual.
     *
     * @return Empleado.
     */
    public Empleado obtenerEmpleadoActual() {
        return gestorLavanderia.getEmpleadoActual();
    }

    /**
     * Lista atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return List<Atencion> Lista de atenciones.
     */
    public List<Atencion> obtenerAtencionesPorServicioId(int servicioId) {
        return gestorLavanderia.obtenerAtencionesPorServicioId(servicioId);
    }

    /**
     * Obtiene todos los tipos de atencion.
     *
     * @return Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencion() {
        return gestorLavanderia.obtenerTiposAtencion();
    }

    /**
     * Crea un nuevo servicio.
     *
     * @param servicio Servicio a crear.
     * @return Servicio Servicio creado.
     */
    public Servicio crearServicio(Servicio servicio) {
        return gestorLavanderia.crearServicio(servicio);
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServicios() {
        return gestorLavanderia.obtenerServicios();
    }

    /**
     * Obtiene todos los servicios existentes con el empleado, el cliente y la dirección.
     *
     * @param documento Documento del cliente.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorClienteDocumento(String documento) {
        return gestorLavanderia.obtenerServiciosPorClienteDocumento(documento);
    }

    /**
     * Buscar todos los servicios que tiene un empleado a partir de su ID.
     *
     * @param empleadoId ID del empleado.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorEmpleadoIdConClienteDireccion(int empleadoId) {
        return gestorLavanderia.obtenerServiciosPorEmpleadoIdConClienteDireccion(empleadoId);
    }

    /**
     * Busca todos los servicios en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin    Fecha de fin.
     * @return List<Servicio> Lista de servicios.
     */
    public List<Servicio> obtenerServiciosPorRangoFechas(String fechaInicio, String fechaFin) {
        return gestorLavanderia.obtenerServiciosPorRangoFechas(fechaInicio, fechaFin);
    }

    /**
     * Busca un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return Servicio Servicio encontrado.
     */
    public Servicio obtenerServicioPorId(int servicioId) {
        return gestorLavanderia.obtenerServicioPorId(servicioId);
    }

    /**
     * Actualiza todos los datos de un servicio.
     *
     * @param servicio Servicio a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarServicio(Servicio servicio) {
        return gestorLavanderia.actualizarServicio(servicio);
    }

    /**
     * Eliminar todas las atenciones a partir del ID de un servicio.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionesPorServicioId(int servicioId) {
        return gestorLavanderia.eliminarAtencionesPorServicioId(servicioId);
    }

    /**
     * Elimina un servicio a partir de su ID.
     *
     * @param servicioId ID del servicio.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarServicioPorId(int servicioId) {
        return gestorLavanderia.eliminarServicioPorId(servicioId);
    }

    /**
     * Crea una nueva atención.
     *
     * @param atencion Atención a crear.
     * @return Atencion Atención creada.
     */
    public Atencion crearAtencion(Atencion atencion) {
        return gestorLavanderia.crearAtencion(atencion);
    }

    /**
     * Eliminar una atención a partir de su ID.
     *
     * @param atencionId ID de la atención.
     * @return boolean True si se eliminó correctamente, false si no.
     */
    public boolean eliminarAtencionPorId(int atencionId) {
        return gestorLavanderia.eliminarAtencionPorId(atencionId);
    }

    /**
     * Crea un nuevo tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a crear.
     * @return TipoAtencion Tipo de atención creado.
     */
    public TipoAtencion crearTipoAtencion(TipoAtencion tipoAtencion) {
        return gestorLavanderia.crearTipoAtencion(tipoAtencion);
    }

    /**
     * Actualiza un tipo de atención.
     *
     * @param tipoAtencion Tipo de atención a actualizar.
     * @return boolean True si se actualizó correctamente, false si no.
     */
    public boolean actualizarTipoAtencion(TipoAtencion tipoAtencion) {
        return gestorLavanderia.actualizarTipoAtencion(tipoAtencion);
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return Conexion Conexión a la base de datos.
     */
    public Connection getConexion() throws SQLException {
        return gestorLavanderia.getConexion();
    }

    /**
     * Obtiene el empleado actual.
     *
     * @param empleado Empleado actual.
     */
    public void setEmpleadoActual(Empleado empleado) {
        gestorLavanderia.setEmpleadoActual(empleado);
    }
}
