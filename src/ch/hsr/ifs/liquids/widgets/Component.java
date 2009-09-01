package ch.hsr.ifs.liquids.widgets;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.list.List;

public abstract class Component extends Element {

	protected List<Renderable> elements = new List<Renderable>();

	public void render(GL gl) {
		for (Renderable element : elements) {
			element.render(gl);
		}
	}

}
