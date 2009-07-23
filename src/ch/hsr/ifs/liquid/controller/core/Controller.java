package ch.hsr.ifs.liquid.controller.core;

import ch.hsr.ifs.liquid.controller.core.events.CursorEvent;
import ch.hsr.ifs.liquid.controller.core.events.InputEvent;
import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.controller.core.listeners.InputListener;
import ch.hsr.ifs.liquid.util.FastList;

public class Controller {
	protected static FastList<CursorListener> cursorListeners = new FastList<CursorListener>();
	protected static FastList<InputListener> inputListeners = new FastList<InputListener>();

	public static void addCursorListener(CursorListener listener) {
		cursorListeners.add(listener);
	}

	public static void addInputListener(InputListener listener) {
		inputListeners.add(listener);
	}

	public static void clickCursor(CursorEvent event) {
		for (CursorListener listener : cursorListeners) {
			listener.cursorClicked(event);
		}
	}

	public static void pressCursor(CursorEvent event) {
		for (CursorListener listener : cursorListeners) {
			listener.cursorPressed(event);
		}
	}

	public static void releaseCursor(CursorEvent event) {
		for (CursorListener listener : cursorListeners) {
			listener.cursorReleased(event);
		}
	}

	public static void dragCursor(CursorEvent event) {
		for (CursorListener listener : cursorListeners) {
			listener.cursorDragged(event);
		}
	}

	public static void moveCursor(CursorEvent event) {
		for (CursorListener listener : cursorListeners) {
			listener.cursorMoved(event);
		}
	}

	public static void sendInput(InputEvent event) {
		for (InputListener listener : inputListeners) {
			listener.inputReceived(event);
		}
	}
}
