package vistas;

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
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class EmpleadoUsuarioFrame extends JInternalFrame {
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtSueldo;
	private JTextField txtCorreo;
	private JPasswordField pwdClave;
	private JPasswordField pwdClaveRepetir;

	/**
	 * Create the frame.
	 */
	public EmpleadoUsuarioFrame() {
		setClosable(true);
		setTitle("Empleados");
		setBounds(100, 100, 450, 300);
		
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

	}

}
