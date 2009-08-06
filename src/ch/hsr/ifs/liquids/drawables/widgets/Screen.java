package ch.hsr.ifs.liquids.drawables.widgets;

import javax.media.opengl.GL;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import ch.hsr.ifs.liquids.common.Drawable;
import ch.hsr.ifs.liquids.util.list.List;

import com.sun.opengl.util.Animator;

public abstract class Screen extends GLCanvas implements Drawable {

	private static final long serialVersionUID = -2664383462725266601L;

	protected Animator animator;
	protected List<Element> elements;

	public Screen() {
		this(new GLCapabilities());
	}

	public Screen(GLCapabilities capabilities) {
		super(capabilities);

		animator = new Animator(this);
		elements = new List<Element>();
	}

	public void open() {
		animator.start();
	}

	public void close() {
		animator.stop();
	}

	public void draw(GL gl) {
		for(Element element : elements){
			element.draw(gl);
		}
	}

}
