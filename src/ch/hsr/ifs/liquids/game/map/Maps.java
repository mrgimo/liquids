package ch.hsr.ifs.liquids.game.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ho.yaml.Yaml;

public class Maps {

	private static final Lock MUTEX = new ReentrantLock();

	private static final File MAP_FILE = new File("data/maps/maps.yml");

	private static Map[] maps;

	static {
		loadMaps();
	}

	public static void loadMaps() {
		try {
			MUTEX.lock();

			maps = Yaml.loadType(MAP_FILE, Map[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			MUTEX.unlock();
		}
	}

	public static Map getMap(int index) {
		try {
			MUTEX.lock();

			return maps[index];
		} finally {
			MUTEX.unlock();
		}
	}

}
