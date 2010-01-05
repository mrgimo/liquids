package ch.hsr.ifs.liquids.devices;

import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Window;

public abstract class Device {

	private final Vector position;

	public abstract void plug();

	public abstract void unplug();

	public Device(Vector position) {
		this.position = position;
	}

	public final float getX() {
		return position.getX();
	}

	public final void setX(float x) {
		final float w = Window.getWindow().getSize().getX();

		position.setX(x > w ? w : x < 0 ? 0 : x);
	}

	public final float getY() {
		return position.getY();
	}

	public final void setY(float x) {
		final float h = Window.getWindow().getSize().getY();

		position.setY(x > h ? h : x < 0 ? 0 : x);
	}

}
