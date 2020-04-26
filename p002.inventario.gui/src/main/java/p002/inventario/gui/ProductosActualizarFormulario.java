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
import javax.swing.JComboBox;

public class ProductosActualizarFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = -8224002992324117103L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtCantidad;
	private JTextField txtCantidadMinimaStock;

	/**
	 * Create the frame.
	 */
	public ProductosActualizarFormulario() {
		setTitle("Productos - Actualizar");
		setClosable(true);
		setBounds(100, 100, 450, 340);
		
		JPanel pnlProductosActualizar = new JPanel();
		pnlProductosActualizar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosActualizar, BorderLayout.CENTER);
		pnlProductosActualizar.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblId = new JLabel("ID");
		pnlProductosActualizar.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProductosActualizar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlProductosActualizar.add(btnBuscar, "12, 4");
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProductosActualizar.add(lblNombre, "2, 6");
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		pnlProductosActualizar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		pnlProductosActualizar.add(lblDescripcion, "2, 8");
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		pnlProductosActualizar.add(txtDescripcion, "12, 8, fill, default");
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		pnlProductosActualizar.add(lblPrecioCompra, "2, 10");
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setEnabled(false);
		pnlProductosActualizar.add(txtPrecioCompra, "12, 10, fill, default");
		txtPrecioCompra.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		pnlProductosActualizar.add(lblPrecioVenta, "2, 12");
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEnabled(false);
		pnlProductosActualizar.add(txtPrecioVenta, "12, 12, fill, default");
		txtPrecioVenta.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		pnlProductosActualizar.add(lblCantidad, "2, 14");
		
		txtCantidad = new JTextField();
		txtCantidad.setEnabled(false);
		pnlProductosActualizar.add(txtCantidad, "12, 14, fill, default");
		txtCantidad.setColumns(10);
		
		JLabel lblCantidadMinimaStock = new JLabel("Cantidad Mínima Stock");
		pnlProductosActualizar.add(lblCantidadMinimaStock, "2, 16");
		
		txtCantidadMinimaStock = new JTextField();
		txtCantidadMinimaStock.setEnabled(false);
		pnlProductosActualizar.add(txtCantidadMinimaStock, "12, 16, fill, default");
		txtCantidadMinimaStock.setColumns(10);
		
		JLabel lblIdProveedor = new JLabel("ID Proveedor");
		pnlProductosActualizar.add(lblIdProveedor, "2, 18");
		
		JComboBox cbxIdProveedor = new JComboBox();
		cbxIdProveedor.setEnabled(false);
		pnlProductosActualizar.add(cbxIdProveedor, "12, 18, fill, default");
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		pnlProductosActualizar.add(btnActualizar, "12, 20");

	}

}
