package ch.hsr.ifs.liquids.util;

public final class RandomBoolean {

	private static final boolean[] RANDOM = new boolean[1024];

	private static int index = 0;

	static {
		for (int i = 0; i < RANDOM.length; i++) {
			RANDOM[i] = Math.random() < 0.5;
		}
	}

	public final static boolean random() {
		return RANDOM[index = ++index % RANDOM.length];
	}

}
