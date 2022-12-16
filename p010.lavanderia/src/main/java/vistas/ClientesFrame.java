package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import org.apache.commons.validator.routines.EmailValidator;

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
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea limpiar los campos?", "Confirmación", JOptionPane.YES_NO_OPTION);
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

				EmailValidator validator = EmailValidator.getInstance();
				if (!validator.isValid(correo)) {
					JOptionPane.showMessageDialog(null, "El correo no es válido", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
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
