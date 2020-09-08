package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	private static final int VALOR_NUMERO_BALDOSA_GANADORA = 2048;
	private static final String FUENTE_BALDOSA = "Arial";
	
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
			
			definirLinea(i, baldosasFusionadas);
			
			if (!seNecesitaBaldosa && !comparar(baldosasSeleccionadas, baldosasFusionadas)) {
				seNecesitaBaldosa = true;
			}
		}
		
		if (seNecesitaBaldosa) {
			agregarBaldosa();
		}
	}

	private boolean comparar(Baldosa[] baldosasSeleccionadas, Baldosa[] baldosasFusionadas) {
		if(baldosasSeleccionadas == baldosasFusionadas) {
			return true;
		} else if (baldosasSeleccionadas.length != baldosasFusionadas.length) {
			return false;
		}
		
		for (int i = 0; i < baldosasFusionadas.length; i++) {
			if (baldosasSeleccionadas[i].getValor() != baldosasFusionadas[i].getValor()) {
				return false;
			}
		}
		
		return true;
	}

	private void definirLinea(int posicion, Baldosa[] baldosasFusionadas) {
		System.arraycopy(baldosasFusionadas, 0, baldosas, posicion * NUMERO_LADOS, NUMERO_LADOS);
	}

	private Baldosa[] fusionarLinea(Baldosa[] baldosasAnteriores) {
		LinkedList<Baldosa> listaBaldosas = new LinkedList<>();
		
		for(int i = 0; i < NUMERO_LADOS && !baldosasAnteriores[i].estaVacia(); ++i) {
			int numero = baldosasAnteriores[i].getValor();
			
			if (i < 3 && baldosasAnteriores[i].getValor() == baldosasAnteriores[i + 1].getValor()) {
				numero *= 2;
				puntaje += numero;
				
				if (numero == VALOR_NUMERO_BALDOSA_GANADORA) {
					gano = true;
				}
				
				++i;
			}
			
			listaBaldosas.add(new Baldosa(numero));
		}
		
		if (listaBaldosas.isEmpty()) {
			return baldosasAnteriores;
		} else {
			mantenerTamagnio(listaBaldosas, NUMERO_LADOS);
			return listaBaldosas.toArray(new Baldosa[NUMERO_LADOS]);
		}
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
		while (listaBaldosas.size() != numeroLados) {
			listaBaldosas.add(new Baldosa());
		}
	}

	private Baldosa[] obtenerBaldosa(int indiceBaldosa) {
		Baldosa[] baldosasTemporales = new Baldosa[NUMERO_LADOS];
		
		for (int i = 0; i < NUMERO_LADOS; ++i) {
			baldosasTemporales[i] = obtenerBaldosaDesdePosicion(i, indiceBaldosa); 
		}
		
		return baldosasTemporales;
	}

	public void abajo() {
		baldosas = rotar(90);
		izquierda();
		baldosas =rotar(270); 
	}

	public void derecha() {
		baldosas = rotar(180);
		izquierda();
		baldosas = rotar(180);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		for(int j = 0; j < NUMERO_LADOS; ++j) {
			for(int i = 0; i < NUMERO_LADOS; ++i) {
				dibujarBaldosa(g, baldosas[i + j * 4], i, j);
			}
		}
	}

	private void dibujarBaldosa(Graphics g, Baldosa baldosa, int x, int y) {
		Graphics2D dibujo = ((Graphics2D) g);
		
		dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		dibujo.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		
		int valor = baldosa.getValor();
		int desplazamientoX = calcularDesplazamiento(x);
		int desplazamientoY = calcularDesplazamiento(y);
		
		dibujo.setColor(baldosa.obtenerColorBaldosa());
		dibujo.fillRoundRect(desplazamientoX, desplazamientoY,  desplazamientoY, TAMAGNIO_BALDOSA, 14, 14);
		dibujo.setColor(baldosa.obtenerColorPrincipalBaldosa());
		
		int tamagnioTexto = valor < 100 ? 36 : valor < 1000 ? 32 : 24;
		Font fuente = new Font(FUENTE_BALDOSA, Font.BOLD, tamagnioTexto);
		dibujo.setFont(fuente);
		
		String textoValor = String.valueOf(valor);
		FontMetrics fm = getFontMetrics(fuente);
		
		int anchoFuente = fm.stringWidth(textoValor);
		int altoFuente = -(int) fm.getLineMetrics(textoValor, dibujo).getBaselineOffsets()[2];
		
		if (valor != 0) {
			dibujo.drawString(textoValor, desplazamientoX + (TAMAGNIO_BALDOSA - anchoFuente) / 2, 
					desplazamientoY + TAMAGNIO_BALDOSA - (TAMAGNIO_BALDOSA - altoFuente) / 2 - 2);
		}
		
		if (gano || perdio) {
			dibujo.setColor(new Color(255, 255, 255, 30));
			dibujo.fillRect(0, 0, getWidth(), getHeight());
			
			dibujo.setColor(new Color(80, 140, 200));
			dibujo.setFont(new Font(FUENTE_BALDOSA, Font.BOLD, 50));
			
			if (gano) {
				dibujo.drawString("¡Has ganado!", 60, 150);
			}
			
			if (gano || perdio) {
				dibujo.setFont(new Font(FUENTE_BALDOSA, Font.PLAIN, 17));
				dibujo.setColor(new Color(130, 130, 130, 130));
				
				dibujo.drawString("Presione Escape para Volver a Jugar", 20, getHeight() - 50);
			}
		}
		
		dibujo.setFont(new Font(FUENTE_BALDOSA, Font.PLAIN, 16));
		dibujo.setColor(new Color(0, 0, 0));
		
		dibujo.drawString(String.format("Puntaje: %d", puntaje), 195, 365);
	}

	private int calcularDesplazamiento(int cantidad) {
		return cantidad * (MARGEN_BALDOSA + TAMAGNIO_BALDOSA) + MARGEN_BALDOSA;
	}
}













