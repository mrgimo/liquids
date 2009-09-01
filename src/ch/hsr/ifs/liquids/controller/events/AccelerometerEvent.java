package ch.hsr.ifs.liquids.controller.events;

import ch.hsr.ifs.liquids.controller.Event;
import ch.hsr.ifs.liquids.controller.devices.Accelerometer.Button;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.list.List;

public class AccelerometerEvent extends Event {

	private Vector acceleration = new Vector();

	private List<Button> pressedButtons = new List<Button>();
	private List<Button> releasedButtons = new List<Button>();

	public AccelerometerEvent(Vector acceleration, List<Button> pressedButtons,
			List<Button> releasedButtons) {
		if (acceleration != null)
			this.acceleration = acceleration;
		
		if (pressedButtons != null)
			this.pressedButtons = pressedButtons;
		
		if (releasedButtons != null)
			this.releasedButtons = releasedButtons;
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public List<Button> getPressedButtons() {
		return pressedButtons;
	}

	public List<Button> getReleasedButtons() {
		return releasedButtons;
	}

}
