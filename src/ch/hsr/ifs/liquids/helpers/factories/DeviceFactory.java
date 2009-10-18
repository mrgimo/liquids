package ch.hsr.ifs.liquids.helpers.factories;

import ch.hsr.ifs.liquids.devices.Accelerometer;
import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.devices.Keyboard;
import ch.hsr.ifs.liquids.devices.Mouse;
import ch.hsr.ifs.liquids.util.Vector;

public class DeviceFactory {

	private static final String MOUSE = "mouse";
	private static final String ACCELEROMETER = "accelerometer";

	private static final char DEFAULT_UP = 'w';
	private static final char DEFAULT_DOWN = 's';
	private static final char DEFAULT_RIGHT = 'd';
	private static final char DEFAULT_LEFT = 'a';

	public static Device createDevice(String deviceName, Vector position) {
		deviceName = formatInput(deviceName);

		if (deviceName.equals(MOUSE)) {
			return new Mouse(position);
		}

		if (deviceName.equals(ACCELEROMETER)) {
			return new Accelerometer(position);
		}

		return createKeyboard(deviceName, position);
	}

	private static String formatInput(String deviceName) {
		return deviceName.replaceAll("\\s", "").toLowerCase();
	}

	private static Keyboard createKeyboard(String deviceName, Vector position) {
		char up, down, right, left;
		if (isValidInput(deviceName)) {
			up = deviceName.charAt(0);
			down = deviceName.charAt(1);
			right = deviceName.charAt(2);
			left = deviceName.charAt(3);
		} else {
			up = DEFAULT_UP;
			down = DEFAULT_DOWN;
			right = DEFAULT_RIGHT;
			left = DEFAULT_LEFT;
		}

		return new Keyboard(position, up, down, right, left);
	}

	private static boolean isValidInput(String deviceName) {
		return deviceName.length() >= 4;
	}

}
