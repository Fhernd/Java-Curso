package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.LongValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Producto;
import modelos.Proveedor;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JComboBox cbxIdProveedor;
	private Aplicacion aplicacion;
	private Map<String, Long> idsProveedores;

	/**
	 * Create the frame.
	 */
	public ProductosCrearFormulario(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

		setTitle("Productos - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);

		JPanel pnlProductosCrear = new JPanel();
		pnlProductosCrear
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosCrear, BorderLayout.CENTER);
		pnlProductosCrear.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

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

		JLabel lblIdProveedor = new JLabel("Proveedor");
		pnlProductosCrear.add(lblIdProveedor, "2, 16");

		cbxIdProveedor = new JComboBox();
		pnlProductosCrear.add(cbxIdProveedor, "12, 16, fill, default");

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				String nombre = txtNombre.getText().trim();
				String descripcion = txtDescripcion.getText().trim();
				String precioCompra = txtPrecioCompra.getText().trim();
				String precioVenta = txtPrecioVenta.getText().trim();
				String cantidad = txtCantidad.getText().trim();
				String cantidadMinimaStock = txtCantidadMinimaStock.getText().trim();
				String idProveedor = cbxIdProveedor.getSelectedItem().toString();

				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this, "El campo ID debe ser un número.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo ID debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Producto producto = aplicacion.buscarProductoPorId(numero.intValue());

				if (producto != null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"Ya existe un proveedor con el ID que se ha especificado. Intente otro ID.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this, "El campo Nombre es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (descripcion.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Double valor = DoubleValidator.getInstance().validate(precioCompra);

				if (valor == null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Precio Compra debe ser un número.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (valor <= 0) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Precio Compra debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				valor = DoubleValidator.getInstance().validate(precioVenta);

				if (valor == null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Precio Venta debe ser un número.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (valor <= 0) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Precio Venta debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				numero = LongValidator.getInstance().validate(cantidad);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Cantidad debe ser un número.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Cantidad debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				numero = LongValidator.getInstance().validate(cantidadMinimaStock);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Cantidad Mínima Stock debe ser un número.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
							"El campo Cantidad Mínima Stock debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long idProveedorSeleccionado = idsProveedores.get(idProveedor);

				producto = new Producto(Integer.parseInt(id), nombre, descripcion, Double.parseDouble(precioCompra),
						Double.parseDouble(precioVenta), Integer.parseInt(cantidad),
						Integer.parseInt(cantidadMinimaStock), idProveedorSeleccionado);
				
				aplicacion.crearProducto(producto);
				
				JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
						"El producto se ha creado de forma satisfactoria.", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
				
				txtId.setText("");
				txtNombre.setText("");
				txtDescripcion.setText("");
				txtPrecioCompra.setText("");
				txtPrecioVenta.setText("");
				txtCantidad.setText("");
				txtCantidadMinimaStock.setText("");
			}
		});
		pnlProductosCrear.add(btnCrear, "12, 18");

		cargarDatosProveedores();

		if (idsProveedores.size() == 0) {
			JOptionPane.showMessageDialog(ProductosCrearFormulario.this,
					"No puede crear un producto ya que no existen proveedores.", "Mensaje",
					JOptionPane.WARNING_MESSAGE);
			btnCrear.setEnabled(false);
		}
	}

	private void cargarDatosProveedores() {
		Proveedor[] proveedores = aplicacion.obtenerProveedores();
		idsProveedores = new HashMap<>();
		String llave;
		long valor;

		for (Proveedor proveedor : proveedores) {
			llave = String.format("%d - %s", proveedor.getId(), proveedor.getNombre());
			valor = proveedor.getId();
			idsProveedores.put(llave, valor);

			cbxIdProveedor.addItem(llave);
		}
	}

}
