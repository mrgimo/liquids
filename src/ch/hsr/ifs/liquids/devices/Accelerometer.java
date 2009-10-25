package ch.hsr.ifs.liquids.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hsr.ifs.liquids.util.Vector;

public class Accelerometer extends Device {

	private static final float STEP = 1;
	private static final float PRECISION = 6;

	private static final Map<Integer, Accelerometer> plugged;
	private static final List<Accelerometer> unplugged;

	private static final Thread updater;

	private static boolean isUpdating = false;
	private static boolean isLoaded;

	static {
		try {
			System.loadLibrary("accelerometer");
			isLoaded = true;
		} catch (UnsatisfiedLinkError e) {
			System.err.println(e.getMessage());
			isLoaded = false;
		}

		plugged = new HashMap<Integer, Accelerometer>();
		unplugged = new ArrayList<Accelerometer>();

		updater = createUpdater();
	}

	private int id;

	public Accelerometer(Vector position) {
		super(position);
	}

	private static Thread createUpdater() {
		return new Thread() {

			@Override
			public void run() {
				if (!isLoaded)
					return;

				init();

				while (isUpdating)
					updatePosition();

				remove();
			}
		};
	}

	private final static void updatePosition() {
		final int[] data = readData();

		if (data == null)
			return;

		final Accelerometer accelerometer = getAccelerometer(data);
		if (accelerometer == null)
			return;

		final float x = calcX(data);
		final float y = calcY(data);

		final Vector position = accelerometer.position;

		position.setX(position.getX() + x);
		position.setY(position.getY() + y);
	}

	private final static Accelerometer getAccelerometer(final int[] data) {
		final int id = data[0];

		Accelerometer accelerometer = plugged.get(id);
		if (accelerometer != null || unplugged.size() <= 0)
			return accelerometer;

		final int index = unplugged.size() - 1;

		accelerometer = unplugged.get(index);
		accelerometer.id = id;

		unplugged.remove(index);
		plugged.put(id, accelerometer);

		return accelerometer;
	}

	private final static float calcX(final int[] data) {
		final int x = data[1];
		final int z = data[3];

		final float alpha = Math.round(PRECISION * Math.atan2(x, z));

		return STEP * alpha;
	}

	private final static float calcY(final int[] data) {
		final int y = data[2];
		final int z = data[3];

		final float beta = Math.round(PRECISION * Math.atan2(y, z));

		return STEP * beta;
	}

	@Override
	public void plug() {
		unplugged.add(this);

		if (!isUpdating) {
			isUpdating = true;
			updater.start();
		}
	}

	@Override
	public void unplug() {
		plugged.remove(id);

		isUpdating = plugged.isEmpty();
	}

	private static native void init();

	private static native void remove();

	private static native int[] readData();

}
