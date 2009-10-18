package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Window;

public class Mouse extends Device {

	private AWTEventListener listener;

	public Mouse(Vector position) {
		super(position);

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
					Window window = Window.getWindow();

					position.setX(mouseEvent.getXOnScreen()
							- window.getPosition().getX());
					position.setY(Window.SCREEN_HEIGHT
							- mouseEvent.getYOnScreen()
							- window.getPosition().getY());
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
