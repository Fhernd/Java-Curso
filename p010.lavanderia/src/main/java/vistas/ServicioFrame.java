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

public class ServicioFrame extends JInternalFrame {
	private JTextField txtServicioId;
	private JTextField txtServicioDescripcion;

	/**
	 * Create the frame.
	 */
	public ServicioFrame() {
		setClosable(true);
		setTitle("Servicios");
		setBounds(100, 100, 450, 300);
		
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
		
		JDateChooser dateChooser = new JDateChooser();
		pnlServiciosDatos.add(dateChooser, "12, 6, fill, fill");
		
		JLabel lblServicioHoraEntrega = new JLabel("Hora entrega:");
		pnlServiciosDatos.add(lblServicioHoraEntrega, "2, 8");

	}

}
