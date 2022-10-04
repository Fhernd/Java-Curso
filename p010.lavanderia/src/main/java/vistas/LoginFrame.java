package vistas;

import java.awt.EventQueue;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import modelos.Usuario;
import org.apache.commons.validator.routines.EmailValidator;
import utilidades.Utilidad;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JInternalFrame {

	/**
	 * UID de la clase.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public LoginFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {

		setClosable(true);
		setTitle("Login");
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblEmail = new JLabel("Email:");
		panel.add(lblEmail, "2, 2");
		
		txtEmail = new JTextField();
		panel.add(txtEmail, "8, 2, fill, default");
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		panel.add(lblPassword, "2, 4");
		
		txtPassword = new JPasswordField();
		panel.add(txtPassword, "8, 4, fill, default");
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText().trim();
				String password = new String(txtPassword.getPassword()).trim();

				if (Utilidad.validarEmail(email)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(LoginFrame.this, "La contraseña no es válida. Debe tener al menos una letra en minúscula, mayúscula, un número, un carácter especial, y una longitud entre 8 y 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Usuario usuario = gestorLavanderiaGUI.iniciarSesion(email, password);

				if (usuario == null) {
					JOptionPane.showMessageDialog(LoginFrame.this, "El usuario no existe o la contraseña es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(LoginFrame.this, "¡Bienvenido!", "Información", JOptionPane.INFORMATION_MESSAGE);

				setVisible(false);

				gestorLavanderiaGUI.mostrarMenus();
			}
		});
		panel.add(btnIniciarSesion, "8, 6");

	}

}
