package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class FacturacionBuscarFormulario extends JInternalFrame {

	

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 8397940004357463819L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FacturacionBuscarFormulario() {
		setTitle("Facturación - Buscar");
		setClosable(true);
		setBounds(100, 100, 450, 380);
		getContentPane().setLayout(null);
		
		JPanel pnlFacturacionBuscar = new JPanel();
		pnlFacturacionBuscar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFacturacionBuscar.setBounds(10, 11, 414, 160);
		getContentPane().add(pnlFacturacionBuscar);
		pnlFacturacionBuscar.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlFacturacionBuscar.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		pnlFacturacionBuscar.add(textField, "12, 2, fill, default");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		pnlFacturacionBuscar.add(btnNewButton, "12, 4");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		pnlFacturacionBuscar.add(lblNewLabel_1, "2, 6");
		
		textField_1 = new JTextField();
		pnlFacturacionBuscar.add(textField_1, "12, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		pnlFacturacionBuscar.add(lblNewLabel_2, "2, 8");
		
		textField_2 = new JTextField();
		pnlFacturacionBuscar.add(textField_2, "12, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		pnlFacturacionBuscar.add(lblNewLabel_3, "2, 10");
		
		textField_3 = new JTextField();
		pnlFacturacionBuscar.add(textField_3, "12, 10, fill, default");
		textField_3.setColumns(10);
		
		table = new JTable();
		table.setBounds(10, 328, 414, -145);
		getContentPane().add(table);

	}
}
