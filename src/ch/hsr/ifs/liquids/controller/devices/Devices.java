package ch.hsr.ifs.liquids.controller.devices;

import java.util.HashMap;
import java.util.Map;

public abstract class Devices {

	public enum Port {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
	}

	protected static Map<Port, Device> devices = new HashMap<Port, Device>();
	protected static Map<String, Class<? extends Device>> defaultDevices;
	
	static {
		defaultDevices = new HashMap<String, Class<? extends Device>>();

		defaultDevices.put("mouse", Mouse.class);
		defaultDevices.put("keyboard", Keyboard.class);
		defaultDevices.put("accelerometer", Accelerometer.class);
	}

	public static void unplugDevice(Port port) {
		devices.get(port).removeDevice();
		devices.put(port, null);
	}

	public static void unplugAllDevices() {
		for (Port port : Port.values()) {
			if (isDevicePluggedIn(port)) {
				unplugDevice(port);
			}
		}
	}

	public static boolean isDevicePluggedIn(Port port) {
		return devices.get(port) != null;
	}

	public static Device getDeviceByName(String name) {
		try {
			return defaultDevices.get(name).newInstance();
		} catch (Exception e) {
			String message = "unable to instantiate device '" + name + "'";
			throw new RuntimeException(message, e);
		}
	}

}
