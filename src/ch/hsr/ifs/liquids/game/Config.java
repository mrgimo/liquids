package ch.hsr.ifs.liquids.game;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

public class Config {

	public static int players;
	public static int particles;

	public static String map;

	static {
		try {
			Yaml.loadType(new File("data/liquids.config"), Config.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		Config.players = players;
	}

	public int getParticles() {
		return particles;
	}

	public void setParticles(int particles) {
		Config.particles = particles;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		Config.map = map;
	}

}
