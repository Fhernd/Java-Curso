package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
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
		baldosas = rotar(270);
		izquierda();
		baldosas = rotar(90);
	}

	private Baldosa[] rotar(int angulo) {
		Baldosa[] baldosasModificadas = new Baldosa[NUMERO_LADOS * NUMERO_LADOS];
		
		int desplazamientoX = 3;
		int desplazamientoY = 3;
		
		if (angulo == 90) {
			desplazamientoY = 0;
		} else if (angulo == 270 ) {
			desplazamientoX = 0;
		}
		
		double radianes = Math.toRadians(angulo);
		int seno = (int) Math.sin(radianes);
		int coseno = (int) Math.cos(radianes);
		
		for (int i = 0; i < NUMERO_LADOS; i++) {
			for (int j = 0; j < NUMERO_LADOS; j++) {
				int x = (i * coseno) - (j * seno) + desplazamientoX;
				int y = (i * seno) + (j * coseno) + desplazamientoY;
				
				baldosasModificadas[x + y * NUMERO_LADOS] = obtenerBaldosaDesdePosicion(i, j);
			}
		}
		
		return baldosasModificadas;
	}

	public boolean baldosasMovibles() {
		if (!tableroLleno()) {
			return true;
		}
		
		for (int i = 0; i < NUMERO_LADOS; i++) {
			for (int j = 0; j < NUMERO_LADOS; j++) {
				Baldosa baldosa = obtenerBaldosaDesdePosicion(i, j);
				
				if ((i < 3 && baldosa.getValor() == obtenerBaldosaDesdePosicion(i + 1, j).getValor())
						|| (j < 3 && baldosa.getValor() == obtenerBaldosaDesdePosicion(i, j + 1).getValor())) {
					return true;
				}
			}
		}
		
		return false;
	}

	private Baldosa obtenerBaldosaDesdePosicion(int i, int j) {
		return baldosas[i + j * NUMERO_LADOS];
	}

	private boolean tableroLleno() {
		return obtenerEspacioDisonible().size() == 0;
	}

	public void izquierda() {
		boolean seNecesitaBaldosa = false;
		
		for(int i = 0; i < NUMERO_LADOS; ++i) {
			Baldosa[] baldosasSeleccionadas = obtenerBaldosa(i);
			Baldosa[] baldosasFusionadas = fusionarLinea(moverLinea(baldosasSeleccionadas));
		}
	}

	private Baldosa[] fusionarLinea(Object moverLinea) {
		// TODO Auto-generated method stub
		return null;
	}

	private Baldosa[] moverLinea(Baldosa[] baldosasSeleccionadas) {
		LinkedList<Baldosa> listaBaldosas = new LinkedList<>();
		
		for(int i = 0; i < NUMERO_LADOS; ++i) {
			if (!baldosasSeleccionadas[i].estaVacia()) {
				listaBaldosas.add(baldosasSeleccionadas[i]);
			}
		}
		
		if (listaBaldosas.isEmpty()) {
			return baldosasSeleccionadas;
		} else {
			Baldosa[] nuevasBaldosas = new Baldosa[NUMERO_LADOS];
			mantenerTamagnio(listaBaldosas, NUMERO_LADOS);
			
			for(int i = 0; i < NUMERO_LADOS; ++i) {
				nuevasBaldosas[i] = listaBaldosas.removeFirst();
			}
			
			return nuevasBaldosas;
		}
	}

	private void mantenerTamagnio(LinkedList<Baldosa> listaBaldosas, int numeroLados) {
		// TODO Auto-generated method stub
		
	}

	private Baldosa[] obtenerBaldosa(int indiceBaldosa) {
		Baldosa[] baldosasTemporales = new Baldosa[NUMERO_LADOS];
		
		for (int i = 0; i < NUMERO_LADOS; ++i) {
			baldosasTemporales[i] = obtenerBaldosaDesdePosicion(i, indiceBaldosa); 
		}
		
		return baldosasTemporales;
	}

	public void abajo() {
		// TODO Auto-generated method stub
		
	}

	public void derecha() {
		// TODO Auto-generated method stub
		
	}
}













