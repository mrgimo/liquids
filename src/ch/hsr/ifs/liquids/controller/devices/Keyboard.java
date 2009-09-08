package ch.hsr.ifs.liquids.controller.devices;

import static java.awt.AWTEvent.KEY_EVENT_MASK;
import static java.awt.event.KeyEvent.*;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import ch.hsr.ifs.liquids.controller.events.KeyboardEvent;
import ch.hsr.ifs.liquids.controller.listeners.KeyboardListener;
import ch.hsr.ifs.liquids.util.list.List;

public class Keyboard extends Device<KeyboardListener> {

	private AWTEventListener eventListener = new AWTEventListener() {

		@Override
		public void eventDispatched(AWTEvent event) {
			KeyboardEvent keyboardEvent = KeyboardEvent
					.createKeyboardEvent(event);

			switch (event.getID()) {
			case KEY_PRESSED:
				pressKey(keyboardEvent);
				break;

			case KEY_RELEASED:
				releaseKey(keyboardEvent);
				break;
			}
		}

	};

	private List<KeyboardListener> listeners = new List<KeyboardListener>();

	private void pressKey(KeyboardEvent event) {
		for (KeyboardListener listener : listeners) {
			listener.keyPressed(event);
		}
	}

	private void releaseKey(KeyboardEvent event) {
		for (KeyboardListener listener : listeners) {
			listener.keyReleased(event);
		}
	}

	@Override
	public void plug(Port port) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.addAWTEventListener(eventListener, KEY_EVENT_MASK);
	}

	@Override
	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.removeAWTEventListener(eventListener);
	}

	public void addListener(KeyboardListener listener) {
		listeners.add(listener);
	}

	public void removeListener(KeyboardListener listener) {
		listeners.remove(listener);
	}

}
