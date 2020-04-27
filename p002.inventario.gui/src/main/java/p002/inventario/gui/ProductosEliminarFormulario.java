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
import javax.swing.JButton;

public class ProductosEliminarFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 4902421048621265227L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the frame.
	 */
	public ProductosEliminarFormulario() {
		setTitle("Productos - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 340);
		
		JPanel pnlProductosEliminar = new JPanel();
		pnlProductosEliminar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosEliminar, BorderLayout.CENTER);
		pnlProductosEliminar.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		pnlProductosEliminar.add(textField, "12, 2, fill, default");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		pnlProductosEliminar.add(btnNewButton, "12, 4");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_1, "2, 6");
		
		textField_1 = new JTextField();
		pnlProductosEliminar.add(textField_1, "12, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_2, "2, 8");
		
		textField_2 = new JTextField();
		pnlProductosEliminar.add(textField_2, "12, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_3, "2, 10");
		
		textField_3 = new JTextField();
		pnlProductosEliminar.add(textField_3, "12, 10, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_4, "2, 12");
		
		textField_4 = new JTextField();
		pnlProductosEliminar.add(textField_4, "12, 12, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_5, "2, 14");
		
		textField_5 = new JTextField();
		pnlProductosEliminar.add(textField_5, "12, 14, fill, default");
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_6, "2, 16");
		
		textField_6 = new JTextField();
		pnlProductosEliminar.add(textField_6, "12, 16, fill, default");
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		pnlProductosEliminar.add(lblNewLabel_7, "2, 18");
		
		textField_7 = new JTextField();
		pnlProductosEliminar.add(textField_7, "12, 18, fill, default");
		textField_7.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		pnlProductosEliminar.add(btnNewButton_1, "12, 20");

	}

}
