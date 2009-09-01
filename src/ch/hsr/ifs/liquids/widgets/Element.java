package ch.hsr.ifs.liquids.widgets;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.controller.listeners.AccelerometerListener;
import ch.hsr.ifs.liquids.controller.listeners.KeyboardListener;
import ch.hsr.ifs.liquids.controller.listeners.MouseListener;
import ch.hsr.ifs.liquids.util.Vector;

public abstract class Element implements Renderable {

	protected Vector position;

	public void addMouseListener(MouseListener listener) {
		
	}

	public void addKeyboardListener(KeyboardListener listener) {

	}

	public void addAccelerometerListener(AccelerometerListener listener) {

	}

}
