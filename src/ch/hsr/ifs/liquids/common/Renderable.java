package ch.hsr.ifs.liquids.common;

import java.io.IOException;

import javax.media.opengl.GL2;

public interface Renderable {

	public void init(GL2 gl) throws IOException;

	public void render(GL2 gl);

}
