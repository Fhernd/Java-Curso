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

public class ServicioFrame extends JInternalFrame {
	private JTextField txtServicioId;
	private JTextField txtServicioDescripcion;
	private JTextField txtServicioHoraEntrega;

	/**
	 * Create the frame.
	 */
	public ServicioFrame() {
		setClosable(true);
		setTitle("Servicios");
		setBounds(100, 100, 600, 450);
		
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
		pnlServiciosAcciones.add(btnServicioNuevo);
		
		JButton btnServicioGuardar = new JButton("Guardar");
		pnlServiciosAcciones.add(btnServicioGuardar);
		
		JButton btnServicioBuscar = new JButton("Buscar...");
		pnlServiciosAcciones.add(btnServicioBuscar);
		
		JButton btnServicioEditar = new JButton("Editar");
		pnlServiciosAcciones.add(btnServicioEditar);
		
		JButton btnServicioEliminar = new JButton("Eliminar");
		pnlServiciosAcciones.add(btnServicioEliminar);

	}

}
