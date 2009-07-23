package ch.hsr.ifs.liquid.model;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.controller.listeners.cursor.PlayerCursorListener;
import ch.hsr.ifs.liquid.controller.listeners.input.PlayerInputListener;
import ch.hsr.ifs.liquid.model.game.Map;
import ch.hsr.ifs.liquid.model.game.Player;
import ch.hsr.ifs.liquid.model.game.Team;
import ch.hsr.ifs.liquid.model.game.warrior.Warrior;
import ch.hsr.ifs.liquid.model.game.warrior.Warriors;
import ch.hsr.ifs.liquid.util.Vector;
import ch.hsr.ifs.liquid.view.core.elements.Cursor;

public class Game {
	private static final int WARRIORS_PER_PLAYER = 3000;

	public Warriors warriors;
	public Player[] players;
	public Map map;

	public Game(PApplet applet) {
		map = new Map(applet);
		players = createPlayers();
		warriors = createWarriors(applet);

		startAnimationThread();
	}

	private void startAnimationThread() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < Warrior.STEPS; ++i) {
						for (Warrior warrior : warriors) {
							warrior.step(map);
						}
					}

					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private Player[] createPlayers() {
		Player[] players = new Player[3];

		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();

		player1.cursor.position = new Vector(100, 300);
		player2.cursor.position = new Vector(200, 200);
		player3.cursor.position = new Vector(300, 100);

		player1.team = new Team();
		player2.team = new Team();
		player3.team = new Team();

		player1.cursor.addInputListener(new PlayerInputListener(player1, 'w', 's', 'a', 'd'));

		player2.cursor.addInputListener(new PlayerInputListener(player2, 'u', 'j', 'h', 'k'));

		player3.cursor.addCursorListener(new PlayerCursorListener(player3));

		players[0] = player1;
		players[1] = player2;
		players[2] = player3;

		return players;
	}

	private Warriors createWarriors(PApplet applet) {
		Warriors warriors = new Warriors();

		for (int i = 0; i != players.length; ++i) {
			for (int j = 0; j != WARRIORS_PER_PLAYER; ++j) {
				int x = (int) (Math.random() * applet.width * 9 / 10) + 50;
				int y = (int) (Math.random() * applet.height * 9 / 10) + 50;

				Warrior warrior = new Warrior(players[i], new Vector(x, y));
				warriors.add(warrior);
			}
		}

		warriors.trim();

		return warriors;
	}

	public void update(PApplet applet) {
		map.drawOn(applet);

		applet.noStroke();
		for (Warrior warrior : warriors) {
			warrior.drawOn(applet);
		}

		applet.stroke(Cursor.BORDER_COLOUR);
		for (Player player : players) {
			player.cursor.drawOn(applet);
		}
	}
}
