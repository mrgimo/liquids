package ch.hsr.ifs.liquids.controller.devices;

import ch.hsr.ifs.liquids.controller.devices.Devices.Port;


public abstract class Device {

	protected Device(Port port) {
		if(Devices.isDevicePluggedIn(port)){
			Devices.unplugDevice(port);
		}
		
		Devices.devices.put(port, this);
	}
	
	public abstract void removeDevice();

}
