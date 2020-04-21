package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ProveedoresCrearFormulario extends JInternalFrame {

	/**
	 * ID de versión de clase.
	 */
	private static final long serialVersionUID = 4875332889027488723L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Create the frame.
	 */
	public ProveedoresCrearFormulario(Aplicacion aplicacion) {
		setTitle("Proveedores - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 230);
		
		JPanel pnlProveedoresCrear = new JPanel();
		pnlProveedoresCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProveedoresCrear, BorderLayout.CENTER);
		pnlProveedoresCrear.setLayout(new FormLayout(new ColumnSpec[] {
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
		pnlProveedoresCrear.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProveedoresCrear.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProveedoresCrear.add(lblNombre, "2, 4");
		
		txtNombre = new JTextField();
		pnlProveedoresCrear.add(txtNombre, "12, 4, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlProveedoresCrear.add(lblDireccion, "2, 6");
		
		txtDireccion = new JTextField();
		pnlProveedoresCrear.add(txtDireccion, "12, 6, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlProveedoresCrear.add(lblTelefono, "2, 8");
		
		txtTelefono = new JTextField();
		pnlProveedoresCrear.add(txtTelefono, "12, 8, fill, default");
		txtTelefono.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		pnlProveedoresCrear.add(btnCrear, "12, 10");

	}

}
