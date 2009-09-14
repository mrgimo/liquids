package ch.hsr.ifs.liquids.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;

public class Config {

	private static final String CONFIG_PATH = "data/liquids.config";

	public static Map<String, Object> map;

	public static List<Map<String, String>> players;
	public static Map<Integer, Integer> particles;
	public static Map<String, Map<String, String>> maps;

	static {
		try {
			Yaml.loadType(new File(CONFIG_PATH), Config.class);
		} catch (FileNotFoundException e) {
			String message = "reading config at '" + CONFIG_PATH
					+ "' has failed";
			throw new RuntimeException(message, e);
		}
	}

	public List<Map<String, String>> getPlayers() {
		return players;
	}

	public void setPlayers(List<Map<String, String>> players) {
		Config.players = players;
	}

	public Map<Integer, Integer> getParticles() {
		return particles;
	}

	public void setParticles(Map<Integer, Integer> particles) {
		Config.particles = particles;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		Config.map = map;
	}

	public Map<String, Map<String, String>> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Map<String, String>> maps) {
		Config.maps = maps;
	}

}
