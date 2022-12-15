package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ClientesFrame extends JInternalFrame {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -2341366357765049257L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesFrame frame = new ClientesFrame();
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
	public ClientesFrame() {
		setBounds(100, 100, 450, 300);

	}

}
