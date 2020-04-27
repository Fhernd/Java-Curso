package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;

public class FacturacionCrearFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 7287407346788257718L;

	/**
	 * Create the frame.
	 */
	public FacturacionCrearFormulario() {
		setTitle("Facturación - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlFacturacionCrear = new JPanel();
		pnlFacturacionCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlFacturacionCrear, BorderLayout.CENTER);
		pnlFacturacionCrear.setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));

	}

}
