package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import ch.hsr.ifs.liquids.util.Vector;

public class Keyboard extends Device {

	private static final int STEP = 3;
	private static final int MOTION_INTERVAL = 10;

	private AWTEventListener listener;
	private Thread motionThread;

	private boolean isUpdating = false;

	private char[] keys;
	private boolean[] arePressed = { false, false, false, false };

	public Keyboard(Vector position, char up, char down, char right, char left) {
		super(position);

		initEventListener();
		initMotionThread();

		initKeys(up, down, right, left);
	}

	private void initEventListener() {
		listener = new AWTEventListener() {

			public void eventDispatched(AWTEvent event) {
				KeyEvent keyEvent = (KeyEvent) event;

				switch (event.getID()) {
				case KeyEvent.KEY_PRESSED:
				case KeyEvent.KEY_RELEASED:
					char key = keyEvent.getKeyChar();

					updateKeys(key);
				}
			}

		};
	}

	private void updateKeys(char key) {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] == key) {
				arePressed[i] = !arePressed[i];
				return;
			}
		}
	}

	private void initMotionThread() {
		motionThread = new Thread() {

			@Override
			public void run() {
				while (isUpdating) {
					updatePosition();

					try {
						sleep(MOTION_INTERVAL);
					} catch (Exception e) {
						continue;
					}
				}
			}

		};
	}

	private void updatePosition() {
		for (int i = 0; i < keys.length; i++) {
			if (arePressed[i]) {
				switch (i) {
				case 0:
					position.setY(position.getY() + STEP);
					break;
				case 1:
					position.setY(position.getY() - STEP);
					break;
				case 2:
					position.setX(position.getX() + STEP);
					break;
				case 3:
					position.setX(position.getX() - STEP);
					break;
				}
			}
		}
	}

	private void initKeys(char up, char right, char down, char left) {
		keys = new char[4];

		keys[0] = up;
		keys[1] = right;
		keys[2] = down;
		keys[3] = left;
	}

	public void plug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);

		isUpdating = true;
		motionThread.start();
	}

	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.removeAWTEventListener(listener);

		isUpdating = false;
	}

}
