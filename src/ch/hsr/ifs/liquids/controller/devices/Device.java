package ch.hsr.ifs.liquids.controller.devices;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.ifs.liquids.common.Audible;
import ch.hsr.ifs.liquids.controller.EventListener;

public abstract class Device<T extends EventListener> implements Audible<T> {

	public enum Port {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;

		private Device<?> device;

		protected void plugDevice(Device<?> device) {
			this.device = device;
		}

		protected void unplugDevice() {
			device = null;
		}

		public boolean isDevicePlugged() {
			return device != null;
		}
	}

	protected static Map<String, Class<? extends Device<?>>> defaultDevices;

	static {
		defaultDevices = new HashMap<String, Class<? extends Device<?>>>();

		defaultDevices.put("mouse", Mouse.class);
		defaultDevices.put("keyboard", Keyboard.class);
		defaultDevices.put("accelerometer", Accelerometer.class);
	}

	public static Device<?> getDeviceByName(String name) {
		try {
			return defaultDevices.get(name).newInstance();
		} catch (Exception e) {
			String message = "unable to instantiate device '" + name + "'";
			throw new RuntimeException(message, e);
		}
	}

	protected Port port;

	public abstract void plug(Port port);

	public abstract void unplug();

	public boolean isPlugged() {
		return port != null;
	}

}
