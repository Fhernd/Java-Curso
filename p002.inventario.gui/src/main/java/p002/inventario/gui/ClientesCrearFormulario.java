package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Cliente;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
					ClientesCrearFormulario frame = new ClientesCrearFormulario(null);
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
	public ClientesCrearFormulario(Aplicacion aplicacion) {
		setClosable(true);
		setTitle("Clientes - Crear");
		setBounds(100, 100, 450, 300);

		JPanel pnlClientesCrearDatos = new JPanel();
		pnlClientesCrearDatos
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlClientesCrearDatos, BorderLayout.CENTER);
		pnlClientesCrearDatos.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

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
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText().strip();
				String nombres = txtNombres.getText().strip();
				String apellidos = txtApellidos.getText().strip();
				String telefono = txtTelefono.getText().strip();
				String direccion = txtDireccion.getText().strip();
				String correoElectronico = txtCorreoElectronico.getText().strip();

				if (cedula.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Cédula es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Integer numero = IntegerValidator.getInstance().validate(cedula);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Cédula debe ser un número.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"El campo Cédula debe ser un número positivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Cliente cliente = aplicacion.buscarClientePorCedula(cedula);
				
				if (cliente != null) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"Ya existe un cliente con la cédula especificada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (nombres.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Nombres es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (apellidos.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Apellidos es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (telefono.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Teléfono es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (telefono.length() != 10) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"El número de Teléfono debe tener 10 dígitos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				numero = IntegerValidator.getInstance().validate(telefono);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Teléfono debe ser un número.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"El campo Teléfono debe ser un número positivo.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (direccion.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this, "El campo Dirección es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (correoElectronico.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"El campo Correo electrónico es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!EmailValidator.getInstance().isValid(correoElectronico)) {
					JOptionPane.showMessageDialog(ClientesCrearFormulario.this,
							"El valor del campo Correo electrónico no es válido.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				cliente = new Cliente(cedula, nombres, apellidos, telefono, direccion, correoElectronico);
				
				
			}
		});
		pnlClientesCrearDatos.add(btnCrear, "8, 14");

		JButton btnLimpiar = new JButton("Limpiar");
		pnlClientesCrearDatos.add(btnLimpiar, "8, 16");

	}

}
