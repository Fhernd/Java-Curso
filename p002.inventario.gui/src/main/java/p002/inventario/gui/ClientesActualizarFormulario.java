package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
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

public class ClientesActualizarFormulario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1689370099348184457L;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;

	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesActualizarFormulario frame = new ClientesActualizarFormulario(null);
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
	public ClientesActualizarFormulario(Aplicacion aplicacion) {
		setClosable(true);
		setTitle("Clientes - Actualizar");
		setBounds(100, 100, 450, 300);

		JPanel pnlClientesActualizar = new JPanel();
		pnlClientesActualizar.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(pnlClientesActualizar, BorderLayout.CENTER);
		pnlClientesActualizar.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblCedula = new JLabel("Cédula");
		pnlClientesActualizar.add(lblCedula, "2, 2");

		txtCedula = new JTextField();
		pnlClientesActualizar.add(txtCedula, "12, 2, fill, default");
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText().strip();

				if (cedula.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this, "El campo Cédula es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Integer numero = IntegerValidator.getInstance().validate(cedula);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Cédula debe ser un número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Cédula debe ser un número positivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Cliente cliente = aplicacion.buscarClientePorCedula(cedula);

				if (cliente == null) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							String.format("No existe un cliente con el número de cédula %s.", cedula), "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					cambiarEstadoFormulario(false, null);
					return;
				}

				cambiarEstadoFormulario(true, cliente);
				txtCedula.setEditable(false);
			}
		});
		pnlClientesActualizar.add(btnBuscar, "12, 4");

		JLabel lblNombres = new JLabel("Nombres");
		pnlClientesActualizar.add(lblNombres, "2, 6");

		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		pnlClientesActualizar.add(txtNombres, "12, 6, fill, default");
		txtNombres.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		pnlClientesActualizar.add(lblApellidos, "2, 8");

		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		pnlClientesActualizar.add(txtApellidos, "12, 8, fill, default");
		txtApellidos.setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono");
		pnlClientesActualizar.add(lblTelefono, "2, 10");

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		pnlClientesActualizar.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);

		JLabel lblDireccion = new JLabel("Dirección");
		pnlClientesActualizar.add(lblDireccion, "2, 12");

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		pnlClientesActualizar.add(txtDireccion, "12, 12, fill, default");
		txtDireccion.setColumns(10);

		JLabel lblCorreoElectronico = new JLabel("Correo electrónico");
		pnlClientesActualizar.add(lblCorreoElectronico, "2, 14");

		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setEditable(false);
		pnlClientesActualizar.add(txtCorreoElectronico, "12, 14, fill, default");
		txtCorreoElectronico.setColumns(10);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText().strip();
				String nombres = txtNombres.getText().strip();
				String apellidos = txtApellidos.getText().strip();
				String telefono = txtTelefono.getText().strip();
				String direccion = txtDireccion.getText().strip();
				String correoElectronico = txtCorreoElectronico.getText().strip();

				if (cedula.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this, "El campo Cédula es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Integer numero = IntegerValidator.getInstance().validate(cedula);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Cédula debe ser un número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Cédula debe ser un número positivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (nombres.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this, "El campo Nombres es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (apellidos.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Apellidos es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (telefono.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Teléfono es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				numero = IntegerValidator.getInstance().validate(telefono);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Teléfono debe ser un número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Teléfono debe ser un número positivo.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (telefono.length() != 10) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El número de Teléfono debe tener 10 dígitos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (direccion.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Dirección es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (correoElectronico.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El campo Correo electrónico es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!EmailValidator.getInstance().isValid(correoElectronico)) {
					JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
							"El valor del campo Correo electrónico no es válido.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Cliente clienteActualizado = new Cliente(cedula, nombres, apellidos, telefono, direccion,
						correoElectronico);

				aplicacion.actualizarCliente(clienteActualizado);

				JOptionPane.showMessageDialog(ClientesActualizarFormulario.this,
						String.format("El cliente con cédula %s se ha actualizado de forma satisfactoria.", cedula),
						"Advertencia", JOptionPane.INFORMATION_MESSAGE);
				
				limpiarCampos();
			}
		});
		pnlClientesActualizar.add(btnActualizar, "12, 16");

	}

	protected void limpiarCampos() {
		txtCedula.setText("");
		txtCedula.setEditable(true);
		txtNombres.setText("");
		txtNombres.setEditable(false);
		txtApellidos.setText("");
		txtApellidos.setEditable(false);
		txtTelefono.setText("");
		txtTelefono.setEditable(false);
		txtDireccion.setText("");
		txtCorreoElectronico.setText("");
		txtCorreoElectronico.setEditable(false);
	}

	private void cambiarEstadoFormulario(boolean estado, Cliente cliente) {
		txtNombres.setText(estado ? cliente.getNombres() : "");
		txtNombres.setEditable(estado);
		txtApellidos.setText(estado ? cliente.getApellidos() : "");
		txtApellidos.setEditable(estado);
		txtTelefono.setText(estado ? cliente.getTelefono() : "");
		txtTelefono.setEditable(estado);
		txtDireccion.setText(estado ? cliente.getDireccion() : "");
		txtDireccion.setEditable(estado);
		txtCorreoElectronico.setText(estado ? cliente.getCorreoElectronico() : "");
		txtCorreoElectronico.setEditable(estado);
		btnActualizar.setEnabled(estado);
	}

}
