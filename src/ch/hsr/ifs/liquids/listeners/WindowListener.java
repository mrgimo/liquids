package ch.hsr.ifs.liquids.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.opengl.util.Animator;

public class WindowListener extends WindowAdapter {

	private Animator animator;

	public WindowListener(Animator animator) {
		this.animator = animator;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		animator.stop();
		System.exit(0);
	}

}
