package ch.hsr.ifs.liquids.helpers.factories;

import java.io.File;
import java.io.FileFilter;

import ch.hsr.ifs.liquids.helpers.Config;
import ch.hsr.ifs.liquids.logic.PlayingField;

public class PlayingFieldFactory {

	private static final String MAP_DIRECTORY = "data/maps";

	private static final String DEFAULT_BOUNDS_PATH = MAP_DIRECTORY + "/arcadia/bounds.png";
	private static final String DEFAULT_TEXTURE_PATH = MAP_DIRECTORY + "/arcadia/texture.jpg";

	private static final String BOUNDS_REGEX = "bounds\\..*";
	private static final String TEXTURE_REGEX = "texture\\..*";

	public static PlayingField createPlayingField(Config config) {
		String name = config.map.name;
		int gridSize = config.map.gridSize;

		File directory = searchMapDirectory(name);

		if (directory == null) {
			return createDefaultPlayingField(gridSize);
		}

		File bounds = null, texture = null;
		File[] files = directory.listFiles();
		for (File file : files) {
			if (isBoundsFile(file)) {
				bounds = file;
			} else if (isTextureFile(file)) {
				texture = file;
			}
		}

		if (bounds == null || texture == null) {
			return createDefaultPlayingField(gridSize);
		}

		return new PlayingField(bounds, texture, gridSize);
	}

	private static File searchMapDirectory(final String name) {
		File root = new File(MAP_DIRECTORY);

		File[] directories = root.listFiles(new FileFilter() {

			public boolean accept(File dir) {
				return dir.isDirectory() && dir.getName().equals(name);
			}

		});

		return directories.length > 0 ? directories[0] : null;
	}

	private static boolean isBoundsFile(File file) {
		return file.getName().matches(BOUNDS_REGEX);
	}

	private static boolean isTextureFile(File file) {
		return file.getName().matches(TEXTURE_REGEX);
	}

	private static PlayingField createDefaultPlayingField(int gridSize) {
		File bounds = new File(DEFAULT_BOUNDS_PATH);
		File texture = new File(DEFAULT_TEXTURE_PATH);

		return new PlayingField(bounds, texture, gridSize);
	}

}
