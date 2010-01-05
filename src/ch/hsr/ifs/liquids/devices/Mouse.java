package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Window;

public final class Mouse extends Device {

	private final AWTEventListener listener;

	public Mouse(Vector position) {
		super(position);

		listener = createEventListener();
	}

	private AWTEventListener createEventListener() {
		return new AWTEventListener() {

			public final void eventDispatched(final AWTEvent event) {
				switch (event.getID()) {
				case MouseEvent.MOUSE_MOVED:
				case MouseEvent.MOUSE_DRAGGED:
					updatePosition((MouseEvent) event);
				}
			}

			private void updatePosition(final MouseEvent event) {
				Vector mousePosition = Window.getWindow().getMousePosition();
				
				setX(mousePosition.getX());
				setY(mousePosition.getY());
			}
		};
	}

	@Override
	public void plug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.addAWTEventListener(listener, AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}

	@Override
	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.removeAWTEventListener(listener);
	}

}
