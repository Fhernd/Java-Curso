package vistas;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import modelos.Empleado;
import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpleadoUsuarioFrame extends JInternalFrame {
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtSueldo;
    private JTextField txtCorreo;
    private JPasswordField pwdClave;
    private JPasswordField pwdClaveRepetir;
    private JTable tblRegistros;

    /**
     * Create the frame.
     */
    public EmpleadoUsuarioFrame() {
        setClosable(true);
        setTitle("Empleados");
        setBounds(100, 100, 600, 735);

        JPanel pnlDatos = new JPanel();
        pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlDatos, BorderLayout.NORTH);
        pnlDatos.setLayout(new FormLayout(new ColumnSpec[]{
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
                new RowSpec[]{
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

        JLabel lblNombres = new JLabel("Nombres:");
        pnlDatos.add(lblNombres, "2, 2, left, bottom");

        txtNombres = new JTextField();
        pnlDatos.add(txtNombres, "10, 2, fill, default");
        txtNombres.setColumns(10);

        JLabel lblApellidos = new JLabel("Apellidos:");
        pnlDatos.add(lblApellidos, "2, 4");

        txtApellidos = new JTextField();
        pnlDatos.add(txtApellidos, "10, 4, fill, default");
        txtApellidos.setColumns(10);

        JLabel lblSueldo = new JLabel("Sueldo:");
        pnlDatos.add(lblSueldo, "2, 6");

        txtSueldo = new JTextField();
        pnlDatos.add(txtSueldo, "10, 6, fill, default");
        txtSueldo.setColumns(10);

        JLabel lblRol = new JLabel("Rol:");
        pnlDatos.add(lblRol, "2, 8");

        JComboBox cbxRol = new JComboBox();
        pnlDatos.add(cbxRol, "10, 8, fill, default");

        JLabel lblCorreo = new JLabel("Correo:");
        pnlDatos.add(lblCorreo, "2, 10");

        txtCorreo = new JTextField();
        pnlDatos.add(txtCorreo, "10, 10, fill, default");
        txtCorreo.setColumns(10);

        JLabel lblClave = new JLabel("Clave:");
        pnlDatos.add(lblClave, "2, 12");

        pwdClave = new JPasswordField();
        pnlDatos.add(pwdClave, "10, 12, fill, default");

        JLabel lblClaveRepetir = new JLabel("Clave (repetir):");
        pnlDatos.add(lblClaveRepetir, "2, 14");

        pwdClaveRepetir = new JPasswordField();
        pnlDatos.add(pwdClaveRepetir, "10, 14, fill, default");

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
                int rolIndice = cbxRol.getSelectedIndex();
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

                String rol = cbxRol.getSelectedItem().toString();

                if (EmailValidator.getInstance().isValid(correo)) {
                    JOptionPane.showMessageDialog(null, "El correo no es válido.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Empleado empleado = new Empleado(nombres, apellidos, sueldo, -1);

                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        pnlAcciones.add(btnGuardar);

        JButton btnBuscar = new JButton("Buscar...");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Buscar un empleado usando su correo electrónico
            }
        });
        pnlAcciones.add(btnBuscar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Edita los datos de un empleado.
            }
        });
        pnlAcciones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Elimina un empleado seleccionado.
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
    }

}
