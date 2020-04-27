package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.LongValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelos.Producto;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductosEliminarFormulario extends JInternalFrame {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 4902421048621265227L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtCantidad;
	private JTextField txtCantidadMinimaStock;
	private JTextField txtIdProveedor;
	private JButton btnEliminar;

	/**
	 * Create the frame.
	 */
	public ProductosEliminarFormulario(Aplicacion aplicacion) {
		setTitle("Productos - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 340);

		JPanel pnlProductosEliminar = new JPanel();
		pnlProductosEliminar
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosEliminar, BorderLayout.CENTER);
		pnlProductosEliminar.setLayout(new FormLayout(
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
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblId = new JLabel("ID");
		pnlProductosEliminar.add(lblId, "2, 2");

		txtId = new JTextField();
		pnlProductosEliminar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();

				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosEliminarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosEliminarFormulario.this, "El campo ID debe ser un número.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosEliminarFormulario.this,
							"El campo ID debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Producto producto = aplicacion.buscarProductoPorId(numero.intValue());

				if (producto == null) {
					JOptionPane.showMessageDialog(ProductosEliminarFormulario.this,
							"No existe un producto con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					txtNombre.setText("");
					btnBuscar.setEnabled(true);
					txtDescripcion.setText("");
					txtPrecioCompra.setText("");
					txtPrecioVenta.setText("");
					txtCantidad.setText("");
					txtCantidadMinimaStock.setText("");
					txtIdProveedor.setText("");
					btnEliminar.setEnabled(false);

					return;
				}
				
				boolean respuesta = aplicacion.productoEnFactura(numero.intValue());
				
				if (respuesta) {
					JOptionPane.showMessageDialog(ProductosEliminarFormulario.this,
							"No se puede eliminar este producto porque está asociado al menos a una factura.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				txtNombre.setText(producto.getNombre());
				txtDescripcion.setText(producto.getDescripcion());
				txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
				txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
				txtCantidad.setText(String.valueOf(producto.getCantidad()));
				txtCantidadMinimaStock.setText(String.valueOf(producto.getCantidadMinimaStock()));
				txtIdProveedor.setText(String.valueOf(producto.getIdProveedor()));

				btnBuscar.setEnabled(false);
				btnEliminar.setEnabled(true);
			}
		});
		pnlProductosEliminar.add(btnBuscar, "12, 4");

		JLabel lblNombre = new JLabel("Nombre");
		pnlProductosEliminar.add(lblNombre, "2, 6");

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		pnlProductosEliminar.add(txtNombre, "12, 6, fill, default");
		txtNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción");
		pnlProductosEliminar.add(lblDescripcion, "2, 8");

		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		pnlProductosEliminar.add(txtDescripcion, "12, 8, fill, default");
		txtDescripcion.setColumns(10);

		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		pnlProductosEliminar.add(lblPrecioCompra, "2, 10");

		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setEnabled(false);
		pnlProductosEliminar.add(txtPrecioCompra, "12, 10, fill, default");
		txtPrecioCompra.setColumns(10);

		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		pnlProductosEliminar.add(lblPrecioVenta, "2, 12");

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEnabled(false);
		pnlProductosEliminar.add(txtPrecioVenta, "12, 12, fill, default");
		txtPrecioVenta.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad");
		pnlProductosEliminar.add(lblCantidad, "2, 14");

		txtCantidad = new JTextField();
		txtCantidad.setEnabled(false);
		pnlProductosEliminar.add(txtCantidad, "12, 14, fill, default");
		txtCantidad.setColumns(10);

		JLabel lblCantidadMinimaStock = new JLabel("Cantidad Mínima Stock");
		pnlProductosEliminar.add(lblCantidadMinimaStock, "2, 16");

		txtCantidadMinimaStock = new JTextField();
		txtCantidadMinimaStock.setEnabled(false);
		pnlProductosEliminar.add(txtCantidadMinimaStock, "12, 16, fill, default");
		txtCantidadMinimaStock.setColumns(10);

		JLabel lblIdProveedor = new JLabel("ID Proveedor");
		pnlProductosEliminar.add(lblIdProveedor, "2, 18");

		txtIdProveedor = new JTextField();
		txtIdProveedor.setEnabled(false);
		pnlProductosEliminar.add(txtIdProveedor, "12, 18, fill, default");
		txtIdProveedor.setColumns(10);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();

				int resultado = JOptionPane.showConfirmDialog(ProductosEliminarFormulario.this,
						"¿Está seguro de querer eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
			}
		});
		btnEliminar.setEnabled(false);
		pnlProductosEliminar.add(btnEliminar, "12, 20");

	}

}
