package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class ClientesFrame extends JInternalFrame {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -2341366357765049257L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesFrame frame = new ClientesFrame();
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
	public ClientesFrame() {
		setTitle("Clientes");
		setBounds(100, 100, 450, 610);
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblNewLabel = new JLabel("ID:");
		panel.add(lblNewLabel, "2, 2");
		
		JSpinner spinner = new JSpinner();
		panel.add(spinner, "12, 2");
		
		JLabel lblNewLabel_1 = new JLabel("Documento:");
		panel.add(lblNewLabel_1, "2, 4");
		
		textField = new JTextField();
		panel.add(textField, "12, 4, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombres:");
		panel.add(lblNewLabel_2, "2, 6");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "12, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos:");
		panel.add(lblNewLabel_3, "2, 8");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "12, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Correo:");
		panel.add(lblNewLabel_4, "2, 10");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "12, 10, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo documento:");
		panel.add(lblNewLabel_5, "2, 12");
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox, "12, 12, fill, default");

	}

}
