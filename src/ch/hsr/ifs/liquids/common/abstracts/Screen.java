package ch.hsr.ifs.liquids.common.abstracts;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import ch.hsr.ifs.liquids.common.interfaces.Drawable;
import ch.hsr.ifs.liquids.util.list.List;

import com.sun.opengl.util.Animator;

public abstract class Screen extends GLCanvas {

	private static final long serialVersionUID = -2664383462725266601L;

	protected Animator animator;
	protected List<Drawable> drawables;

	public Screen() {
		this(new GLCapabilities());
	}

	public Screen(GLCapabilities capabilities) {
		super(capabilities);

		animator = new Animator(this);
		drawables = new List<Drawable>();
	}

	public void open() {
		animator.start();
	}

	public void close() {
		animator.stop();
	}

	public List<Drawable> getDrawables() {
		return drawables;
	}

}
