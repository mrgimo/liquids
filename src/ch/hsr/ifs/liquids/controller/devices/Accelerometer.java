package ch.hsr.ifs.liquids.controller.devices;

import ch.hsr.ifs.liquids.controller.listeners.AccelerometerListener;
import ch.hsr.ifs.liquids.util.list.List;

public class Accelerometer extends Device<AccelerometerListener> {

	public enum Button {
		LEFT, RIGHT, STICK_LEFT, STICK_RIGHT, STICK_UP, STICK_DOWN;
	}

	private List<AccelerometerListener> listeners;

	@Override
	public void plug(Port port) {
		
	}

	@Override
	public void unplug() {

	}

	public void addListener(AccelerometerListener listener) {
		listeners.add(listener);
	}

	public void removeListener(AccelerometerListener listener) {
		listeners.remove(listener);
	}

}
