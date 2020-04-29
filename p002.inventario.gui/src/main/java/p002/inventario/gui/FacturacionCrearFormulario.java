package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Cliente;
import modelos.Producto;

import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.validator.routines.IntegerValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacturacionCrearFormulario extends JInternalFrame {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 7287407346788257718L;
	private JTextField txtCedulaCliente;
	private JTextField txtImpuesto;
	private JTable tblProductos;
	private Map<String, Integer> mapaProductos;
	private JComboBox cbxProducto;
	private JButton btnGenerarFactura;

	private Aplicacion aplicacion;
	private List<Integer> idsProductos;

	/**
	 * Create the frame.
	 */
	public FacturacionCrearFormulario(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

		setTitle("Facturación - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);

		JPanel pnlFacturacionCrear = new JPanel();
		pnlFacturacionCrear
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlFacturacionCrear, BorderLayout.NORTH);
		pnlFacturacionCrear.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

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

		cbxProducto = new JComboBox();
		pnlFacturacionCrear.add(cbxProducto, "12, 6, fill, default");

		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productoSeleccionado = cbxProducto.getSelectedItem().toString();
				Integer idProducto = mapaProductos.get(productoSeleccionado); 
				
				Producto producto = aplicacion.buscarProductoPorId(idProducto.intValue());
				idsProductos.add(idProducto);
				
				DefaultTableModel dtm = (DefaultTableModel) tblProductos.getModel();
				
				dtm.addRow(new Object[] {idProducto, producto.getNombre(), producto.getPrecioVenta()});
				
				if (idsProductos.size() == 1) {
					btnGenerarFactura.setEnabled(true);
				}
			}
		});
		pnlFacturacionCrear.add(btnAgregarProducto, "12, 8");

		tblProductos = new JTable();
		tblProductos.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "ID", "Nombre", "Precio" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getContentPane().add(tblProductos, BorderLayout.CENTER);

		btnGenerarFactura = new JButton("Generar Factura");
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedulaCliente = txtCedulaCliente.getText().trim();
				String impuesto = txtImpuesto.getText().trim();
				
				if (cedulaCliente.isEmpty()) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Cédula Cliente es obligatorio.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				Integer numero = IntegerValidator.getInstance().validate(cedulaCliente);
				
				if (numero == null) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Cédula Cliente debe ser un número.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				if (numero > 0) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Cédula Cliente debe ser un número positivo.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				Cliente cliente = aplicacion.buscarClientePorCedula(cedulaCliente);
				
				if (cliente == null) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"No existe un cliente con el número de cédula especificado.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				if (impuesto.isEmpty()) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Impuesto es obligatorio.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				numero = IntegerValidator.getInstance().validate(impuesto);
				
				if (numero == null) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Impuesto debe ser un número.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				if (numero > 0) {
					JOptionPane.showMessageDialog(FacturacionCrearFormulario.this,
							"El campo Impuesto debe ser un número positivo.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				
			}
		});
		btnGenerarFactura.setEnabled(false);
		getContentPane().add(btnGenerarFactura, BorderLayout.SOUTH);

		mapaProductos = new HashMap<>();
		cargarProductos();

		if (mapaProductos.size() == 0) {
			JOptionPane.showMessageDialog(this,
					"No se puede crear una factura ya que no existe ningún producto. Cree uno primero", "Mensaje",
					JOptionPane.WARNING_MESSAGE);
			
			btnAgregarProducto.setEnabled(false);
			btnGenerarFactura.setEnabled(false);
		}
	}

	private void cargarProductos() {
		Producto[] copiaProductos = aplicacion.obtenerProductos();
		String contenido;

		for (Producto producto : copiaProductos) {
			contenido = String.format("%d - %s - $%.0f", producto.getId(), producto.getNombre(),
					producto.getPrecioVenta());

			cbxProducto.addItem(contenido);
			mapaProductos.put(contenido, producto.getId());
		}
	}

}
