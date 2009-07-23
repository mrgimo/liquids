package ch.hsr.ifs.liquid.model.game.warrior;

import ch.hsr.ifs.liquid.util.FastList;

public class Warriors extends FastList<Warrior> {
	public void add(Warrior warrior) {
		warrior.warriors = this;
		warrior.warriorIndex = currentIndex;
		super.add(warrior);
	}
}
