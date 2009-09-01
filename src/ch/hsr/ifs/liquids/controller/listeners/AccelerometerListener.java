package ch.hsr.ifs.liquids.controller.listeners;

import ch.hsr.ifs.liquids.controller.EventListener;
import ch.hsr.ifs.liquids.controller.events.AccelerometerEvent;

public interface AccelerometerListener extends EventListener {

	public void buttonPressed(AccelerometerEvent event);

	public void accelerationChanged(AccelerometerEvent event);

}
