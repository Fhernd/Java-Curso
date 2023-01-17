package vistas;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import modelos.Empleado;
import modelos.Rol;
import modelos.Usuario;
import org.apache.commons.validator.routines.EmailValidator;
import utilidades.Utilidad;
import vistas.models.RolesComboBoxModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpleadoUsuarioFrame extends JInternalFrame {

    private final static String CLAVE = "@$#&%-123-987-%&$#@";
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtSueldo;
    private JTextField txtCorreo;
    private JPasswordField pwdClave;
    private JPasswordField pwdClaveRepetir;
    private JTable tblRegistros;
    private JComboBox cbxRol;
    private GestorLavanderiaGUI gestorLavanderiaGUI;
    private RolesComboBoxModel rolesComboBoxModel;
    private JTextField txtId;
    private int empleadoId;

    /**
     * Create the frame.
     */
    public EmpleadoUsuarioFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;
        empleadoId = 0;

        setClosable(true);
        setTitle("Empleados");
        setBounds(100, 100, 600, 760);

        JPanel pnlDatos = new JPanel();
        pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlDatos, BorderLayout.NORTH);
        pnlDatos.setLayout(new FormLayout(new ColumnSpec[] {
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),},
        	new RowSpec[] {
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		FormSpecs.DEFAULT_ROWSPEC,}));
        
        JLabel lblId = new JLabel("ID:");
        pnlDatos.add(lblId, "2, 2, left, default");
        
        txtId = new JTextField();
        txtId.setEditable(false);
        pnlDatos.add(txtId, "10, 2, fill, top");
        txtId.setColumns(10);

        JLabel lblNombres = new JLabel("Nombres:");
        pnlDatos.add(lblNombres, "2, 4, left, bottom");

        txtNombres = new JTextField();
        pnlDatos.add(txtNombres, "10, 4, fill, default");
        txtNombres.setColumns(10);

        JLabel lblApellidos = new JLabel("Apellidos:");
        pnlDatos.add(lblApellidos, "2, 6");

        txtApellidos = new JTextField();
        pnlDatos.add(txtApellidos, "10, 6, fill, default");
        txtApellidos.setColumns(10);

        JLabel lblSueldo = new JLabel("Sueldo:");
        pnlDatos.add(lblSueldo, "2, 8");

        txtSueldo = new JTextField();
        pnlDatos.add(txtSueldo, "10, 8, fill, default");
        txtSueldo.setColumns(10);

        JLabel lblRol = new JLabel("Rol:");
        pnlDatos.add(lblRol, "2, 10");

        cbxRol = new JComboBox();
        pnlDatos.add(cbxRol, "10, 10, fill, default");

        JLabel lblCorreo = new JLabel("Correo:");
        pnlDatos.add(lblCorreo, "2, 12");

        txtCorreo = new JTextField();
        pnlDatos.add(txtCorreo, "10, 12, fill, default");
        txtCorreo.setColumns(10);

        JLabel lblClave = new JLabel("Clave:");
        pnlDatos.add(lblClave, "2, 14");

        pwdClave = new JPasswordField();
        pnlDatos.add(pwdClave, "10, 14, fill, default");

        JLabel lblClaveRepetir = new JLabel("Clave (repetir):");
        pnlDatos.add(lblClaveRepetir, "2, 16");

        pwdClaveRepetir = new JPasswordField();
        pnlDatos.add(pwdClaveRepetir, "10, 16, fill, default");

        JPanel pnlAcciones = new JPanel();
        pnlAcciones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlAcciones, BorderLayout.CENTER);
        pnlAcciones.setLayout(new GridLayout(0, 5, 0, 0));

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String sueldoTexto = txtSueldo.getText().trim();
                int rolIndice = cbxRol.getSelectedIndex();
                String correo = txtCorreo.getText().trim();
                String clave = new String(pwdClave.getPassword());
                String claveRepetir = new String(pwdClaveRepetir.getPassword());

                if (!nombres.isEmpty() || !apellidos.isEmpty() || !sueldoTexto.isEmpty() || !correo.isEmpty() || !clave.isEmpty() || !claveRepetir.isEmpty() || rolIndice > 0) {

                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea limpiar los campos?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        txtNombres.setText("");
                        txtApellidos.setText("");
                        txtSueldo.setText("");
                        cbxRol.setSelectedIndex(0);
                        txtCorreo.setText("");
                        pwdClave.setText("");
                        pwdClaveRepetir.setText("");
                    }
                }

            }
        });
        pnlAcciones.add(btnNuevo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String sueldoTexto = txtSueldo.getText().trim();
                int rolId = ((Rol) cbxRol.getSelectedItem()).getId();
                String correo = txtCorreo.getText().trim();
                String clave = new String(pwdClave.getPassword());
                String claveRepetir = new String(pwdClaveRepetir.getPassword());

                if (nombres.isEmpty() || apellidos.isEmpty() || sueldoTexto.isEmpty() || correo.isEmpty() || clave.isEmpty() || claveRepetir.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!clave.equals(claveRepetir)) {
                    JOptionPane.showMessageDialog(null, "Las claves no coinciden", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double sueldo;

                try {
                    sueldo = Double.parseDouble(sueldoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El sueldo debe ser un valor numérico.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (sueldo < 0) {
                    JOptionPane.showMessageDialog(null, "El sueldo debe ser mayor a cero.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!EmailValidator.getInstance().isValid(correo)) {
                    JOptionPane.showMessageDialog(null, "El correo no es válido.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Empleado empleado = new Empleado(nombres, apellidos, sueldo, rolId);

                empleado = gestorLavanderiaGUI.crearEmpleado(empleado);

                if (empleado == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el empleado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                clave = Utilidad.encriptar(clave);
                Usuario usuario = new Usuario(correo, clave, empleado.getId());

                usuario = gestorLavanderiaGUI.crearUsuario(usuario);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el usuario.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(null, "Empleado correctamente creado.", "Información", JOptionPane.INFORMATION_MESSAGE);

                limpiarCampos();
                List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();
                cargarEmpleados(empleados);
            }
        });
        pnlAcciones.add(btnGuardar);

        JButton btnBuscar = new JButton("Buscar...");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String[] opciones = {"Por correo", "Por rol"};

                String opcion = (String) JOptionPane.showInputDialog(EmpleadoUsuarioFrame.this, "Seleccione el tipo de búsqueda", "Búsqueda", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (opcion == null) {
                    return;
                }

                if (opcion.equals(opciones[0])) {
                    String correo = JOptionPane.showInputDialog(EmpleadoUsuarioFrame.this, "Ingrese el correo", "Búsqueda", JOptionPane.QUESTION_MESSAGE);

                    if (correo == null) {
                        return;
                    }

                    correo = correo.trim();

                    if (correo.isEmpty()) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "Debe ingresar el correo", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!EmailValidator.getInstance().isValid(correo)) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "El correo no es válido", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Empleado empleado = gestorLavanderiaGUI.obtenerEmpleadoPorCorreo(correo);

                    if (empleado == null) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se encontró ningún empleado con el correo dado (" + correo +").", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        limpiarCampos();
                        return;
                    }

                    empleadoId = empleado.getId();

                    mostrarEmpleado(empleado);
                } else {
                    List<Rol> roles = gestorLavanderiaGUI.obtenerRoles();

                    String[] nombresRoles = roles.stream().map(rol -> rol.getNombre()).toArray(String[]::new);

                    String nombreRol = (String) JOptionPane.showInputDialog(EmpleadoUsuarioFrame.this, "Seleccione el rol", "Búsqueda", JOptionPane.QUESTION_MESSAGE, null, nombresRoles, nombresRoles[1]);

                    if (nombreRol == null) {
                        return;
                    }

                    int rolId = rolesComboBoxModel.buscarIdRolPorNombre(nombreRol);

                    buscarEmpleadosPorRolId(rolId);
                }
            }
        });
        pnlAcciones.add(btnBuscar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (empleadoId == 0) {
                    JOptionPane.showMessageDialog(null, "Debe buscar o seleccionar un empleado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String sueldoTexto = txtSueldo.getText().trim();
                String rolNombre = ((Rol) cbxRol.getSelectedItem()).getNombre();
                int rolId = rolesComboBoxModel.buscarIdRolPorNombre(rolNombre);
                String correo = txtCorreo.getText().trim();
                String clave = new String(pwdClave.getPassword());
                String claveRepetir = new String(pwdClaveRepetir.getPassword());

                if (nombres.isEmpty() || apellidos.isEmpty() || sueldoTexto.isEmpty() || rolId == -1 || correo.isEmpty() || clave.isEmpty() || claveRepetir.isEmpty()) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "Debe llenar todos los campos", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double sueldo;

                try {
                    sueldo = Double.parseDouble(sueldoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "El sueldo debe ser un valor numérico.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (sueldo < 0) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "El sueldo debe ser mayor a cero.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!EmailValidator.getInstance().isValid(correo)) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "El correo no es válido", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!clave.equals(claveRepetir)) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "Las claves no coinciden", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Empleado empleado = new Empleado(empleadoId, nombres, apellidos, sueldo, rolId);

                boolean respuesta = gestorLavanderiaGUI.actualizarEmpleado(empleado);

                if (!respuesta) {
                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se pudo editar el empleado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Usuario usuario = gestorLavanderiaGUI.obtenerUsuarioPorEmpleadoId(empleadoId);

                if (!usuario.getCorreo().equals(correo)) {
                    respuesta = gestorLavanderiaGUI.actualizarUsuarioCorreo(empleadoId, correo);

                    if (!respuesta) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se pudo editar el correo del usuario.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (!clave.equals(CLAVE) && !claveRepetir.equals(CLAVE)) {
                    clave = Utilidad.encriptar(clave);
                    respuesta = gestorLavanderiaGUI.actualizarUsuarioClave(empleadoId, clave);

                    if (!respuesta) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se pudo editar la clave del usuario.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "Empleado correctamente editado.", "Información", JOptionPane.INFORMATION_MESSAGE);

                limpiarCampos();
                List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();
                cargarEmpleados(empleados);
            }
        });
        pnlAcciones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (empleadoId == 0) {
                    JOptionPane.showMessageDialog(null, "Debe buscar o seleccionar un empleado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int respuesta = JOptionPane.showConfirmDialog(EmpleadoUsuarioFrame.this, "¿Está seguro de eliminar el empleado?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    boolean eliminado = gestorLavanderiaGUI.eliminarEmpleadoPorId(empleadoId);

                    if (!eliminado) {
                        JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se pudo eliminar el empleado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "Empleado correctamente eliminado.", "Información", JOptionPane.INFORMATION_MESSAGE);

                    limpiarCampos();
                    List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();
                    cargarEmpleados(empleados);
                }
            }
        });
        pnlAcciones.add(btnEliminar);

        JPanel pnlRegistros = new JPanel();
        pnlRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlRegistros, BorderLayout.SOUTH);
        pnlRegistros.setLayout(new BorderLayout(0, 0));

        JScrollPane spnRegistros = new JScrollPane();
        pnlRegistros.add(spnRegistros, BorderLayout.CENTER);

        tblRegistros = new JTable();
        tblRegistros.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID", "Nombre completo", "Sueldo", "Rol", "Correo"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class, Double.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        spnRegistros.setViewportView(tblRegistros);
        tblRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblRegistros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                int selectedRow = tblRegistros.getSelectedRow();

                if (selectedRow == -1) {
                    return;
                }

                empleadoId = (int) tblRegistros.getValueAt(selectedRow, 0);
                Empleado empleado = gestorLavanderiaGUI.obtenerEmpleadoPorId(empleadoId);
                mostrarEmpleado(empleado);
            }
        });

        cargarRoles();

        List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();
        cargarEmpleados(empleados);
    }

    private void buscarEmpleadosPorRolId(int rolId) {
        List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleadosPorRolId(rolId);

        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(EmpleadoUsuarioFrame.this, "No se encontró ningún empleado con el rol dado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            cargarEmpleados(empleados);
            return;
        }

        cargarEmpleados(empleados);
    }

    private void mostrarEmpleado(Empleado empleado) {
        txtId.setText(String.valueOf(empleado.getId()));
        txtNombres.setText(empleado.getNombres());
        txtApellidos.setText(empleado.getApellidos());
        txtSueldo.setText(String.valueOf(empleado.getSueldo()));

        int indiceRol = rolesComboBoxModel.buscarIndiceDelRol(empleado.getRolId());
        cbxRol.setSelectedIndex(indiceRol);
        cbxRol.repaint();

        Usuario usuario = gestorLavanderiaGUI.obtenerUsuarioPorEmpleadoId(empleado.getId());

        txtCorreo.setText(usuario.getCorreo());
        pwdClave.setText(CLAVE);
        pwdClaveRepetir.setText(CLAVE);
    }

    private void cargarEmpleados(List<Empleado> empleados) {
        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();
        modelo.setRowCount(0);

        List<Rol> roles = gestorLavanderiaGUI.obtenerRoles();
        List<Usuario> usuarios = gestorLavanderiaGUI.obtenerUsuarios();

        for (Empleado empleado : empleados) {
            Rol rol = roles.stream().filter(r -> r.getId() == empleado.getRolId()).findFirst().orElse(null);
            Usuario usuario = usuarios.stream().filter(u -> u.getEmpleadoId() == empleado.getId()).findFirst().orElse(null);

            modelo.addRow(new Object[]{
                    empleado.getId(),
                    empleado.getNombres() + " " + empleado.getApellidos(),
                    empleado.getSueldo(),
                    rol != null ? rol.getNombre() : "",
                    usuario != null ? usuario.getCorreo() : ""
            });
        }
    }

    private void limpiarCampos() {
        empleadoId = 0;
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtSueldo.setText("");
        cbxRol.setSelectedIndex(0);
        txtCorreo.setText("");
        pwdClave.setText("");
        pwdClaveRepetir.setText("");
    }

    private void cargarRoles() {
        List<Rol> roles = gestorLavanderiaGUI.obtenerRoles();

        Rol[] rolesArreglo = roles.toArray(new Rol[roles.size()]);

        rolesComboBoxModel = new RolesComboBoxModel(rolesArreglo);

        cbxRol.setModel(rolesComboBoxModel);

        cbxRol.setSelectedIndex(0);
    }

}
