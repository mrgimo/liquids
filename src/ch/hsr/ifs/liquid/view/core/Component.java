package ch.hsr.ifs.liquid.view.core;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.util.FastList;

public abstract class Component extends Element {
	public FastList<Element> elements = new FastList<Element>();

	public void add(Element element) {
		element.parent = this;

		elements.add(element);
		element.added();
	}

	@Override
	public void drawOn(PApplet applet) {
		for (Element element : elements) {
			element.drawOn(applet);
		}
	}
}
