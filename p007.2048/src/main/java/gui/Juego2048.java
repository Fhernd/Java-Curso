package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
	private static final int NUMERO_LADOS = 4;
	
	private boolean gano;
	private boolean perdio;
	private int puntaje;
	
	private Baldosa[] baldosas;
	
	public Juego2048() {
		setPreferredSize(new Dimension(350, 400));
		setFocusable(true);
		
		addKeyListener(new ManejadorComandos());
		
		reiniciarJuego();
	}
	
	public void reiniciarJuego() {
		puntaje = 0;
		gano = false;
		perdio = false;
		
		baldosas = new Baldosa[NUMERO_LADOS * NUMERO_LADOS];
		for (int i = 0; i < baldosas.length; i++) {
			baldosas[i] = new Baldosa();
		}
		
		agregarBaldosa();
		agregarBaldosa();
	}
	
	private void agregarBaldosa() {
		List<Baldosa> espacioDisponible = obtenerEspacioDisonible();
		
		if (!espacioDisponible.isEmpty()) {
			int indiceBaldosaVacia = (int) (Math.random() * espacioDisponible.size()) % espacioDisponible.size();
			Baldosa baldosaVacia = espacioDisponible.get(indiceBaldosaVacia);
			
			if (Math.random() < 0.9) {
				baldosaVacia.setValor(2);
			} else {
				baldosaVacia.setValor(4);
			}
		}
	}

	private List<Baldosa> obtenerEspacioDisonible() {
		List<Baldosa> baldosasDisponibles = new ArrayList<Baldosa>(16);
		
		for (Baldosa baldosa : baldosas) {
			if (baldosa.estaVacia()) {
				baldosasDisponibles.add(baldosa);
			}
		}
		
		return baldosasDisponibles;
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













