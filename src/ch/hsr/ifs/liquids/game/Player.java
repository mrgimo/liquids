package ch.hsr.ifs.liquids.game;

import java.util.Map;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.controller.devices.Device;
import ch.hsr.ifs.liquids.controller.devices.Devices;
import ch.hsr.ifs.liquids.util.Color;

public class Player implements Movable, Renderable {

	public String name;
	public Color color;
	public Device device;

	public static Player[] createPlayers() {
		Player[] players = new Player[Config.players.size()];

		int i = 0;
		for (Map<String, String> config : Config.players) {
			Player player = new Player();

			String name = config.get("name");
			String color = config.get("color");
			String device = config.get("device");

			player.name = name;
			player.color = Color.getColorByName(color);
			player.device = Devices.getDeviceByName(device);

			players[i++] = player;
		}

		return players;
	}

	public void move() {

	}

	public void render(GL gl) {

	}

}
