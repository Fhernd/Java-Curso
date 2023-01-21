package vistas;

import java.awt.EventQueue;

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
import com.toedter.calendar.JDateChooser;
import com.raven.swing.TimePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;

public class ServicioFrame extends JInternalFrame {
	private JTextField txtServicioId;
	private JTextField txtServicioDescripcion;
	private JTextField txtServicioHoraEntrega;
	private JTable tblServiciosRegistros;
	private JTextField txtAtencionId;
	private JTextField txtAtencionPrecio;
	private JComboBox cbxAtencionTipo;

	/**
	 * Create the frame.
	 */
	public ServicioFrame() {
		setClosable(true);
		setTitle("Servicios");
		setBounds(100, 100, 600, 700);
		
		JPanel pnlServicios = new JPanel();
		pnlServicios.setBorder(new TitledBorder(null, "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlServicios, BorderLayout.NORTH);
		pnlServicios.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlServiciosDatos = new JPanel();
		pnlServiciosDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlServicios.add(pnlServiciosDatos, BorderLayout.NORTH);
		pnlServiciosDatos.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblServicioId = new JLabel("ID:");
		pnlServiciosDatos.add(lblServicioId, "2, 2");
		
		txtServicioId = new JTextField();
		txtServicioId.setEditable(false);
		pnlServiciosDatos.add(txtServicioId, "12, 2, fill, default");
		txtServicioId.setColumns(10);
		
		JLabel lblServicioDescripcion = new JLabel("Descripción:");
		pnlServiciosDatos.add(lblServicioDescripcion, "2, 4");
		
		txtServicioDescripcion = new JTextField();
		pnlServiciosDatos.add(txtServicioDescripcion, "12, 4, fill, top");
		txtServicioDescripcion.setColumns(10);
		
		JLabel lblServicioFechaEntrega = new JLabel("Fecha entrega:");
		pnlServiciosDatos.add(lblServicioFechaEntrega, "2, 6");
		
		JDateChooser datServicioFechaEntrega = new JDateChooser();
		pnlServiciosDatos.add(datServicioFechaEntrega, "12, 6, fill, fill");
		
		JLabel lblServicioHoraEntrega = new JLabel("Hora entrega:");
		pnlServiciosDatos.add(lblServicioHoraEntrega, "2, 8");
		
		txtServicioHoraEntrega = new JTextField();
		txtServicioHoraEntrega.setEditable(false);
		pnlServiciosDatos.add(txtServicioHoraEntrega, "12, 8, fill, default");
		txtServicioHoraEntrega.setColumns(10);
		
		JButton btnServicioSeleccionarHora = new JButton("Seleccionar hora...");
		btnServicioSeleccionarHora.addActionListener(e -> {
			// TODO: Abrir ventana de selección de hora
		});
		pnlServiciosDatos.add(btnServicioSeleccionarHora, "12, 10");
		
		JLabel lblServicioEmpleado = new JLabel("Empleado:");
		pnlServiciosDatos.add(lblServicioEmpleado, "2, 12, left, default");
		
		JComboBox cbxServicioEmpleado = new JComboBox();
		pnlServiciosDatos.add(cbxServicioEmpleado, "12, 12, fill, default");
		
		JLabel lblServicioCliente = new JLabel("Cliente:");
		pnlServiciosDatos.add(lblServicioCliente, "2, 14");
		
		JComboBox cbxServicioCliente = new JComboBox();
		pnlServiciosDatos.add(cbxServicioCliente, "12, 14, fill, default");
		
		JLabel lblServicioDireccion = new JLabel("Dirección entrega:");
		pnlServiciosDatos.add(lblServicioDireccion, "2, 16");
		
		JComboBox cbxServicioDireccionEntrega = new JComboBox();
		pnlServiciosDatos.add(cbxServicioDireccionEntrega, "12, 16, fill, default");
		
		JPanel pnlServiciosAcciones = new JPanel();
		pnlServiciosAcciones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlServicios.add(pnlServiciosAcciones, BorderLayout.CENTER);
		pnlServiciosAcciones.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnServicioNuevo = new JButton("Nuevo");
		btnServicioNuevo.addActionListener(e -> {
			// TODO: Iniciar un nuevo servicio
		});
		pnlServiciosAcciones.add(btnServicioNuevo);
		
		JButton btnServicioGuardar = new JButton("Guardar");
		btnServicioGuardar.addActionListener(e -> {
			// TODO: Guardar los datos de un nuevo servicio
		});
		pnlServiciosAcciones.add(btnServicioGuardar);
		
		JButton btnServicioBuscar = new JButton("Buscar...");
		btnServicioBuscar.addActionListener(e -> {
			// TODO: Buscar entre los registros de servicios
		});
		pnlServiciosAcciones.add(btnServicioBuscar);
		
		JButton btnServicioEditar = new JButton("Editar");
		btnServicioEditar.addActionListener(e -> {
			// TODO: Editar los datos de un servicio
		});
		pnlServiciosAcciones.add(btnServicioEditar);
		
		JButton btnServicioEliminar = new JButton("Eliminar");
		btnServicioEliminar.addActionListener(e -> {
			// TODO: Eliminar un servicio seleccionado
		});
		pnlServiciosAcciones.add(btnServicioEliminar);
		
		JPanel pnlServiciosRegistros = new JPanel();
		pnlServiciosRegistros.setSize(200, 200);
		pnlServiciosRegistros.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlServicios.add(pnlServiciosRegistros, BorderLayout.SOUTH);
		pnlServiciosRegistros.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spnServiciosRegistros = new JScrollPane();
		spnServiciosRegistros.setSize(200, 200);
		pnlServiciosRegistros.add(spnServiciosRegistros, BorderLayout.CENTER);
		
		tblServiciosRegistros = new JTable();
		tblServiciosRegistros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Descripci\u00F3n", "Fecha entrega", "Empleado", "Cliente", "Direcci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		spnServiciosRegistros.setViewportView(tblServiciosRegistros);
		tblServiciosRegistros.setSize(200, 200);
		tblServiciosRegistros.setPreferredScrollableViewportSize(new Dimension(600, 150));
		
		
		JPanel pnlAtenciones = new JPanel();
		pnlAtenciones.setBorder(new TitledBorder(null, "Atenciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlAtenciones, BorderLayout.SOUTH);
		pnlAtenciones.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlAtencionesDatos = new JPanel();
		pnlAtencionesDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAtenciones.add(pnlAtencionesDatos, BorderLayout.NORTH);
		pnlAtencionesDatos.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblAtencionId = new JLabel("ID:");
		pnlAtencionesDatos.add(lblAtencionId, "2, 2");
		
		txtAtencionId = new JTextField();
		txtAtencionId.setEditable(false);
		pnlAtencionesDatos.add(txtAtencionId, "18, 2, fill, default");
		txtAtencionId.setColumns(10);
		
		JLabel lblAtencionCantidad = new JLabel("Cantidad:");
		pnlAtencionesDatos.add(lblAtencionCantidad, "2, 4");
		
		JSpinner snnAtencionCantidad = new JSpinner();
		pnlAtencionesDatos.add(snnAtencionCantidad, "18, 4");
		
		JLabel lblAtencionPrecio = new JLabel("Precio:");
		pnlAtencionesDatos.add(lblAtencionPrecio, "2, 6");
		
		txtAtencionPrecio = new JTextField();
		txtAtencionPrecio.setText("");
		pnlAtencionesDatos.add(txtAtencionPrecio, "18, 6, fill, top");
		txtAtencionPrecio.setColumns(10);
		
		JLabel lblAtencionTipo = new JLabel("Tipo:");
		pnlAtencionesDatos.add(lblAtencionTipo, "2, 8");
		
		cbxAtencionTipo = new JComboBox();
		pnlAtencionesDatos.add(cbxAtencionTipo, "18, 8, fill, default");

	}

}
