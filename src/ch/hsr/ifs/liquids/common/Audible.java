package ch.hsr.ifs.liquids.common;

import ch.hsr.ifs.liquids.controller.EventListener;

public interface Audible {
	
	public <T extends EventListener> void addEventListener(T listener);

}
