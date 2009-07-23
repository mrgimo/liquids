package ch.hsr.ifs.liquid.model.game.warrior;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.model.game.Map;
import ch.hsr.ifs.liquid.model.game.Player;
import ch.hsr.ifs.liquid.util.Color;
import ch.hsr.ifs.liquid.util.FastMath;
import ch.hsr.ifs.liquid.util.PseudoRandom;
import ch.hsr.ifs.liquid.util.Vector;
import ch.hsr.ifs.liquid.view.core.Element;

public class Warrior extends Element {
	public static final int SIZE = 3;
	public static final int STEPS = 2;

	private static final int MIN_HEALTH_POINTS = 1;
	private static final int MAX_HEALTH_POINTS = 255;

	private static final int DAMAGE = 13;
	private static final int HEALING = 2;

	public int healthPoints = MAX_HEALTH_POINTS;

	protected int warriorIndex;
	protected Warriors warriors;

	protected Player player;
	/* the current color this warrior displays */
	protected int color;

	private int gridIndex;

	private Vector intercept = new Vector();
	private Vector[] steps = new Vector[4];

	public Warrior(Player player, Vector position) {
		color = player.cursor.colour;
		this.player = player;
		this.position = position;

		for (int i = 0; i < steps.length; ++i) {
			steps[i] = new Vector();
		}
	}

	public void step(Map map) {
		map.setAccessible(gridIndex);

		float x = player.cursor.position.x - position.x;
		float y = player.cursor.position.y - position.y;

		float distance = FastMath.invInvSqrt(x * x + y * y);

		intercept.x = x / distance * SIZE;
		intercept.y = y / distance * SIZE;

		boolean hasNotStepped = true;

		updateSteps();
		for (Vector position : steps) {
			int gridIndex = map.getIndexOf(position);
			if (map.isWarrior(gridIndex)) {
				int warriorIndex = map.getWarriorIndex(gridIndex);
				Warrior warrior = warriors.get(warriorIndex);
				if (isAlly(warrior)) {
					heal(warrior);
				} else {
					attack(warrior);
				}
			} else if (hasNotStepped && map.isAccessible(gridIndex)) {
				this.position = position;
				this.gridIndex = gridIndex;

				hasNotStepped = false;
			}
		}

		map.setWarrior(gridIndex, warriorIndex);
	}

	private final void updateSteps() {
		steps[0].x = position.x + intercept.x;
		steps[0].y = position.y + intercept.y;

		if (PseudoRandom.rand() < 0.5) {
			steps[1].x = position.x - intercept.y;
			steps[1].y = position.y + intercept.x;

			steps[2].x = position.x + intercept.y;
			steps[2].y = position.y - intercept.x;
		} else {
			steps[2].x = position.x - intercept.y;
			steps[2].y = position.y + intercept.x;

			steps[1].x = position.x + intercept.y;
			steps[1].y = position.y - intercept.x;
		}

		steps[3].x = position.x - intercept.x;
		steps[3].y = position.y - intercept.y;
	}

	private final boolean isAlly(Warrior warrior) {
		return warrior.player.team == player.team;
	}

	private final void attack(Warrior enemy) {
		if ((enemy.healthPoints -= DAMAGE) <= MIN_HEALTH_POINTS) {
			enemy.player = player;
			// --enemy.player.numberOfWarriors;
			enemy.healthPoints = MAX_HEALTH_POINTS;
			// ++player.numberOfWarriors;
		}
		color = Color.lerpColor(color, enemy.color, ((float)MAX_HEALTH_POINTS)/healthPoints);
	}

	private final void heal(Warrior ally) {
		if (ally.healthPoints < MAX_HEALTH_POINTS) {
			ally.healthPoints += HEALING;
			if(ally.healthPoints > 255){
				ally.healthPoints = 255;
			}
		}
		ally.color = Color.lerpColor(ally.color, ally.player.cursor.colour, ((float)MAX_HEALTH_POINTS)/ally.healthPoints);
	}

	@Override
	public void drawOn(PApplet applet) {
		//int colour = (color & ((healthPoints << 24) | 0x00ffffff));
		
		applet.noStroke();
		applet.fill(color);
		applet.rect(position.x, position.y, SIZE, SIZE);
	}
}
