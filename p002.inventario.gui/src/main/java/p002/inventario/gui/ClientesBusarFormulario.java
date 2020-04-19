package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ClientesBusarFormulario extends JInternalFrame {
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesBusarFormulario frame = new ClientesBusarFormulario();
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
	public ClientesBusarFormulario() {
		setBounds(100, 100, 450, 260);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCedula, "2, 2, left, default");
		
		txtCedula = new JTextField();
		panel.add(txtCedula, "12, 2, fill, default");
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar, "12, 4");
		
		JLabel lblNombres = new JLabel("Nombres");
		panel.add(lblNombres, "2, 6");
		
		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		panel.add(txtNombres, "12, 6, fill, default");
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		panel.add(lblApellidos, "2, 8");
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		panel.add(txtApellidos, "12, 8, fill, default");
		txtApellidos.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		panel.add(lblTelefono, "2, 10");
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		panel.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		panel.add(lblDireccion, "2, 12");
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		panel.add(txtDireccion, "12, 12, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electrónico");
		panel.add(lblCorreoElectronico, "2, 14");
		
		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setEditable(false);
		panel.add(txtCorreoElectronico, "12, 14, fill, default");
		txtCorreoElectronico.setColumns(10);

	}

}
