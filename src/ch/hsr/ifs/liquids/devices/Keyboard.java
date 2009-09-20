package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class Keyboard extends Device {

	private static final int UPDATING_RATE = 10;
	private static final int STEP = 3;

	private AWTEventListener listener;
	private Thread positionUpdater;

	private boolean isUpdating = false;

	private char[] keys = { 'w', 'd', 's', 'a' };
	private boolean[] arePressed = { false, false, false, false };

	public Keyboard() {
		initEventListener();
		initPositionUpdater();
	}

	private void initEventListener() {
		listener = new AWTEventListener() {

			public void eventDispatched(AWTEvent event) {
				KeyEvent keyEvent = (KeyEvent) event;

				switch (event.getID()) {
				case KeyEvent.KEY_PRESSED:
				case KeyEvent.KEY_RELEASED:
					updateKeys(keyEvent.getKeyChar());
				}
			}

			private void updateKeys(char key) {
				for (int i = 0; i < keys.length; i++) {
					if (keys[i] == key) {
						arePressed[i] = !arePressed[i];
						return;
					}
				}
			}

		};
	}

	private void initPositionUpdater() {
		positionUpdater = new Thread() {

			public void run() {
				while (isUpdating) {
					try {
						updatePosition();
						Thread.sleep(UPDATING_RATE);
					} catch (InterruptedException e) {
						continue;
					}
				}
			}

			private void updatePosition() {
				for (int i = 0; i < keys.length; i++) {
					if (arePressed[i]) {

						switch (i) {
						case 0:
							setY(getY() + STEP);
							break;
						case 1:
							setX(getX() + STEP);
							break;
						case 2:
							setY(getY() - STEP);
							break;
						case 3:
							setX(getX() - STEP);
							break;
						}
					}
				}
			}

		};
	}

	public void plug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);

		isUpdating = true;
		positionUpdater.start();
	}

	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.removeAWTEventListener(listener);

		isUpdating = false;
	}

}
