package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;

public class ProveedoresCrearFormulario extends JInternalFrame {

	/**
	 * ID de versión de clase.
	 */
	private static final long serialVersionUID = 4875332889027488723L;

	/**
	 * Create the frame.
	 */
	public ProveedoresCrearFormulario(Aplicacion aplicacion) {
		setTitle("Proveedores - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlProveedoresCrear = new JPanel();
		pnlProveedoresCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProveedoresCrear, BorderLayout.CENTER);
		pnlProveedoresCrear.setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));

	}

}
