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
import modelos.GestorLavanderia;

import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DireccionesFrame extends JInternalFrame {
    private JTextField txtDescripcion;
    private JTable tblRegistros;
    private GestorLavanderiaGUI gestorLavanderiaGUI;

    /**
     * Create the frame.
     */
    public DireccionesFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setTitle("Direcciones");
        setMaximizable(true);
        setClosable(true);
        setBounds(100, 100, 450, 630);

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

        JComboBox cbxClienteId = new JComboBox();
        pnlDatos.add(cbxClienteId, "10, 4, fill, default");

        JButton btnBuscarClientePordocumento = new JButton("Buscar cliente por documento...");
        btnBuscarClientePordocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String documento = JOptionPane.showInputDialog("Documento del cliente:");

                if (!documento.isEmpty()) {
                    Cliente cliente = gestorLavanderiaGUI.obtenerClientePorDocumento(documento);

                    if (cliente != null) {
//                        cbxClienteId.setSelectedItem(cliente);
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
                // TODO: Limpiar los campos del formulario
            }
        });
        pnlBotones.add(btnNuevo);

        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Guardar una nueva dirección en la base de datos
            }
        });
        pnlBotones.add(btnNewButton);

        JButton btnBuscar = new JButton("Buscar...");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Buscar una dirección de un cliente
            }
        });
        pnlBotones.add(btnBuscar);

        JButton btnEditar = new JButton(" Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Editar una dirección de un cliente
            }
        });
        pnlBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Eliminar una dirección de un cliente
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
    }

}
