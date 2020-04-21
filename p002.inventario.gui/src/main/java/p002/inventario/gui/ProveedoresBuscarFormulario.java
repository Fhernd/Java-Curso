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

public class ProveedoresBuscarFormulario extends JInternalFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -817070648471104551L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Create the frame.
	 */
	public ProveedoresBuscarFormulario(Aplicacion aplicacion) {
		setTitle("Proveedores - Buscar");
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
					JOptionPane.showMessageDialog(ProveedoresBuscarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProveedoresBuscarFormulario.this,
							"El campo ID debe ser un número entero.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProveedoresBuscarFormulario.this,
							"El campo ID debe ser un número entero positivo.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Proveedor proveedor = aplicacion.buscarProveedorPorId(numero);
				
				if (proveedor == null) {
					JOptionPane.showMessageDialog(ProveedoresBuscarFormulario.this,
							"Ya existe un proveedor con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					
					txtNombre.setText("");
					txtDireccion.setText("");
					txtTelefono.setText("");
					
					return;
				}
				
				txtNombre.setText(proveedor.getNombre());
				txtDireccion.setText(proveedor.getDireccion());
				txtTelefono.setText(proveedor.getTelefono());
			}
		});
		pnlProveedoresBuscar.add(btnBuscar, "12, 4");
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProveedoresBuscar.add(lblNombre, "2, 6");
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		pnlProveedoresBuscar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlProveedoresBuscar.add(lblDireccion, "2, 8");
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		pnlProveedoresBuscar.add(txtDireccion, "12, 8, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlProveedoresBuscar.add(lblTelefono, "2, 10");
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		pnlProveedoresBuscar.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);
	}

}
