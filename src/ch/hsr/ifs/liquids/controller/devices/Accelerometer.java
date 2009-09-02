package ch.hsr.ifs.liquids.controller.devices;

import ch.hsr.ifs.liquids.common.Audible;
import ch.hsr.ifs.liquids.controller.devices.Devices.Port;
import ch.hsr.ifs.liquids.controller.listeners.AccelerometerListener;
import ch.hsr.ifs.liquids.util.list.List;

public class Accelerometer extends Device implements
		Audible<AccelerometerListener> {

	public enum Button {
		LEFT, RIGHT, STICK_LEFT, STICK_RIGHT, STICK_UP, STICK_DOWN;
	}

	private List<AccelerometerListener> listeners;

	public Accelerometer(Port port) {
		super(port);
	}

	@Override
	public void removeDevice() {

	}

	public void addEventListener(AccelerometerListener listener) {
		listeners.add(listener);
	}

	public void removeEventListener(AccelerometerListener listener) {
		listeners.remove(listener);
	}

}
