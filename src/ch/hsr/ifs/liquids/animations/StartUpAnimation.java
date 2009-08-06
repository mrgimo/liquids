package ch.hsr.ifs.liquids.animations;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.abstracts.Animation;
import ch.hsr.ifs.liquids.common.interfaces.Drawable;

public class StartUpAnimation extends Animation {

	public StartUpAnimation() {
		drawables = new Drawable[4];

		drawables[0] = new Drawable() {

			public void draw(GL gl) {

			}

		};

		drawables[1] = new Drawable() {

			public void draw(GL gl) {

			}

		};

		drawables[2] = new Drawable() {

			public void draw(GL gl) {

			}

		};

		drawables[3] = new Drawable() {

			public void draw(GL gl) {

			}

		};
	}

	@Override
	public void draw(GL gl) {
		for (Drawable drawable : drawables) {
			drawable.draw(gl);
		}
	}

}
