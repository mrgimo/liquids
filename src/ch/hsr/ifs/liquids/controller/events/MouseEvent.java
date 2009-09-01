package ch.hsr.ifs.liquids.controller.events;

import ch.hsr.ifs.liquids.controller.Event;
import ch.hsr.ifs.liquids.controller.devices.Mouse.Button;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.list.List;

public class MouseEvent extends Event {

	private Vector position = new Vector();

	private List<Button> clickedButtons = new List<Button>();
	private List<Button> releasedButtons = new List<Button>();

	public MouseEvent(Vector position, List<Button> clickedButtons,
			List<Button> releasedButtons) {
		if (position != null)
			this.position = position;

		if (clickedButtons != null)
			this.clickedButtons = clickedButtons;

		if (releasedButtons != null)
			this.releasedButtons = releasedButtons;
	}

	public Vector getPosition() {
		return position;
	}

	public List<Button> getClickedButtons() {
		return clickedButtons;
	}

	public List<Button> getReleasedButtons() {
		return releasedButtons;
	}

}
