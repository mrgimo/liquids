package ch.hsr.ifs.liquids.controller.devices;

import ch.hsr.ifs.liquids.controller.devices.Devices.Port;

public abstract class Device {

	public Device() {
		for (Port port : Port.values()) {
			if (!Devices.isDevicePluggedIn(port)) {
				Devices.devices.put(port, this);
			}
		}

		throw new RuntimeException("no port found to plug device");
	}

	protected Device(Port port) {
		if (Devices.isDevicePluggedIn(port)) {
			Devices.unplugDevice(port);
		}

		Devices.devices.put(port, this);
	}

	public abstract void removeDevice();

}
