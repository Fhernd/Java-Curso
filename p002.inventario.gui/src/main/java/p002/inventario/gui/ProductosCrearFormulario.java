package p002.inventario.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

public class ProductosCrearFormulario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7218636655538886731L;

	/**
	 * Create the frame.
	 */
	public ProductosCrearFormulario() {
		setTitle("Productos - Crear");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlProductosCrear = new JPanel();
		pnlProductosCrear.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlProductosCrear, BorderLayout.CENTER);

	}

}
