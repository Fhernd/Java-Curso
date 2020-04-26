package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

public class ProductosActualizarFormulario extends JInternalFrame {

	
	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = -8224002992324117103L;

	/**
	 * Create the frame.
	 */
	public ProductosActualizarFormulario() {
		setTitle("Productos - Actualizar");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlProductosActualizar = new JPanel();
		pnlProductosActualizar.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosActualizar, BorderLayout.CENTER);

	}

}
