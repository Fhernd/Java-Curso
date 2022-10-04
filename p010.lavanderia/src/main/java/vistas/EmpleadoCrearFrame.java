package vistas;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class EmpleadoCrearFrame extends JInternalFrame {

	/**
	 * ID de clase.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public EmpleadoCrearFrame() {
		setTitle("Empleado - Crear");
		setBounds(100, 100, 450, 270);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblNewLabel = new JLabel("Nombres:");
		pnlPrincipal.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		pnlPrincipal.add(textField, "8, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		pnlPrincipal.add(lblNewLabel_1, "2, 4");
		
		textField_1 = new JTextField();
		pnlPrincipal.add(textField_1, "8, 4, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		pnlPrincipal.add(lblNewLabel_2, "2, 6");
		
		textField_2 = new JTextField();
		pnlPrincipal.add(textField_2, "8, 6, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Rol:");
		pnlPrincipal.add(lblNewLabel_3, "2, 8");
		
		JComboBox comboBox = new JComboBox();
		pnlPrincipal.add(comboBox, "8, 8, fill, default");
		
		JLabel lblNewLabel_4 = new JLabel("Correo:");
		pnlPrincipal.add(lblNewLabel_4, "2, 10");
		
		textField_3 = new JTextField();
		pnlPrincipal.add(textField_3, "8, 10, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Contraseña:");
		pnlPrincipal.add(lblNewLabel_5, "2, 12, right, top");
		
		passwordField = new JPasswordField();
		pnlPrincipal.add(passwordField, "8, 12, fill, default");
		
		JButton btnNewButton = new JButton("Crear");
		pnlPrincipal.add(btnNewButton, "8, 14");

	}

}
