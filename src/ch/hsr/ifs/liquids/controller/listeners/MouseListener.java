package ch.hsr.ifs.liquids.controller.listeners;

import ch.hsr.ifs.liquids.controller.EventListener;
import ch.hsr.ifs.liquids.controller.events.MouseEvent;

public interface MouseListener extends EventListener {

	public void mouseMoved(MouseEvent event);

	public void buttonClicked(MouseEvent event);

	public void buttonReleased(MouseEvent event);

}
