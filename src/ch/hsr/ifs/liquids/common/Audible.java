package ch.hsr.ifs.liquids.common;

import ch.hsr.ifs.liquids.controller.EventListener;

public interface Audible<T extends EventListener> {

	public void addEventListener(T listener);

	public void removeEventListener(T listener);

}
