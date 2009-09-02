package ch.hsr.ifs.liquids.controller.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import ch.hsr.ifs.liquids.common.Audible;
import ch.hsr.ifs.liquids.controller.devices.Devices.Port;
import ch.hsr.ifs.liquids.controller.listeners.KeyboardListener;
import ch.hsr.ifs.liquids.util.list.List;

public class Keyboard extends Device implements Audible<KeyboardListener> {

	private AWTEventListener eventListener;
	
	private List<KeyboardListener> listeners;

	public Keyboard(Port port) {
		super(port);

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		initEvents(toolkit);
	}

	private void initEvents(Toolkit toolkit) {
		eventListener = new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				System.out.println(event.paramString());
			}

		};

		toolkit.addAWTEventListener(eventListener, AWTEvent.KEY_EVENT_MASK);
	}

	@Override
	public void removeDevice() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.removeAWTEventListener(eventListener);
	}

	public void addEventListener(KeyboardListener listener) {
		listeners.add(listener);
	}

	public void removeEventListener(KeyboardListener listener) {
		listeners.remove(listener);
	}

}
