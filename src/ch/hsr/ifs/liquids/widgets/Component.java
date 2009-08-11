package ch.hsr.ifs.liquids.widgets;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.list.List;

public abstract class Component implements Renderable {

	protected List<Renderable> renderables;

	public void render(GL gl) {
		for (Renderable renderable : renderables) {
			renderable.render(gl);
		}
	}

}
