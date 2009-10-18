package ch.hsr.ifs.liquids.engines;

import ch.hsr.ifs.liquids.common.Moveable;

public class Physics {

	private static final int MOTION_INTERVAL = 20;

	private Moveable moveable;
	private Thread motionThread;

	private boolean isRunning = false;

	public Physics(Moveable moveable) {
		this.moveable = moveable;

		initMotionThread();
	}

	private void initMotionThread() {
		motionThread = new Thread() {

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
	}

	public void start() {
		motionThread.start();
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

}
