package ch.hsr.ifs.liquids.game.map;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

public class Maps {

	private static final String PATH_TO_MAPS_FILE = "data/maps/maps.yml";

	private static Map[] maps;

	static {
		File mapFile = new File(PATH_TO_MAPS_FILE);

		try {
			maps = Yaml.loadType(mapFile, Map[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Map getMap(int index) {
		return maps[index];
	}

}
