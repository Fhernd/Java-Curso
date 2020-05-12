package p003.buscadortexto.aplicacion;

import java.awt.EventQueue;

import p003.buscadortexto.gui.VentanaPrincipal;

/**
 * Representa la clase que sirve como punto de entrada a la aplicación.
 * 
 * @author John Ortiz Ordoñez.
 */
public class Aplicacion {

	/**
	 * Lanza la aplicación.
	 * 
	 * @param args Datos de entrada desde la línea de comandos.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new VentanaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
