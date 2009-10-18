package ch.hsr.ifs.liquids.devices;

import ch.hsr.ifs.liquids.util.Vector;

public abstract class Device {
	
	public final Vector position;

	public abstract void plug();

	public abstract void unplug();
	
	public Device(Vector position) {
		this.position = position;
	}

}
