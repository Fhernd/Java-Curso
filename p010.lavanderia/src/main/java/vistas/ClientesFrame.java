package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import modelos.Cliente;
import modelos.TipoDocumento;
import org.apache.commons.validator.routines.EmailValidator;
import vistas.models.CustomComboBoxModel;

public class ClientesFrame extends JInternalFrame {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = -2341366357765049257L;
    private JTextField txtDocumento;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTable tblRegistros;

    private JSpinner spnId;

    private JComboBox cbxTipoDocumento;

    private GestorLavanderiaGUI gestorLavanderiaGUI;

    private boolean clienteEncontrado = false;


    /**
     * Create the frame.
     *
     * @param gestorLavanderiaGUI Gestor de la aplicación.
     */
    public ClientesFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setTitle("Clientes");
        setBounds(100, 100, 500, 710);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setClosable(true);

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
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblId = new JLabel("ID:");
        pnlDatos.add(lblId, "2, 2");

        spnId = new JSpinner();
        pnlDatos.add(spnId, "12, 2");

        JLabel lblDocumento = new JLabel("Documento:");
        pnlDatos.add(lblDocumento, "2, 4");

        txtDocumento = new JTextField();
        pnlDatos.add(txtDocumento, "12, 4, fill, default");
        txtDocumento.setColumns(10);

        JLabel lblNombres = new JLabel("Nombres:");
        pnlDatos.add(lblNombres, "2, 6");

        txtNombres = new JTextField();
        pnlDatos.add(txtNombres, "12, 6, fill, default");
        txtNombres.setColumns(10);

        JLabel lblApellidos = new JLabel("Apellidos:");
        pnlDatos.add(lblApellidos, "2, 8");

        txtApellidos = new JTextField();
        pnlDatos.add(txtApellidos, "12, 8, fill, default");
        txtApellidos.setColumns(10);

        JLabel lblCorreo = new JLabel("Correo:");
        pnlDatos.add(lblCorreo, "2, 10");

        txtCorreo = new JTextField();
        pnlDatos.add(txtCorreo, "12, 10, fill, default");
        txtCorreo.setColumns(10);

        JLabel lblTipoDocumento = new JLabel("Tipo documento:");
        pnlDatos.add(lblTipoDocumento, "2, 12");

        cbxTipoDocumento = new JComboBox();
        pnlDatos.add(cbxTipoDocumento, "12, 12, fill, default");

        JPanel pnlBotones = new JPanel();
        pnlBotones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlBotones, BorderLayout.CENTER);
        pnlBotones.setLayout(new GridLayout(0, 5, 0, 0));

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hayDatosPresentes()) {
                    // Preguntar si desea limpiar los datos
                    int respuesta = JOptionPane.showConfirmDialog(null, "Hay datos escritos en uno o más campos, ¿desea limpiar los campos?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        limpiarCampos();
                    }
                } else {
                    limpiarCampos();
                }
            }
        });
        pnlBotones.add(btnNuevo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String documento = txtDocumento.getText().trim();
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();

                if (documento.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Integer.parseInt(documento);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El documento debe ser un número entero", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (Integer.parseInt(documento) <= 0) {
                    JOptionPane.showMessageDialog(null, "El documento debe ser un número positivo", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (gestorLavanderiaGUI.obtenerClientePorDocumento(documento) != null) {
                    JOptionPane.showMessageDialog(null, "Ya existe un cliente con el documento " + documento, "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                EmailValidator validator = EmailValidator.getInstance();
                if (!validator.isValid(correo)) {
                    JOptionPane.showMessageDialog(null, "El correo no es válido", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                TipoDocumento tipoDocumento = (TipoDocumento) cbxTipoDocumento.getSelectedItem();

                Cliente cliente = new Cliente();
                cliente.setDocumento(documento);
                cliente.setNombres(nombres);
                cliente.setApellidos(apellidos);
                cliente.setCorreo(correo);
                cliente.setTipoDocumentoId(tipoDocumento.getId());

                if (gestorLavanderiaGUI.crearCliente(cliente) != null) {
                    JOptionPane.showMessageDialog(null, "Cliente creado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();

                    agregarRegistroClienteTabla(cliente);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el cliente. Intente otra vez.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pnlBotones.add(btnGuardar);

        JButton btnBuscar = new JButton("Buscar...");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Mostrar un diálogo para capturar el documento del cliente:
                String documento = JOptionPane.showInputDialog(ClientesFrame.this, "Ingrese el documento del cliente", "Buscar cliente", JOptionPane.QUESTION_MESSAGE).trim();

                if (!documento.isEmpty()) {
                    try {
                        Integer.parseInt(documento);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ClientesFrame.this, "El documento debe ser un número entero", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (Integer.parseInt(documento) <= 0) {
                        JOptionPane.showMessageDialog(ClientesFrame.this, "El documento debe ser un número positivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Cliente cliente = gestorLavanderiaGUI.obtenerClientePorDocumento(documento);

                    if (cliente != null) {
                        clienteEncontrado = true;
                        spnId.setValue(cliente.getId());
                        spnId.setEnabled(false);
                        txtDocumento.setText(cliente.getDocumento());
                        txtNombres.setText(cliente.getNombres());
                        txtApellidos.setText(cliente.getApellidos());
                        txtCorreo.setText(cliente.getCorreo());

                        for (int i = 0; i < cbxTipoDocumento.getItemCount(); i++) {
                            TipoDocumento tipoDocumento = (TipoDocumento) cbxTipoDocumento.getItemAt(i);
                            if (tipoDocumento.getId() == cliente.getTipoDocumentoId()) {
                                cbxTipoDocumento.setSelectedIndex(i);
                                cbxTipoDocumento.repaint();
                                break;
                            }
                        }
                    } else {
                        clienteEncontrado = false;
                        JOptionPane.showMessageDialog(null, "No existe un cliente con el documento " + documento, "Warning", JOptionPane.WARNING_MESSAGE);
                        limpiarCampos();
                    }
                }
        	}
        });

        pnlBotones.add(btnBuscar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!clienteEncontrado) {
                    JOptionPane.showMessageDialog(null, "Debe buscar un cliente primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int clienteId = (int) spnId.getValue();
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();

                if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombres, Apellidos y Correo electrónico", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                EmailValidator validator = EmailValidator.getInstance();
                if (!validator.isValid(correo)) {
                    JOptionPane.showMessageDialog(null, "El correo no es válido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                TipoDocumento tipoDocumento = (TipoDocumento) cbxTipoDocumento.getSelectedItem();

                Cliente cliente = new Cliente();
                cliente.setId(clienteId);
                cliente.setNombres(nombres);
                cliente.setApellidos(apellidos);
                cliente.setCorreo(correo);
                cliente.setTipoDocumentoId(tipoDocumento.getId());

                if (gestorLavanderiaGUI.actualizarCliente(cliente)) {
                    JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    clienteEncontrado = false;
                    spnId.setEnabled(true);
                    spnId.setValue(0);
                    spnId.repaint();

                    cargarRegistrosClientes();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente. Intente otra vez.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        pnlBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar...");
        pnlBotones.add(btnEliminar);
        // Evento click para el botón eliminar:
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!clienteEncontrado) {
                    JOptionPane.showMessageDialog(null, "Debe buscar un cliente primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int clienteId = (int) spnId.getValue();

                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    if (gestorLavanderiaGUI.eliminarClientePorDocumento(txtDocumento.getText())) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                        clienteEncontrado = false;
                        spnId.setEnabled(true);
                        spnId.setValue(0);
                        spnId.repaint();

                        cargarRegistrosClientes();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente. Intente otra vez.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

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
                        "ID", "Documento", "Nombres", "Apellidos", "Correo", "Tipo documento"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        spnRegistros.setViewportView(tblRegistros);

        // Event handler for selected row:
        tblRegistros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblRegistros.getSelectedRow() != -1) {
                    int clienteId = (int) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0);
                    Cliente cliente = gestorLavanderiaGUI.obtenerClientePorId(clienteId);

                    if (cliente != null) {
                        spnId.setValue(cliente.getId());
                        spnId.setEnabled(false);
                        txtDocumento.setText(cliente.getDocumento());
                        txtNombres.setText(cliente.getNombres());
                        txtApellidos.setText(cliente.getApellidos());
                        txtCorreo.setText(cliente.getCorreo());

                        for (int i = 0; i < cbxTipoDocumento.getItemCount(); i++) {
                            TipoDocumento tipoDocumento = (TipoDocumento) cbxTipoDocumento.getItemAt(i);
                            if (tipoDocumento.getId() == cliente.getTipoDocumentoId()) {
                                cbxTipoDocumento.setSelectedIndex(i);
                                cbxTipoDocumento.repaint();
                                break;
                            }
                        }
                    }
                }
            }
        });

        cargarTiposDocumento();

        cargarRegistrosClientes();
    }

    private void agregarRegistroClienteTabla(Cliente cliente) {
        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();

        TipoDocumento[] tiposDocumento = gestorLavanderiaGUI.obtenerTiposDocumentos();

        modelo.addRow(new Object[]{cliente.getId(), cliente.getDocumento(), cliente.getNombres(), cliente.getApellidos(), cliente.getCorreo(), Arrays.stream(tiposDocumento).filter(tipoDocumento -> tipoDocumento.getId() == cliente.getTipoDocumentoId()).findFirst().get().getNombre()});
    }

    /**
     * Carga todos los registros de clientes en la tabla.
     */
    private void cargarRegistrosClientes() {
        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();
        modelo.setRowCount(0);

        TipoDocumento[] tiposDocumento = gestorLavanderiaGUI.obtenerTiposDocumentos();

        for (Cliente cliente : gestorLavanderiaGUI.obtenerClientes()) {
            modelo.addRow(new Object[]{cliente.getId(), cliente.getDocumento(), cliente.getNombres(), cliente.getApellidos(), cliente.getCorreo(), Arrays.stream(tiposDocumento).filter(tipoDocumento -> tipoDocumento.getId() == cliente.getTipoDocumentoId()).findFirst().get().getNombre()});
        }
    }

    private void cargarTiposDocumento() {
        TipoDocumento[] tiposDocumento = gestorLavanderiaGUI.obtenerTiposDocumentos();

        CustomComboBoxModel model = new CustomComboBoxModel(tiposDocumento);
        cbxTipoDocumento.setModel(model);

        cbxTipoDocumento.setSelectedIndex(0);
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        spnId.setValue(0);
        cbxTipoDocumento.setSelectedIndex(0);
    }

    protected boolean hayDatosPresentes() {
        return !txtDocumento.getText().trim().isEmpty() ||
                !txtNombres.getText().trim().isEmpty() ||
                !txtApellidos.getText().trim().isEmpty() ||
                !txtCorreo.getText().trim().isEmpty();
    }

}
