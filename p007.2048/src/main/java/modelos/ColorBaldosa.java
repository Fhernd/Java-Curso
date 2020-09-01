package modelos;

public enum ColorBaldosa {
	C_0(0xcdc1be),
	C_2(0xeee4da),
	C_4(0xede0c8),
	C_8(0xf2b179),
	C_16(0xf59563),
	C_32(0xf67c5f),
	C_64(0xf65e3b),
	C_128(0xedcf72),
	C_256(0xedcc61),
	C_512(0xedc850),
	C_1024(0xedc53f),
	C_2048(0xedc22e);
	
	private int colorHex;
	
	private ColorBaldosa(int colorHex) {
		this.colorHex = colorHex;
	}
}
