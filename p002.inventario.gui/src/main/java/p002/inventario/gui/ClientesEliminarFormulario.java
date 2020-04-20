package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class ClientesEliminarFormulario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2235777285388677224L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesEliminarFormulario frame = new ClientesEliminarFormulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientesEliminarFormulario() {
		setTitle("Clientes - Eliminar");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlDatosCliente = new JPanel();
		pnlDatosCliente.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlDatosCliente, BorderLayout.CENTER);
		pnlDatosCliente.setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));

	}

}
