package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProveedoresEliminarFormulario extends JInternalFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 6326645294099341817L;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ProveedoresEliminarFormulario() {
		setTitle("Proveedores - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlProveedoresEliminar.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		pnlProveedoresEliminar.add(textField, "12, 2, fill, default");
		textField.setColumns(10);

	}

}
