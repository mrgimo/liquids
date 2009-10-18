package ch.hsr.ifs.liquids.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hsr.ifs.liquids.util.Vector;

public class Accelerometer extends Device {

	static {
		try {
			System.loadLibrary("accelerometer");

			isLoaded = true;
		} catch (UnsatisfiedLinkError e) {
			System.err.println(e.getMessage());

			isLoaded = false;
		}
	}

	private static final Map<Integer, Accelerometer> plugged = new HashMap<Integer, Accelerometer>();
	private static final List<Accelerometer> unplugged = new ArrayList<Accelerometer>();

	private static final float STEP = 1;
	private static final float PRECISION = 6;

	private static Thread updater;

	private static boolean isUpdating = false;
	private static boolean isLoaded;

	public Accelerometer(Vector position) {
		super(position);

		updater = createUpdater();
	}

	private Thread createUpdater() {
		return new Thread() {

			@Override
			public void run() {
				if (!isLoaded)
					return;

				init();

				while (isUpdating) {
					int[] data = readAcceleration();

					if (data[0] == 0 && data[1] == 0 && data[2] == 0
							&& data[3] == 0)
						return;

					int id = data[0];

					int x = data[1];
					int y = data[2];
					int z = data[3];

					Accelerometer accelerometer = plugged.get(id);
					if (accelerometer == null && unplugged.size() > 0) {
						int index = unplugged.size() - 1;

						plugged.put(id, unplugged.get(index));
						unplugged.remove(index);
					}

					Vector position = accelerometer.position;

					float alpha = Math.round(PRECISION * Math.atan2(x, z));
					float beta = Math.round(PRECISION * Math.atan2(y, z));

					position.setX(position.getX() + alpha * STEP);
					position.setY(position.getY() + beta * STEP);
				}

				remove();
			}
		};
	}

	@Override
	public void plug() {
		if (!isUpdating) {
			isUpdating = true;
			updater.start();
		}

		unplugged.add(this);
	}

	@Override
	public void unplug() {
		isUpdating = false;
	}

	private static native void init();

	private static native void remove();

	private static native int[] readAcceleration();

}
