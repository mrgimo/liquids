package ch.hsr.ifs.liquids.controller.engines;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.util.list.List;

public class PhysicsEngine {

	private static final int MOTION_INTERVAL = 5;

	private Thread motionThread;
	private List<Movable> movables;

	private boolean isRunning = false;

	public PhysicsEngine(List<Movable> movables) {
		this.movables = movables;

		initMotionThread();
	}

	private void initMotionThread() {
		motionThread = new Thread() {

			@Override
			public void run() {
				while (isRunning) {
					try {
						for (Movable movable : movables) {
							movable.move();
						}

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
