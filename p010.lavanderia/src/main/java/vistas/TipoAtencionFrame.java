package vistas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class TipoAtencionFrame extends JInternalFrame {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2632599037283355071L;
	private JTextField txtId;
	private JTextField txtNombre;

	/**
	 * Create the frame.
	 */
	public TipoAtencionFrame() {
		setTitle("Tipo Atención");
		setClosable(true);
		setBounds(100, 100, 500, 450);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlDatos, BorderLayout.NORTH);
		pnlDatos.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblId = new JLabel("ID:");
		pnlDatos.add(lblId, "2, 2");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		pnlDatos.add(txtId, "18, 2, fill, default");
		txtId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		pnlDatos.add(lblNewLabel, "2, 4");
		
		txtNombre = new JTextField();
		pnlDatos.add(txtNombre, "18, 4, fill, default");
		txtNombre.setColumns(10);
		
		JPanel pnlAcciones = new JPanel();
		getContentPane().add(pnlAcciones, BorderLayout.CENTER);
		pnlAcciones.setLayout(new GridLayout(0, 3, 0, 0));

	}

}
