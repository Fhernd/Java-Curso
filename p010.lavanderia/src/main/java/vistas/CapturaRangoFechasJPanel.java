package vistas;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.GridLayout;

public class CapturaRangoFechasJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2309572527450541301L;

	/**
	 * Create the panel.
	 */
	public CapturaRangoFechasJPanel() {
		setBorder(new TitledBorder(null, "Fechas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		add(lblFechaInicio, "2, 2");
		
		JDateChooser datFechaInicio = new JDateChooser();
		add(datFechaInicio, "10, 2, fill, fill");
		
		JLabel lblFechaFinal = new JLabel("Fecha final:");
		add(lblFechaFinal, "2, 4");
		
		JDateChooser datFechaFinal = new JDateChooser();
		add(datFechaFinal, "10, 4, fill, fill");
		
		JPanel pnlBotones = new JPanel();
		add(pnlBotones, "10, 8, fill, fill");
		pnlBotones.setLayout(new GridLayout(0, 2, 15, 0));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setMnemonic('A');
		pnlBotones.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic('C');
		pnlBotones.add(btnCancelar);

	}

}
