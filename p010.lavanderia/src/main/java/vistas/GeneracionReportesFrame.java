package vistas;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class GeneracionReportesFrame extends JInternalFrame {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 8174451787899692365L;

	/**
	 * Create the frame.
	 */
	public GeneracionReportesFrame() {
		setTitle("Generación de Reportes");
		setBounds(100, 100, 500, 540);
		getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pnlReporte1 = new JPanel();
		pnlReporte1.setBorder(new TitledBorder(null, "Clientes con m\u00E1s servicios solicitados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlReporte1);
		pnlReporte1.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblReporte1CantidadClientes = new JLabel("Cantidad clientes:");
		pnlReporte1.add(lblReporte1CantidadClientes, "2, 2");
		
		JSpinner spnCantidadClientes = new JSpinner();
		pnlReporte1.add(spnCantidadClientes, "12, 2");
		
		JLabel lblReporte1FechaInicio = new JLabel("Fecha inicio:");
		pnlReporte1.add(lblReporte1FechaInicio, "2, 4");
		
		JDateChooser datReporte1FechaInicio = new JDateChooser();
		pnlReporte1.add(datReporte1FechaInicio, "12, 4, fill, fill");
		
		JLabel lblReporte1FechaFinal = new JLabel("Fecha final:");
		pnlReporte1.add(lblReporte1FechaFinal, "2, 6");
		
		JDateChooser datReporte1FechaFinal = new JDateChooser();
		pnlReporte1.add(datReporte1FechaFinal, "12, 6, fill, fill");
		
		JPanel pnlReporte1Botones = new JPanel();
		pnlReporte1.add(pnlReporte1Botones, "12, 8, fill, fill");
		pnlReporte1Botones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnReporte1Visualizar = new JButton("Visualizar");
		pnlReporte1Botones.add(btnReporte1Visualizar);
		
		JButton btnReporte1Guardar = new JButton("Guardar...");
		pnlReporte1Botones.add(btnReporte1Guardar);

	}

}
