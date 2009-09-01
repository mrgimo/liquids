package ch.hsr.ifs.liquids.util;

public class Vector {

	public float x, y, z;

	public Vector() {
		this(0, 0, 0);
	}

	public Vector(int x, int y) {
		this(x, y, 0);
	}

	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
