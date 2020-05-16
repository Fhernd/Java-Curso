package gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Cliente;
import modelos.Factura;
import modelos.Producto;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.validator.routines.IntegerValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
	public FacturacionBuscarFormulario(Aplicacion aplicacion) {
		setTitle("Facturación - Buscar");
		setClosable(true);
		setBounds(100, 100, 450, 388);
		getContentPane().setLayout(null);

		JPanel pnlFacturacionBuscar = new JPanel();
		pnlFacturacionBuscar
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFacturacionBuscar.setBounds(10, 11, 414, 160);
		getContentPane().add(pnlFacturacionBuscar);
		pnlFacturacionBuscar.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblIdFactura = new JLabel("ID Factura");
		pnlFacturacionBuscar.add(lblIdFactura, "2, 2");

		txtIdFactura = new JTextField();
		pnlFacturacionBuscar.add(txtIdFactura, "12, 2, fill, default");
		txtIdFactura.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idFactura = txtIdFactura.getText().trim();

				if (idFactura.isEmpty()) {
					JOptionPane.showMessageDialog(FacturacionBuscarFormulario.this,
							"El campo ID Factura es obligatorio.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Integer numero = IntegerValidator.getInstance().validate(idFactura);
				
				if (numero == null) {
					JOptionPane.showMessageDialog(FacturacionBuscarFormulario.this,
							"El campo ID Factura debe ser un número.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (numero <= 0) {
					JOptionPane.showMessageDialog(FacturacionBuscarFormulario.this,
							"El campo ID Factura debe ser un número positivo.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Factura factura = aplicacion.buscarFacturaPorId(numero.intValue());
				
				if (factura == null) {
					JOptionPane.showMessageDialog(FacturacionBuscarFormulario.this,
							"No existe una factura con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					
					txtCliente.setText("");
					txtImpuesto.setText("");
					DefaultTableModel dtm = (DefaultTableModel) tblProductos.getModel();
					dtm.setRowCount(0);
					
					return;
				}
				
				Cliente cliente = aplicacion.buscarClientePorCedula(factura.getCedulaCliente());
				txtCliente.setText(String.format("%s %s (%s)", cliente.getNombres(), cliente.getApellidos(), cliente.getCedula()));
				txtImpuesto.setText(String.format("%.2f", factura.getImpuesto()) + "%");
				
				Integer[] idsProductos = factura.getIdsProductos();
				DefaultTableModel dtm = (DefaultTableModel) tblProductos.getModel();
				Producto producto;
				
				for (Integer id : idsProductos) {
					producto = aplicacion.buscarProductoPorId(id);
					
					dtm.addRow(new Object[] {id, producto.getNombre(), producto.getPrecioVenta()});
				}
			}
		});
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

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(10, 182, 84, 14);
		getContentPane().add(lblProductos);
		
		JScrollPane spnProductos = new JScrollPane();
		spnProductos.setBounds(10, 207, 414, 140);
		getContentPane().add(spnProductos);
		
		tblProductos = new JTable();
		tblProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "ID", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Double.class
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
		tblProductos.getColumnModel().getColumn(1).setPreferredWidth(77);
		spnProductos.setViewportView(tblProductos);

	}
}
