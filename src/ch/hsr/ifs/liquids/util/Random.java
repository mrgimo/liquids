package ch.hsr.ifs.liquids.util;

public class Random {

	private static final float[] RANDOM = new float[1024];
	private static int index = 0;

	static {
		for (int i = 0; i < RANDOM.length; i++) {
			RANDOM[i] = (float) Math.random();
		}
	}

	public static float random() {
		index++;
		return RANDOM[index %= RANDOM.length];
	}

}
