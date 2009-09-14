package ch.hsr.ifs.liquids.controller.devices;

import static java.awt.AWTEvent.MOUSE_EVENT_MASK;
import static java.awt.AWTEvent.MOUSE_MOTION_EVENT_MASK;
import static java.awt.event.MouseEvent.MOUSE_DRAGGED;
import static java.awt.event.MouseEvent.MOUSE_MOVED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;
import static java.awt.event.MouseEvent.MOUSE_RELEASED;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import ch.hsr.ifs.liquids.controller.events.MouseEvent;
import ch.hsr.ifs.liquids.controller.listeners.MouseListener;
import ch.hsr.ifs.liquids.util.list.List;

public class Mouse extends Device<MouseListener> {

	public enum MouseButton {
		NO_BUTTON, LEFT, RIGHT, MIDDLE;

		public static MouseButton getByID(int id) {
			return values()[id];
		}

		private boolean isPressed = false;

		public void press() {
			isPressed = true;
		}

		public void release() {
			isPressed = false;
		}

		public void invertIsPressed() {
			isPressed = isPressed ? false : true;
		}

		public boolean isPressed() {
			return isPressed;
		}

	}

	private AWTEventListener eventListener = new AWTEventListener() {

		@Override
		public void eventDispatched(AWTEvent event) {
			MouseEvent mouseEvent = MouseEvent.createMouseEvent(event);

			switch (event.getID()) {
			case MOUSE_PRESSED:
				pressButton(mouseEvent);
				break;
			case MOUSE_RELEASED:
				releaseButton(mouseEvent);
				break;
			}
		}

	};

	private AWTEventListener motionListener = new AWTEventListener() {

		@Override
		public void eventDispatched(AWTEvent event) {
			MouseEvent mouseEvent = MouseEvent.createMouseEvent(event);

			switch (event.getID()) {
			case MOUSE_MOVED:
			case MOUSE_DRAGGED:
				moveMouse(mouseEvent);
				break;
			}
		}

	};

	private List<MouseListener> listeners = new List<MouseListener>();

	private void moveMouse(MouseEvent event) {
		for (MouseListener listener : listeners) {
			listener.mouseMoved(event);
		}
	}

	private void pressButton(MouseEvent event) {
		for (MouseListener listener : listeners) {
			listener.buttonPressed(event);
		}
	}

	private void releaseButton(MouseEvent event) {
		for (MouseListener listener : listeners) {
			listener.buttonReleased(event);
		}
	}

	@Override
	public void plug(Port port) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.addAWTEventListener(eventListener, MOUSE_EVENT_MASK);
		toolkit.addAWTEventListener(motionListener, MOUSE_MOTION_EVENT_MASK);
	}

	@Override
	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.removeAWTEventListener(eventListener);
		toolkit.removeAWTEventListener(motionListener);
	}

	public void addListener(MouseListener listener) {
		listeners.add(listener);
	}

	public void removeListener(MouseListener listener) {
		listeners.remove(listener);
	}

}
