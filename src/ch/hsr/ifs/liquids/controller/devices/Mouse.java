package ch.hsr.ifs.liquids.controller.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

public class Mouse extends Device {

	public enum Button {
		LEFT, RIGHT, MIDDLE, WHEEL_UP, WHEEL_DOWN;
	}

	private AWTEventListener eventListener;
	private AWTEventListener motionListener;

	protected Mouse() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		initEvents(toolkit);
		initMotion(toolkit);
	}

	private void initEvents(Toolkit toolkit) {
		eventListener = new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				System.out.println(event.paramString());
			}

		};

		toolkit.addAWTEventListener(eventListener, AWTEvent.MOUSE_EVENT_MASK);
	}

	private void initMotion(Toolkit toolkit) {
		motionListener = new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				System.out.println(event.paramString());
			}

		};

		toolkit.addAWTEventListener(motionListener,
				AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}

	@Override
	public void removeDevice() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.removeAWTEventListener(eventListener);
		toolkit.removeAWTEventListener(motionListener);
	}

}
