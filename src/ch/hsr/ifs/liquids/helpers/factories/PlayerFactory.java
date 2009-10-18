package ch.hsr.ifs.liquids.helpers.factories;

import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.helpers.Config;
import ch.hsr.ifs.liquids.logic.Player;
import ch.hsr.ifs.liquids.logic.PlayingField;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;

public class PlayerFactory {

	public static Player[] createPlayers(Config config,
			PlayingField playingField) {
		Player[] players = new Player[config.players.length];

		float w = playingField.widthInPixels;
		float h = playingField.heightInPixels;

		float x = w / 2;
		float y = h / 2;

		double radius = calcRadius(w, h);
		double angle = calcAngle(players.length);

		for (int i = 0; i < players.length; i++) {
			String deviceName = config.players[i].device;
			String colorName = config.players[i].color;

			Vector position = calcPosition(x, y, radius, angle * i);

			Device device = DeviceFactory.createDevice(deviceName, position);
			Color color = ColorFactory.createColor(colorName);

			device.plug();

			players[i] = new Player(color, device, config.particles.quantity);
		}

		return players;
	}

	private static double calcRadius(float x, float y) {
		return Math.min(x, y) * 4 / 10;
	}

	private static double calcAngle(int players) {
		return 2 * Math.PI / players;
	}

	private static Vector calcPosition(float x, float y, double radius,
			double angle) {
		x = (float) (Math.cos(angle) * radius) + x;
		y = (float) (Math.sin(angle) * radius) + y;

		return new Vector(x, y);
	}

}
