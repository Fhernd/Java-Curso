package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import modelos.Cliente;
import modelos.Direccion;
import modelos.GestorLavanderia;
import vistas.models.ClientesComboBoxModel;

import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class DireccionesFrame extends JInternalFrame {
    private JTextField txtDescripcion;
    private JTable tblRegistros;
    private GestorLavanderiaGUI gestorLavanderiaGUI;

    private JComboBox cbxClienteId;

    private ClientesComboBoxModel clientesComboBoxModel;

    private int direccionId = -1;

    /**
     * Create the frame.
     */
    public DireccionesFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setTitle("Direcciones");
        setMaximizable(true);
        setClosable(true);
        setBounds(100, 100, 550, 645);

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
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblDescripcion = new JLabel("Dirección:");
        pnlDatos.add(lblDescripcion, "2, 2");

        txtDescripcion = new JTextField();
        pnlDatos.add(txtDescripcion, "10, 2, fill, default");
        txtDescripcion.setColumns(10);

        JLabel lblClienteId = new JLabel("Cliente:");
        pnlDatos.add(lblClienteId, "2, 4");

        cbxClienteId = new JComboBox();
        pnlDatos.add(cbxClienteId, "10, 4, fill, default");

        JButton btnBuscarClientePordocumento = new JButton("Buscar cliente por documento...");
        btnBuscarClientePordocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String documento = JOptionPane.showInputDialog("Documento del cliente:");

                if (!documento.isEmpty()) {
                    Cliente cliente = gestorLavanderiaGUI.obtenerClientePorDocumento(documento);

                    if (cliente != null) {
                        int indice = clientesComboBoxModel.buscarIndiceDelCliente(documento);
                        cbxClienteId.setSelectedIndex(indice);
                        cbxClienteId.repaint();
                    } else {
                        JOptionPane.showMessageDialog(DireccionesFrame.this, "No se encontró el cliente con documento " + documento, "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        pnlDatos.add(btnBuscarClientePordocumento, "10, 6, default, bottom");

        JPanel pnlBotones = new JPanel();
        pnlBotones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlBotones, BorderLayout.CENTER);
        pnlBotones.setLayout(new GridLayout(0, 5, 0, 0));

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtDescripcion.getText().trim();
                int indice = cbxClienteId.getSelectedIndex();

                if (!descripcion.isEmpty() || indice > 0) {
                    int respuesta = JOptionPane.showConfirmDialog(DireccionesFrame.this, "¿Desea quitar los datos ya escritos?", "Mensaje", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {
                        txtDescripcion.setText("");
                        cbxClienteId.setSelectedIndex(0);
                        cbxClienteId.repaint();
                    }
                }

                txtDescripcion.requestFocus();
            }
        });
        pnlBotones.add(btnNuevo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtDescripcion.getText().trim();
                Cliente cliente = (Cliente) cbxClienteId.getSelectedItem();

                if (descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "Debe ingresar una descripción para la dirección", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    txtDescripcion.requestFocus();
                    return;
                }

                Direccion direccion = new Direccion();
                direccion.setDescripcion(descripcion);
                direccion.setClienteId(cliente.getId());

                if (gestorLavanderiaGUI.crearDireccion(direccion) != null) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "Dirección guardada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    txtDescripcion.setText("");
                    cbxClienteId.setSelectedIndex(0);
                    cbxClienteId.repaint();
                    txtDescripcion.requestFocus();

                    List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDirecciones();

                    cargarDirecciones(direcciones);
                } else {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "No se pudo guardar la dirección", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pnlBotones.add(btnGuardar);

        JButton btnBuscar = new JButton("Buscar direcciones");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Buscar por el documento del cliente:
                String documento = JOptionPane.showInputDialog("Documento del cliente:").trim();

                if (!documento.isEmpty()) {
                    Cliente cliente = gestorLavanderiaGUI.obtenerClientePorDocumento(documento);

                    if (cliente != null) {
                        int indice = clientesComboBoxModel.buscarIndiceDelCliente(documento);
                        cbxClienteId.setSelectedIndex(indice);
                        cbxClienteId.repaint();

                        List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDireccionesPorClienteId(cliente.getId());

                        if (direcciones.isEmpty()) {
                            borrarRegistrosDirecciones();
                            JOptionPane.showMessageDialog(DireccionesFrame.this, "No se encontraron direcciones para el cliente con documento " + documento, "Mensaje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        cargarDirecciones(direcciones);
                    } else {
                        JOptionPane.showMessageDialog(DireccionesFrame.this, "No se encontró el cliente con documento " + documento, "Mensaje", JOptionPane.WARNING_MESSAGE);
                        borrarRegistrosDirecciones();
                    }
                }
            }
        });
        pnlBotones.add(btnBuscar);

        JButton btnEditar = new JButton(" Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (direccionId == -1) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "No ha seleccionado una dirección para edición.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String descripcion = txtDescripcion.getText().trim();
                Cliente cliente = (Cliente) cbxClienteId.getSelectedItem();

                if (descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "Debe ingresar una descripción para la dirección.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    txtDescripcion.requestFocus();
                    return;
                }

                Direccion direccion = new Direccion();
                direccion.setId(direccionId);
                direccion.setDescripcion(descripcion);
                direccion.setClienteId(cliente.getId());

                if (gestorLavanderiaGUI.actualizarDireccion(direccion)) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "Dirección editada correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    txtDescripcion.setText("");
                    cbxClienteId.setSelectedIndex(0);
                    cbxClienteId.repaint();
                    txtDescripcion.requestFocus();

                    List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDirecciones();

                    cargarDirecciones(direcciones);
                } else {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "No se pudo editar la dirección.", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pnlBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (direccionId == -1) {
                    JOptionPane.showMessageDialog(DireccionesFrame.this, "No ha seleccionado una dirección para eliminar.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (JOptionPane.showConfirmDialog(DireccionesFrame.this, "¿Está seguro de eliminar la dirección seleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (gestorLavanderiaGUI.eliminarDireccionPorId(direccionId)) {
                        JOptionPane.showMessageDialog(DireccionesFrame.this, "Dirección eliminada correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        txtDescripcion.setText("");
                        cbxClienteId.setSelectedIndex(0);
                        cbxClienteId.repaint();
                        txtDescripcion.requestFocus();

                        List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDirecciones();

                        cargarDirecciones(direcciones);
                    } else {
                        JOptionPane.showMessageDialog(DireccionesFrame.this, "No se pudo eliminar la dirección.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        pnlBotones.add(btnEliminar);

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
                        "ID", "Direcci\u00F3n", "Cliente"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        spnRegistros.setViewportView(tblRegistros);
        tblRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Evento para cuando se selecciona una fila de la tabla:
        tblRegistros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                int filaSeleccionada = tblRegistros.getSelectedRow();

                if (filaSeleccionada != -1) {
                    direccionId = (int) tblRegistros.getValueAt(filaSeleccionada, 0);
                    String descripcion = (String) tblRegistros.getValueAt(filaSeleccionada, 1);

                    Direccion direccion = gestorLavanderiaGUI.obtenerDireccionPorId(direccionId);

                    txtDescripcion.setText(descripcion);
                    cbxClienteId.setSelectedIndex(clientesComboBoxModel.buscarIndiceDelCliente(direccion.getClienteId()));
                    cbxClienteId.repaint();
                }
            }
        });

        cargarClientes();

        List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDirecciones();

        cargarDirecciones(direcciones);
    }

    private void borrarRegistrosDirecciones() {
        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();
        modelo.setRowCount(0);
    }

    /**
     * Carga todas las direcciones de la base de datos en la tabla de registros.
     */
    private void cargarDirecciones(List<Direccion> direcciones) {
        DefaultTableModel modelo = (DefaultTableModel) tblRegistros.getModel();
        modelo.setRowCount(0);

        List<Cliente> clientes = gestorLavanderiaGUI.obtenerClientes();

        for (Direccion direccion : direcciones) {
            Cliente cliente = clientes.stream().filter(c -> c.getId() == direccion.getClienteId()).findFirst().get();
            modelo.addRow(new Object[]{direccion.getId(), direccion.getDescripcion(), String.format("%s %s (%s)", cliente.getNombres(), cliente.getApellidos(), cliente.getDocumento())});
        }
    }

    /**
     * Carga los clientes en el combo box.
     */
    private void cargarClientes() {
        List<Cliente> clientes = gestorLavanderiaGUI.obtenerClientes();

        Cliente[] clientesArray = clientes.toArray(new Cliente[clientes.size()]);

        clientesComboBoxModel = new ClientesComboBoxModel(clientesArray);

        cbxClienteId.setModel(clientesComboBoxModel);

        cbxClienteId.setSelectedIndex(0);
    }
}
