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
		
		JLabel lblNewLabel = new JLabel("Fecha inicio:");
		add(lblNewLabel, "2, 2");
		
		JDateChooser dateChooser = new JDateChooser();
		add(dateChooser, "10, 2, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("Fecha final:");
		add(lblNewLabel_1, "2, 4");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		add(dateChooser_1, "10, 4, fill, fill");
		
		JPanel panel = new JPanel();
		add(panel, "10, 8, fill, fill");
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Aceptar");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		panel.add(btnNewButton_1);

	}

}
