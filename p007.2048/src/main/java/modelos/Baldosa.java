package modelos;

import java.awt.Color;

public class Baldosa {
	private static final int BALDOSA_VACIA = 0;
	
	private int valor;
	
	public Baldosa() {
		this(0);
	}

	public Baldosa(int valor) {
		this.valor = valor;
	}
	
	public boolean estaVacia() {
		return valor == BALDOSA_VACIA;
	}
	
	public Color obtenerColorBaldosa() {
		switch(valor) {
			case 2:
				return new Color(ColorBaldosa.COLOR_2.obtenerColorHex());
			case 4:
				return new Color(ColorBaldosa.COLOR_4.obtenerColorHex());
		}
		
		return new Color(ColorBaldosa.COLOR_0.obtenerColorHex());
	}
}
