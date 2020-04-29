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
import javax.swing.table.DefaultTableModel;

public class FacturacionCrearFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 7287407346788257718L;
	private JTextField txtCedulaCliente;
	private JTextField txtImpuesto;
	private JTable tblProductos;

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
		
		JLabel lblCedulaCliente = new JLabel("Cédula Cliente");
		pnlFacturacionCrear.add(lblCedulaCliente, "2, 2");
		
		txtCedulaCliente = new JTextField();
		pnlFacturacionCrear.add(txtCedulaCliente, "12, 2, fill, default");
		txtCedulaCliente.setColumns(10);
		
		JLabel lblImpuesto = new JLabel("Impuesto");
		pnlFacturacionCrear.add(lblImpuesto, "2, 4");
		
		txtImpuesto = new JTextField();
		pnlFacturacionCrear.add(txtImpuesto, "12, 4, fill, default");
		txtImpuesto.setColumns(10);
		
		JLabel lblProducto = new JLabel("Producto");
		pnlFacturacionCrear.add(lblProducto, "2, 6");
		
		JComboBox cbxProducto = new JComboBox();
		pnlFacturacionCrear.add(cbxProducto, "12, 6, fill, default");
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		pnlFacturacionCrear.add(btnAgregarProducto, "12, 8");
		
		tblProductos = new JTable();
		tblProductos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getContentPane().add(tblProductos, BorderLayout.CENTER);
		
		JButton btnGenerarFactura = new JButton("Generar Factura");
		getContentPane().add(btnGenerarFactura, BorderLayout.SOUTH);

	}

}
