package ch.hsr.ifs.liquid.controller.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.controller.core.Controller;
import ch.hsr.ifs.liquid.controller.core.events.CursorEvent;
import ch.hsr.ifs.liquid.controller.core.events.InputEvent;

public class DefaultController {
	public DefaultController(PApplet applet) {
		applet.addMouseListener(createMouseListener());
		applet.addMouseMotionListener(createMouseMotionListener());
		applet.addKeyListener(createKeyListener());
	}

	private MouseListener createMouseListener() {
		return new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				int clickCount = e.getClickCount();

				CursorEvent event = new CursorEvent(x, y, button, clickCount);

				Controller.clickCursor(event);
			}

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				int clickCount = e.getClickCount();

				CursorEvent event = new CursorEvent(x, y, button, clickCount);

				Controller.pressCursor(event);
			}

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				int clickCount = e.getClickCount();

				CursorEvent event = new CursorEvent(x, y, button, clickCount);

				Controller.releaseCursor(event);
			}

			public void mouseEntered(MouseEvent e) {
				return;
			}

			public void mouseExited(MouseEvent e) {
				return;
			}
		};
	}

	private MouseMotionListener createMouseMotionListener() {
		return new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				int clickCount = e.getClickCount();

				CursorEvent event = new CursorEvent(x, y, button, clickCount);

				Controller.dragCursor(event);
			}

			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				int clickCount = e.getClickCount();

				CursorEvent event = new CursorEvent(x, y, button, clickCount);

				Controller.moveCursor(event);
			}
		};
	}

	private KeyListener createKeyListener() {
		return new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				InputEvent event = new InputEvent(c);

				Controller.sendInput(event);
			}

			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				InputEvent event = new InputEvent(c);

				Controller.sendInput(event);
			}

			public void keyReleased(KeyEvent e) {
				return;
			}

		};
	}
}
