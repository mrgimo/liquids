package ch.hsr.ifs.liquids.engines;

import ch.hsr.ifs.liquids.common.Moveable;

public final class Physics {

	private static final int MOTION_INTERVAL = 25;

	private final Moveable moveable;

	private final Thread motionThread = new Thread() {

		@Override
		public void run() {
			while (isRunning) {
				moveable.move();

				try {
					sleep(MOTION_INTERVAL);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}

	};

	private boolean isRunning = false;

	public Physics(Moveable moveable) {
		this.moveable = moveable;
	}

	public void start() {
		motionThread.start();
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

}
