package ch.hsr.ifs.liquids.controller.devices;

import java.util.HashMap;
import java.util.Map;

public abstract class Devices {

	public enum Port {
		ONE, TWO, THREE, FOUR, FIVE;
	}

	protected static Map<Port, Device> devices = new HashMap<Port, Device>();

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

}
