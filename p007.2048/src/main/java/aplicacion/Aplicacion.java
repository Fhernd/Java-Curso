package aplicacion;

import javax.swing.SwingUtilities;

import gui.Juego2048Ventana;

public class Aplicacion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Juego2048Ventana(); 
		});
	}

}
