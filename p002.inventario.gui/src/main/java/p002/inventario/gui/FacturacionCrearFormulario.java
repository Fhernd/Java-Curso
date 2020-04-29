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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class FacturacionCrearFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 7287407346788257718L;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FacturacionCrearFormulario() {
		setTitle("Facturación - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlFacturacionCrear = new JPanel();
		pnlFacturacionCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlFacturacionCrear, BorderLayout.NORTH);
		pnlFacturacionCrear.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlFacturacionCrear.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		pnlFacturacionCrear.add(textField, "12, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		pnlFacturacionCrear.add(lblNewLabel_1, "2, 4");
		
		textField_1 = new JTextField();
		pnlFacturacionCrear.add(textField_1, "12, 4, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		pnlFacturacionCrear.add(lblNewLabel_2, "2, 6");
		
		JComboBox comboBox = new JComboBox();
		pnlFacturacionCrear.add(comboBox, "12, 6, fill, default");
		
		JButton btnNewButton = new JButton("New button");
		pnlFacturacionCrear.add(btnNewButton, "12, 8");
		
		table = new JTable();
		getContentPane().add(table, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("New button");
		getContentPane().add(btnNewButton_1, BorderLayout.SOUTH);

	}

}
