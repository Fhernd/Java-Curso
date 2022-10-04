package vistas;

import java.awt.EventQueue;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import org.apache.commons.validator.routines.EmailValidator;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
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
				String password = new String(txtPassword.getPassword());

				EmailValidator emailValidator = EmailValidator.getInstance();
				if (!emailValidator.isValid(email)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		panel.add(btnIniciarSesion, "8, 6");

	}

}
