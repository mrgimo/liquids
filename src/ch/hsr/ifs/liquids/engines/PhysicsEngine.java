package ch.hsr.ifs.liquids.engines;

import ch.hsr.ifs.liquids.common.Movable;

public class PhysicsEngine {

	private static final int MOTION_INTERVAL = 5;

	private Thread motionThread;
	private Movable movable;

	private boolean isRunning = false;

	public PhysicsEngine() {
		initMotionThread();
	}

	private void initMotionThread() {
		motionThread = new Thread() {

			@Override
			public void run() {
				while (isRunning) {
					try {
						movable.move();

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

	public void setMovable(Movable movable) {
		this.movable = movable;
	}

}
