package ch.hsr.ifs.liquids.common;

import java.io.IOException;

import javax.media.opengl.GL;

public interface Renderable {

	public void init() throws IOException;

	public void render(GL gl);

}
