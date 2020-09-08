package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Juego2048Ventana extends JFrame {

	/**
	 * ID de Versión.
	 */
	private static final long serialVersionUID = -3729896791377236721L;

	public Juego2048Ventana() {
		setTitle("2048");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(340, 400));
		setResizable(false);
		
		Juego2048 juego = new Juego2048();
		add(juego);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
