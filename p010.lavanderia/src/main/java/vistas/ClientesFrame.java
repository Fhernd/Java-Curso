package vistas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;

public class ClientesFrame extends JInternalFrame {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -2341366357765049257L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

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
		setBounds(100, 100, 450, 690);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.NORTH);
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
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_1.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Documento", "Nombres", "Apellidos", "Correo", "Tipo documento"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		
	}

}
