package ch.hsr.ifs.liquids.util;

public enum Color {

	RED((byte)0xff, (byte)0x00, (byte)0x00),
	GREEN((byte)0x00, (byte)0xff, (byte)0x00),
	BLUE((byte)0x00, (byte)0x00, (byte)0xff);

	public byte red;
	public byte green;
	public byte blue;
	
	private Color(byte red, byte green, byte blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	} 
	
	public static void main(String[] args) {
		
	}
	
}
