package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.LongValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Proveedor;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProveedoresActualizarFormulario extends JInternalFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 6521643222418998497L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JButton btnActualizar;

	/**
	 * Create the frame.
	 */
	public ProveedoresActualizarFormulario(Aplicacion aplicacion) {
		setTitle("Proveedores - Actualizar");
		setClosable(true);
		setBounds(100, 100, 450, 230);
		
		JPanel pnlProveedoresBuscar = new JPanel();
		pnlProveedoresBuscar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProveedoresBuscar, BorderLayout.CENTER);
		pnlProveedoresBuscar.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblId = new JLabel("ID");
		pnlProveedoresBuscar.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProveedoresBuscar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this,
							"El campo ID debe ser un número entero.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this,
							"El campo ID debe ser un número entero positivo.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Proveedor proveedor = aplicacion.buscarProveedorPorId(numero);
				
				if (proveedor == null) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this,
							"No existe un proveedor con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					
					txtNombre.setText("");
					txtNombre.setEnabled(false);
					txtDireccion.setText("");
					txtDireccion.setEnabled(false);
					txtTelefono.setText("");
					txtTelefono.setEnabled(false);
					btnActualizar.setEnabled(false);
					
					return;
				}
				
				txtNombre.setText(proveedor.getNombre());
				txtNombre.setEnabled(true);
				txtDireccion.setText(proveedor.getDireccion());
				txtDireccion.setEnabled(true);
				txtTelefono.setText(proveedor.getTelefono());
				txtTelefono.setEnabled(true);
				btnActualizar.setEnabled(true);
				txtId.setEnabled(false);
			}
		});
		pnlProveedoresBuscar.add(btnBuscar, "12, 4");
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProveedoresBuscar.add(lblNombre, "2, 6");
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		pnlProveedoresBuscar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlProveedoresBuscar.add(lblDireccion, "2, 8");
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		pnlProveedoresBuscar.add(txtDireccion, "12, 8, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlProveedoresBuscar.add(lblTelefono, "2, 10");
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		pnlProveedoresBuscar.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				String nombre = txtNombre.getText().trim();
				String direccion = txtDireccion.getText().trim();
				
				Proveedor proveedor = aplicacion.buscarProveedorPorId(Long.parseLong(id));
				
				String telefono = txtTelefono.getText().trim();
				
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this, "El campo Nombre es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (direccion.isEmpty()) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this, "El campo Dirección es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (telefono.isEmpty()) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this, "El campo Teléfono es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (telefono.length() != 10) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this,
							"El campo Teléfono debe tener longitud 10.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(telefono);

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProveedoresActualizarFormulario.this,
							"El campo Teléfono debe ser un número positivo.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				proveedor.setNombre(nombre);
				proveedor.setDireccion(direccion);
				proveedor.setTelefono(telefono);
				
				aplicacion.actualizarProveedor(proveedor);
				
				txtId.setText("");
				txtId.setEnabled(true);
				txtNombre.setText("");
				txtNombre.setEnabled(false);
				txtDireccion.setText("");
				txtDireccion.setEnabled(false);
				txtTelefono.setText("");
				txtTelefono.setEnabled(false);
				btnActualizar.setEnabled(false);
			}
		});
		btnActualizar.setEnabled(false);
		pnlProveedoresBuscar.add(btnActualizar, "12, 12");

	}

}
