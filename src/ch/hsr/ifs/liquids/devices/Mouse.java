package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

public class Mouse extends Device {

	private AWTEventListener listener;

	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit()
			.getScreenSize().height;

	public Mouse() {
		initEventListener();
	}

	private void initEventListener() {
		listener = new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				MouseEvent mouseEvent = (MouseEvent) event;

				switch (event.getID()) {
				case MouseEvent.MOUSE_MOVED:
				case MouseEvent.MOUSE_DRAGGED:
					setX(mouseEvent.getX());
					setY(SCREEN_HEIGHT - mouseEvent.getY());
				}
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
