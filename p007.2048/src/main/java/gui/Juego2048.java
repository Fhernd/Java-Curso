package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import modelos.Baldosa;

public class Juego2048 extends JPanel {

	/**
	 * Representa el ID de versión de la clase.
	 */
	private static final long serialVersionUID = -7189306025861606629L;
	
	private static final int TAMAGNIO_BALDOSA = 64;
	private static final int MARGEN_BALDOSA = 16;
	private static final Color COLOR_FONDO_PANEL = new Color(0xffffff);
	
	private boolean gano;
	private boolean perdio;
	private int puntaje;
	
	private Baldosa[] baldosas;
	
	public Juego2048() {
		setPreferredSize(new Dimension(350, 400));
		setFocusable(true);
		
		addKeyListener(new ManejadorComandos());
	}
	
	class ManejadorComandos extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent evento) {
			if (evento.getKeyCode() == KeyEvent.VK_ESCAPE) {
				reiniciarJuego();
			}
			
			if (!baldosasMovibles()) {
				perdio = true;
			}
			
			if (!perdio && !gano) {
				switch(evento.getKeyCode()) {
					case KeyEvent.VK_UP:
						arriba();
						break;
					case KeyEvent.VK_RIGHT:
						derecha();
						break;
					case KeyEvent.VK_DOWN:
						abajo();
						break;
					case KeyEvent.VK_LEFT:
						izquierda();
						break;
				}
			}
			
			if (!gano && !baldosasMovibles()) {
				perdio = true;
			}
			
			repaint();
		}
	}

	public void arriba() {
		// TODO Auto-generated method stub
		
	}

	public boolean baldosasMovibles() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reiniciarJuego() {
		// TODO Auto-generated method stub
		
	}

	public void izquierda() {
		// TODO Auto-generated method stub
		
	}

	public void abajo() {
		// TODO Auto-generated method stub
		
	}

	public void derecha() {
		// TODO Auto-generated method stub
		
	}
}













