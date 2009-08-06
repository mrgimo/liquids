package ch.hsr.ifs.liquids.game;

import ch.hsr.ifs.liquids.game.map.Map;
import ch.hsr.ifs.liquids.util.list.List;

public class Game {

	private Map map;
	private List<Fighter> particles;

	public Game(Map map, Player... players) {
		this.map = map;

		initializeParticles();
	}

	private void initializeParticles() {

	}

}
