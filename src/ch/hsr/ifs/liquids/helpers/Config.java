package ch.hsr.ifs.liquids.helpers;

import java.io.File;

import org.ho.yaml.Yaml;

public class Config {

	public static class Window {

		public boolean fullscreen;
		public boolean hideCursor;

	}

	public static class Map {

		public String name;
		public int gridSize;

	}

	public static class Particles {

		public int quantity;
		public int healing;
		public int damage;
		public int size;

	}

	public static class Player {

		public String color;
		public String device;

	}

	public Window window;
	public Map map;
	public Particles particles;
	public Player[] players;

	public static Config load(String path) {
		try {
			return Yaml.loadType(new File(path), Config.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
