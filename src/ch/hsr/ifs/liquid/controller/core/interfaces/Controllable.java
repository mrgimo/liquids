package ch.hsr.ifs.liquid.controller.core.interfaces;

import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.controller.core.listeners.InputListener;

public interface Controllable {
	public void addCursorListener(CursorListener listener);
	public void addInputListener(InputListener listener);
}
