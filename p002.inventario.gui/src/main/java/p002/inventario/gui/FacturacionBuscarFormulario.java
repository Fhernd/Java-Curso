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
import javax.swing.table.DefaultTableModel;

public class FacturacionBuscarFormulario extends JInternalFrame {

	

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 8397940004357463819L;
	private JTextField txtIdFactura;
	private JTextField txtCliente;
	private JTextField txtImpuesto;
	private JTextField txtTotal;
	private JTable tblProductos;

	/**
	 * Create the frame.
	 */
	public FacturacionBuscarFormulario() {
		setTitle("Facturación - Buscar");
		setClosable(true);
		setBounds(100, 100, 450, 388);
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
		
		JLabel lblIdFactura = new JLabel("ID Factura");
		pnlFacturacionBuscar.add(lblIdFactura, "2, 2");
		
		txtIdFactura = new JTextField();
		pnlFacturacionBuscar.add(txtIdFactura, "12, 2, fill, default");
		txtIdFactura.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlFacturacionBuscar.add(btnBuscar, "12, 4");
		
		JLabel lblCliente = new JLabel("Cliente");
		pnlFacturacionBuscar.add(lblCliente, "2, 6");
		
		txtCliente = new JTextField();
		txtCliente.setEnabled(false);
		pnlFacturacionBuscar.add(txtCliente, "12, 6, fill, default");
		txtCliente.setColumns(10);
		
		JLabel lblImpuesto = new JLabel("Impuesto");
		pnlFacturacionBuscar.add(lblImpuesto, "2, 8");
		
		txtImpuesto = new JTextField();
		txtImpuesto.setEnabled(false);
		pnlFacturacionBuscar.add(txtImpuesto, "12, 8, fill, default");
		txtImpuesto.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		pnlFacturacionBuscar.add(lblTotal, "2, 10");
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		pnlFacturacionBuscar.add(txtTotal, "12, 10, fill, default");
		txtTotal.setColumns(10);
		
		tblProductos = new JTable();
		tblProductos.setModel(new DefaultTableModel(
			new Object[][] {
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
		tblProductos.setBounds(10, 350, 414, -145);
		getContentPane().add(tblProductos);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(10, 182, 84, 14);
		getContentPane().add(lblProductos);

	}
}
