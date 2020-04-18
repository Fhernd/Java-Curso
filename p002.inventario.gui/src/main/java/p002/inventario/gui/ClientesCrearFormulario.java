package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientesCrearFormulario extends JInternalFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 4454429000716379328L;
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
					ClientesCrearFormulario frame = new ClientesCrearFormulario();
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
	public ClientesCrearFormulario() {
		setClosable(true);
		setTitle("Clientes - Crear");
		setBounds(100, 100, 450, 300);
		
		JPanel pnlClientesCrearDatos = new JPanel();
		pnlClientesCrearDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlClientesCrearDatos, BorderLayout.CENTER);
		pnlClientesCrearDatos.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblCedula = new JLabel("Cédula");
		pnlClientesCrearDatos.add(lblCedula, "2, 2");
		
		txtCedula = new JTextField();
		pnlClientesCrearDatos.add(txtCedula, "8, 2, fill, default");
		txtCedula.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		pnlClientesCrearDatos.add(lblNombres, "2, 4");
		
		txtNombres = new JTextField();
		pnlClientesCrearDatos.add(txtNombres, "8, 4, fill, default");
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		pnlClientesCrearDatos.add(lblApellidos, "2, 6");
		
		txtApellidos = new JTextField();
		pnlClientesCrearDatos.add(txtApellidos, "8, 6, fill, default");
		txtApellidos.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlClientesCrearDatos.add(lblTelefono, "2, 8");
		
		txtTelefono = new JTextField();
		pnlClientesCrearDatos.add(txtTelefono, "8, 8, fill, default");
		txtTelefono.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlClientesCrearDatos.add(lblDireccion, "2, 10");
		
		txtDireccion = new JTextField();
		pnlClientesCrearDatos.add(txtDireccion, "8, 10, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electrónico");
		pnlClientesCrearDatos.add(lblCorreoElectronico, "2, 12");
		
		txtCorreoElectronico = new JTextField();
		pnlClientesCrearDatos.add(txtCorreoElectronico, "8, 12, fill, default");
		txtCorreoElectronico.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		pnlClientesCrearDatos.add(btnCrear, "8, 14");
		
		JButton btnLimpiar = new JButton("Limpiar");
		pnlClientesCrearDatos.add(btnLimpiar, "8, 16");

	}

}
