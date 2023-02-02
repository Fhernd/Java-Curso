package vistas;

import javax.swing.*;
import java.awt.BorderLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormSpecs;
import modelos.TipoAtencion;

import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class TipoAtencionFrame extends JInternalFrame {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2632599037283355071L;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTable tblRegistros;

    private GestorLavanderiaGUI gestorLavanderiaGUI;

    /**
     * Create the frame.
     */
    public TipoAtencionFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;
        setTitle("Tipo Atención");
        setClosable(true);
        setBounds(100, 100, 500, 600);

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
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblId = new JLabel("ID:");
        pnlDatos.add(lblId, "2, 2");

        txtId = new JTextField();
        txtId.setEditable(false);
        pnlDatos.add(txtId, "18, 2, fill, default");
        txtId.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nombre:");
        pnlDatos.add(lblNewLabel, "2, 4");

        txtNombre = new JTextField();
        pnlDatos.add(txtNombre, "18, 4, fill, default");
        txtNombre.setColumns(10);

        JPanel pnlAcciones = new JPanel();
        pnlAcciones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlAcciones, BorderLayout.CENTER);
        pnlAcciones.setLayout(new GridLayout(0, 3, 0, 0));

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();

            if (!nombre.isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(this, "¿Desea crear un nuevo registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    limpiarCampos();
                }
            } else {
                limpiarCampos();
            }
        });
        pnlAcciones.add(btnNuevo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            TipoAtencion tipoAtencion = new TipoAtencion();
            tipoAtencion.setNombre(nombre);

            tipoAtencion = gestorLavanderiaGUI.crearTipoAtencion(tipoAtencion);

            if (tipoAtencion == null) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el registro.", "Mensaje", JOptionPane.ERROR_MESSAGE);

                return;
            }

            JOptionPane.showMessageDialog(this, "Registro creado correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();
        });
        pnlAcciones.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> {
            // TODO: Implementar la funcionalidad para editar un registro.
        });
        pnlAcciones.add(btnEditar);

        JPanel pnlRegistros = new JPanel();
        pnlRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlRegistros, BorderLayout.SOUTH);
        pnlRegistros.setLayout(new BorderLayout(0, 0));

        JScrollPane spnRegistros = new JScrollPane();
        pnlRegistros.add(spnRegistros, BorderLayout.CENTER);

        tblRegistros = new JTable();
        tblRegistros.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null},
                },
                new String[]{
                        "ID", "Nombre"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        spnRegistros.setViewportView(tblRegistros);
        tblRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Listener para la selección de una fila de la tabla:
        tblRegistros.getSelectionModel().addListSelectionListener(e -> {
            if (tblRegistros.getSelectedRow() != -1) {
                btnEditar.setEnabled(true);
                int id = (int) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0);
                String nombre = (String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1);

                txtId.setText(String.valueOf(id));
                txtNombre.setText(nombre);
            }
        });

        mostrarRegistros();
    }

    /**
     * Limpia los campos de texto.
     */
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
    }

    /**
     * Muestra todos los registros de tipo atención en la tabla.
     */
    private void mostrarRegistros() {
        List<TipoAtencion> tipoAtenciones = gestorLavanderiaGUI.obtenerTiposAtencion();

        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();

        modelo.setRowCount(0);

        for (TipoAtencion tipoAtencion : tipoAtenciones) {
            modelo.addRow(new Object[]{
                    tipoAtencion.getId(),
                    tipoAtencion.getNombre()
            });
        }
    }
}
