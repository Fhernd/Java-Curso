package vistas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesFrame frame = new ClientesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientesFrame() {
		setTitle("Clientes");
		setBounds(100, 100, 500, 710);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblId = new JLabel("ID:");
		pnlDatos.add(lblId, "2, 2");
		
		JSpinner spnId = new JSpinner();
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
		
		JComboBox cbxTipoDocumento = new JComboBox();
		pnlDatos.add(cbxTipoDocumento, "12, 12, fill, default");
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlBotones, BorderLayout.CENTER);
		pnlBotones.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNuevo = new JButton("Nuevo");
		pnlBotones.add(btnNuevo);
		
		JButton btnGuardar = new JButton("Guardar");
		pnlBotones.add(btnGuardar);
		
		JButton btnBuscar = new JButton("Buscar...");
		pnlBotones.add(btnBuscar);
		
		JButton btnEditar = new JButton("Editar");
		pnlBotones.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar...");
		pnlBotones.add(btnEliminar);
		
		JPanel pnlRegistros = new JPanel();
		pnlRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlRegistros, BorderLayout.SOUTH);
		pnlRegistros.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spnRegistros = new JScrollPane();
		pnlRegistros.add(spnRegistros, BorderLayout.CENTER);
		
		tblRegistros = new JTable();
		tblRegistros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Documento", "Nombres", "Apellidos", "Correo", "Tipo documento"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		spnRegistros.setViewportView(tblRegistros);
		
		
	}

}
