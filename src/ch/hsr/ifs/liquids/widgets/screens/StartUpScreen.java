package ch.hsr.ifs.liquids.widgets.screens;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.widgets.Component;
import ch.hsr.ifs.liquids.widgets.Image;

public class StartUpScreen extends Component {

	public StartUpScreen() {
		elements.add(new Renderable() {

			@Override
			public void render(GL gl) {
				gl.glColor3f(1, 1, 1);
				gl.glBegin(GL.GL_POLYGON);
					gl.glVertex3f(25, 25, 0);
					gl.glVertex3f(75, 25, 0);
					gl.glVertex3f(75, 75, 0);
					gl.glVertex3f(25, 75, 0);
				gl.glEnd();
			}

		});
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		elements.add(new Image("data/maps/arcadia/texture.jpg", screenSize.width, screenSize.height));
	}

}
