package ch.hsr.ifs.liquids.controller.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

public class Keyboard extends Device {

	private AWTEventListener listener;

	protected Keyboard() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		initEvents(toolkit);
	}

	private void initEvents(Toolkit toolkit) {
		listener = new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				System.out.println(event.paramString());
			}

		};

		toolkit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);
	}

	@Override
	public void removeDevice() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.removeAWTEventListener(listener);
	}

}
