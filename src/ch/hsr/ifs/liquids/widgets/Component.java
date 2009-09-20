package ch.hsr.ifs.liquids.widgets;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.util.list.List;

public abstract class Component extends Element {

	private List<Element> elements = new List<Element>();

	public void render(GL gl) {
		for (Element element : elements) {
			element.render(gl);
		}
	}

	public void addElement(Element element) {
		elements.add(element);
	}

	public void removeElement(Element element) {
		elements.remove(element);
	}

}
