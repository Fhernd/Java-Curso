package cliente;

import java.awt.EventQueue;

import gui.VentanaPrincipal;

/**
 * Representa el punto de entrada a la aplicación.
 * 
 * @author John Ortiz Ordoñez.
 *
 */
public class Aplicacion {
	
	/**
	 * Inicia la aplicación.
	 * 
	 * @param args Conjunto de argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		
		// Inicia un hilo (thread) para la interfaz gráfica de usuario:
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
