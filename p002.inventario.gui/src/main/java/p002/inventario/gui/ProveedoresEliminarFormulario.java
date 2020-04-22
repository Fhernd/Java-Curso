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

public class ProveedoresEliminarFormulario extends JInternalFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 6326645294099341817L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Create the frame.
	 */
	public ProveedoresEliminarFormulario(Aplicacion aplicacion) {
		setTitle("Proveedores - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 230);
		
		JPanel pnlProveedoresEliminar = new JPanel();
		pnlProveedoresEliminar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProveedoresEliminar, BorderLayout.CENTER);
		pnlProveedoresEliminar.setLayout(new FormLayout(new ColumnSpec[] {
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
		pnlProveedoresEliminar.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProveedoresEliminar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String id = txtId.getText().trim();
				
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProveedoresEliminarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProveedoresEliminarFormulario.this,
							"El campo ID debe ser un número entero.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProveedoresEliminarFormulario.this,
							"El campo ID debe ser un número entero positivo.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Proveedor proveedor = aplicacion.buscarProveedorPorId(numero);
				
				if (proveedor == null) {
					JOptionPane.showMessageDialog(ProveedoresEliminarFormulario.this,
							"No existe un proveedor con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					
					txtNombre.setText("");
					txtDireccion.setText("");
					txtTelefono.setText("");
					
					return;
				}
				
				// TODO: Validar que el proveedor no esté asociado a un producto.
				
				txtNombre.setText(proveedor.getNombre());
				txtDireccion.setText(proveedor.getDireccion());
				txtTelefono.setText(proveedor.getTelefono());
			}
		});
		pnlProveedoresEliminar.add(btnBuscar, "12, 4");
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProveedoresEliminar.add(lblNombre, "2, 6");
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		pnlProveedoresEliminar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlProveedoresEliminar.add(lblDireccion, "2, 8");
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		pnlProveedoresEliminar.add(txtDireccion, "12, 8, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlProveedoresEliminar.add(lblTelefono, "2, 10");
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		pnlProveedoresEliminar.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		pnlProveedoresEliminar.add(btnEliminar, "12, 12");

	}

}
