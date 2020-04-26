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
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ProductosCrearFormulario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7218636655538886731L;
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
	public ProductosCrearFormulario() {
		setTitle("Productos - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlProductosCrear = new JPanel();
		pnlProductosCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosCrear, BorderLayout.CENTER);
		pnlProductosCrear.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblId = new JLabel("ID");
		pnlProductosCrear.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProductosCrear.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProductosCrear.add(lblNombre, "2, 4");
		
		txtNombre = new JTextField();
		pnlProductosCrear.add(txtNombre, "12, 4, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		pnlProductosCrear.add(lblDescripcion, "2, 6");
		
		txtDescripcion = new JTextField();
		pnlProductosCrear.add(txtDescripcion, "12, 6, fill, default");
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecioCompra = new JLabel("Precio de compra");
		pnlProductosCrear.add(lblPrecioCompra, "2, 8");
		
		txtPrecioCompra = new JTextField();
		pnlProductosCrear.add(txtPrecioCompra, "12, 8, fill, default");
		txtPrecioCompra.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio de venta");
		pnlProductosCrear.add(lblPrecioVenta, "2, 10");
		
		txtPrecioVenta = new JTextField();
		pnlProductosCrear.add(txtPrecioVenta, "12, 10, fill, default");
		txtPrecioVenta.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		pnlProductosCrear.add(lblCantidad, "2, 12");
		
		txtCantidad = new JTextField();
		pnlProductosCrear.add(txtCantidad, "12, 12, fill, default");
		txtCantidad.setColumns(10);
		
		JLabel lblCantidadMinimaStock = new JLabel("Cantidad mínima stock");
		pnlProductosCrear.add(lblCantidadMinimaStock, "2, 14");
		
		txtCantidadMinimaStock = new JTextField();
		pnlProductosCrear.add(txtCantidadMinimaStock, "12, 14, fill, default");
		txtCantidadMinimaStock.setColumns(10);
		
		JLabel lblIdProveedor = new JLabel("New label");
		pnlProductosCrear.add(lblIdProveedor, "2, 16");
		
		JComboBox cbxIdProveedor = new JComboBox();
		pnlProductosCrear.add(cbxIdProveedor, "12, 16, fill, default");
		
		JButton btnCrear = new JButton("Crear");
		pnlProductosCrear.add(btnCrear, "12, 18");

	}

}
