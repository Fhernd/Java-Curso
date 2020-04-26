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
import modelos.Proveedor;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

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
	private JComboBox cbxIdProveedor;
	private JButton btnActualizar;
	
	private Aplicacion aplicacion;
	Map<String, Long> idsProveedores;
	
	/**
	 * Create the frame.
	 */
	public ProductosActualizarFormulario(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		idsProveedores = new HashMap<>();
		
		setTitle("Productos - Actualizar");
		setClosable(true);
		setBounds(100, 100, 450, 340);

		JPanel pnlProductosActualizar = new JPanel();
		pnlProductosActualizar
				.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosActualizar, BorderLayout.CENTER);
		pnlProductosActualizar.setLayout(new FormLayout(
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
		pnlProductosActualizar.add(lblId, "2, 2");

		txtId = new JTextField();
		pnlProductosActualizar.add(txtId, "12, 2, fill, default");
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();

				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(ProductosActualizarFormulario.this, "El campo ID es obligatorio.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Long numero = LongValidator.getInstance().validate(id);

				if (numero == null) {
					JOptionPane.showMessageDialog(ProductosActualizarFormulario.this, "El campo ID debe ser un número.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (numero <= 0) {
					JOptionPane.showMessageDialog(ProductosActualizarFormulario.this,
							"El campo ID debe ser un número positivo (mayor a cero).", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Producto producto = aplicacion.buscarProductoPorId(numero.intValue());

				if (producto == null) {
					JOptionPane.showMessageDialog(ProductosActualizarFormulario.this,
							"No existe un producto con el ID especificado.", "Mensaje", JOptionPane.WARNING_MESSAGE);
					
					txtId.setEnabled(true);
					txtNombre.setText("");
					txtNombre.setEnabled(false);
					txtDescripcion.setText("");
					txtDescripcion.setEnabled(false);
					txtPrecioCompra.setText("");
					txtPrecioCompra.setEnabled(false);
					txtPrecioVenta.setText("");
					txtPrecioVenta.setEnabled(false);
					txtCantidad.setText("");
					txtCantidad.setEnabled(false);
					txtCantidadMinimaStock.setText("");
					txtCantidadMinimaStock.setEnabled(false);
					
					idsProveedores.clear();
					btnActualizar.setEnabled(false);
					btnBuscar.setEnabled(true);

					return;
				}

				txtNombre.setText(producto.getNombre());
				txtNombre.setEnabled(true);
				txtDescripcion.setText(producto.getDescripcion());
				txtDescripcion.setEnabled(true);
				txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
				txtPrecioCompra.setEnabled(true);
				txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
				txtPrecioVenta.setEnabled(true);
				txtCantidad.setText(String.valueOf(producto.getCantidad()));
				txtCantidad.setEnabled(true);
				txtCantidadMinimaStock.setText(String.valueOf(producto.getCantidadMinimaStock()));
				txtCantidadMinimaStock.setEnabled(true);
				
				cargarDatosProveedores();
				cbxIdProveedor.setEnabled(true);
				Proveedor proveedor = aplicacion.buscarProveedorPorId(producto.getIdProveedor());
				cbxIdProveedor.setSelectedItem(String.format("%d - %s", proveedor.getId(), proveedor.getNombre()));
				
				btnActualizar.setEnabled(true);
				txtId.setEnabled(false);
			}
		});
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

		cbxIdProveedor = new JComboBox();
		cbxIdProveedor.setEnabled(false);
		pnlProductosActualizar.add(cbxIdProveedor, "12, 18, fill, default");

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnActualizar.setEnabled(false);
		pnlProductosActualizar.add(btnActualizar, "12, 20");

	}

	private void cargarDatosProveedores() {
		Proveedor[] proveedores = aplicacion.obtenerProveedores();
		idsProveedores.clear();
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
