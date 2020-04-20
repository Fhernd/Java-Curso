package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.IntegerValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Cliente;
import modelos.Factura;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ClientesEliminarFormulario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2235777285388677224L;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesEliminarFormulario frame = new ClientesEliminarFormulario(null);
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
	public ClientesEliminarFormulario(Aplicacion aplicacion) {
		setTitle("Clientes - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 300);

		JPanel pnlDatosCliente = new JPanel();
		pnlDatosCliente.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlDatosCliente, BorderLayout.CENTER);
		pnlDatosCliente.setLayout(new FormLayout(
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
		pnlDatosCliente.add(lblCedula, "2, 2");

		txtCedula = new JTextField();
		pnlDatosCliente.add(txtCedula, "12, 2, fill, default");
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText().strip();

				if (cedula.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesEliminarFormulario.this, "El campo Cédula es obligatorio.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Integer numero = IntegerValidator.getInstance().validate(cedula);

				if (numero == null) {
					JOptionPane.showMessageDialog(ClientesEliminarFormulario.this,
							"El campo Cédula debe ser un número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ClientesEliminarFormulario.this,
							"El campo Cédula debe ser un número positivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Cliente cliente = aplicacion.buscarClientePorCedula(cedula);

				if (cliente == null) {
					JOptionPane.showMessageDialog(ClientesEliminarFormulario.this,
							String.format("No existe un cliente con el número de cédula %s.", cedula), "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					txtNombres.setText("");
					txtApellidos.setText("");
					txtTelefono.setText("");
					txtDireccion.setText("");
					txtCorreoElectronico.setText("");
					return;
				}

				txtNombres.setText(cliente.getNombres());
				txtApellidos.setText(cliente.getApellidos());
				txtTelefono.setText(cliente.getTelefono());
				txtDireccion.setText(cliente.getDireccion());
				txtCorreoElectronico.setText(cliente.getCorreoElectronico());

				List<Factura> facturasCliente = aplicacion.buscarFacturasCliente(cedula);

				if (!facturasCliente.isEmpty()) {
					JOptionPane.showMessageDialog(ClientesEliminarFormulario.this,
							"No se puede eliminar este cliente ya que tiene uno o más facturas asociadas.",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}

				btnEliminar.setEnabled(true);
			}
		});
		pnlDatosCliente.add(btnBuscar, "12, 4");

		JLabel lblNombres = new JLabel("Nombres");
		pnlDatosCliente.add(lblNombres, "2, 6");

		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		pnlDatosCliente.add(txtNombres, "12, 6, fill, default");
		txtNombres.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		pnlDatosCliente.add(lblApellidos, "2, 8");

		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		pnlDatosCliente.add(txtApellidos, "12, 8, fill, default");
		txtApellidos.setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono");
		pnlDatosCliente.add(lblTelefono, "2, 10");

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		pnlDatosCliente.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);

		JLabel lblDireccion = new JLabel("Dirección");
		pnlDatosCliente.add(lblDireccion, "2, 12");

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		pnlDatosCliente.add(txtDireccion, "12, 12, fill, default");
		txtDireccion.setColumns(10);

		JLabel lblCorreoElectronico = new JLabel("Correo electrónico");
		pnlDatosCliente.add(lblCorreoElectronico, "2, 14");

		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setEditable(false);
		pnlDatosCliente.add(txtCorreoElectronico, "12, 14, fill, default");
		txtCorreoElectronico.setColumns(10);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resultado = JOptionPane.showConfirmDialog(ClientesEliminarFormulario.this,
						"¿Está seguro de querer eliminar este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);

				if (resultado == JOptionPane.YES_OPTION) {
					String cedula = txtCedula.getText();
					
					aplicacion.eliminarClientePorNumeroCedula(cedula);
					
					
				}
			}
		});
		btnEliminar.setEnabled(false);
		pnlDatosCliente.add(btnEliminar, "12, 16");

	}

}
