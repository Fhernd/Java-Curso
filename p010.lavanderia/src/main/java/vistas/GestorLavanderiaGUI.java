package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GestorLavanderiaGUI {

	private JFrame frmGestorLavanderiaGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorLavanderiaGUI window = new GestorLavanderiaGUI();
					window.frmGestorLavanderiaGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestorLavanderiaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorLavanderiaGUI = new JFrame();
		frmGestorLavanderiaGUI.setTitle("Gestor Lavandería");
		frmGestorLavanderiaGUI.setBounds(100, 100, 450, 300);
		frmGestorLavanderiaGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
