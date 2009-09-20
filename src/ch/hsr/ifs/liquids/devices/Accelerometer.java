package ch.hsr.ifs.liquids.devices;

public class Accelerometer extends Device {

	public void plug() {
		System.out.println("accelerometer plugged");
	}

	public void unplug() {
		System.out.println("accelerometer unplugged");
	}

}
