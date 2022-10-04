package vistas;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import modelos.Rol;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmpleadoCrearFrame extends JInternalFrame {

	/**
	 * ID de clase.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtSueldo;
	private JTextField txtCorreo;
	private JPasswordField txtPassword;

	GestorLavanderiaGUI gestorLavanderiaGUI;

	/**
	 * Create the frame.
	 */
	public EmpleadoCrearFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
		this.gestorLavanderiaGUI = gestorLavanderiaGUI;

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
		
		JLabel lblNombres = new JLabel("Nombres:");
		pnlPrincipal.add(lblNombres, "2, 2");
		
		txtNombres = new JTextField();
		pnlPrincipal.add(txtNombres, "8, 2, fill, default");
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		pnlPrincipal.add(lblApellidos, "2, 4");
		
		txtApellidos = new JTextField();
		pnlPrincipal.add(txtApellidos, "8, 4, fill, default");
		txtApellidos.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		pnlPrincipal.add(lblSueldo, "2, 6");
		
		txtSueldo = new JTextField();
		pnlPrincipal.add(txtSueldo, "8, 6, fill, default");
		txtSueldo.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol:");
		pnlPrincipal.add(lblRol, "2, 8");
		
		JComboBox cbxRoles = new JComboBox();
		pnlPrincipal.add(cbxRoles, "8, 8, fill, default");
		
		JLabel lblCorreo = new JLabel("Correo:");
		pnlPrincipal.add(lblCorreo, "2, 10");
		
		txtCorreo = new JTextField();
		pnlPrincipal.add(txtCorreo, "8, 10, fill, default");
		txtCorreo.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		pnlPrincipal.add(lblPassword, "2, 12, right, top");
		
		txtPassword = new JPasswordField();
		pnlPrincipal.add(txtPassword, "8, 12, fill, default");
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pnlPrincipal.add(btnCrear, "8, 14");

		cargarRoles(cbxRoles);
	}

	private void cargarRoles(JComboBox cbxRoles) {
		List<Rol> roles = gestorLavanderiaGUI.getRoles();
		for (Rol rol : roles) {
			cbxRoles.addItem(rol.getNombre());
		}
	}

}
