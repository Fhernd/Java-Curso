package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class DireccionesFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DireccionesFrame frame = new DireccionesFrame();
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
	public DireccionesFrame() {
		setBounds(100, 100, 450, 300);

	}

}
