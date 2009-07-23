
package ch.hsr.ifs.liquid.controller.core.events;

public class CursorEvent{
	private int x;
	private int y;
	private int button;
	private int clickCount;

	public CursorEvent(int x, int y, int button, int clickCount) {
		this.x = x;
		this.y = y;
		this.button = button;
		this.clickCount = clickCount;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getButton() {
		return button;
	}
	
	public int getCount() {
		return clickCount;
	}
}
