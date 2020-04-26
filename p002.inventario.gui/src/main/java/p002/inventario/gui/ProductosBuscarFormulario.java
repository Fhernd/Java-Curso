package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Producto;

import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.LongValidator;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductosBuscarFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = -7272490546935297906L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtCantidad;
	private JTextField txtCantidadMinimaStock;
	private JTextField txtIdProveedor;

	/**
	 * Create the frame.
	 */
	public ProductosBuscarFormulario(Aplicacion aplicacion) {
		setTitle("Productos - Buscar");
		setClosable(true);
		setBounds(100, 100, 450, 320);
		
		JPanel pnlProductosBuscar = new JPanel();
		pnlProductosBuscar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosBuscar, BorderLayout.CENTER);
		pnlProductosBuscar.setLayout(new FormLayout(new ColumnSpec[] {
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
		pnlProductosBuscar.add(lblId, "2, 2");
		
		txtId = new JTextField();
		pnlProductosBuscar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosBuscarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosBuscarFormulario.this, "El campo ID debe ser un número.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosBuscarFormulario.this,
							"El campo ID debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Producto producto = aplicacion.buscarProductoPorId(numero.intValue());

				if (producto != null) {
					JOptionPane.showMessageDialog(ProductosBuscarFormulario.this,
							"Ya existe un proveedor con el ID que se ha especificado. Intente otro ID.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
			}
		});
		pnlProductosBuscar.add(btnBuscar, "12, 4");
		
		JLabel lblNombre = new JLabel("Nombre");
		pnlProductosBuscar.add(lblNombre, "2, 6");
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		pnlProductosBuscar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		pnlProductosBuscar.add(lblDescripcion, "2, 8");
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		pnlProductosBuscar.add(txtDescripcion, "12, 8, fill, default");
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		pnlProductosBuscar.add(lblPrecioCompra, "2, 10");
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setEnabled(false);
		pnlProductosBuscar.add(txtPrecioCompra, "12, 10, fill, default");
		txtPrecioCompra.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		pnlProductosBuscar.add(lblPrecioVenta, "2, 12");
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEnabled(false);
		pnlProductosBuscar.add(txtPrecioVenta, "12, 12, fill, default");
		txtPrecioVenta.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		pnlProductosBuscar.add(lblCantidad, "2, 14");
		
		txtCantidad = new JTextField();
		txtCantidad.setEnabled(false);
		pnlProductosBuscar.add(txtCantidad, "12, 14, fill, default");
		txtCantidad.setColumns(10);
		
		JLabel lblCantidadMinimaStock = new JLabel("Cantidad Mínima Stock");
		pnlProductosBuscar.add(lblCantidadMinimaStock, "2, 16");
		
		txtCantidadMinimaStock = new JTextField();
		txtCantidadMinimaStock.setEnabled(false);
		pnlProductosBuscar.add(txtCantidadMinimaStock, "12, 16, fill, default");
		txtCantidadMinimaStock.setColumns(10);
		
		JLabel lblIdProveedor = new JLabel("ID Proveedor");
		pnlProductosBuscar.add(lblIdProveedor, "2, 18");
		
		txtIdProveedor = new JTextField();
		txtIdProveedor.setEnabled(false);
		pnlProductosBuscar.add(txtIdProveedor, "12, 18, fill, default");
		txtIdProveedor.setColumns(10);

	}

}
