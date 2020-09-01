package modelos;

public enum ColorBaldosa {
	COLOR_0(0xcdc1be),
	COLOR_2(0xeee4da),
	COLOR_4(0xede0c8),
	COLOR_8(0xf2b179),
	COLOR_16(0xf59563),
	COLOR_32(0xf67c5f),
	COLOR_64(0xf65e3b),
	COLOR_128(0xedcf72),
	COLOR_256(0xedcc61),
	COLOR_512(0xedc850),
	COLOR_1024(0xedc53f),
	COLOR_2048(0xedc22e);
	
	private int colorHex;
	
	private ColorBaldosa(int colorHex) {
		this.colorHex = colorHex;
	}
	
	public int obtenerColorHex() {
		return colorHex;
	}
}
