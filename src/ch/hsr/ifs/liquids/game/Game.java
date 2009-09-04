package ch.hsr.ifs.liquids.game;

public class Game {

	protected Player[] players;
	protected Particle[] particles;

	protected Field map;

	public Game() {
		players = Player.createPlayers();
		particles = Particle.createParticles(players);
		map = Field.creatField();
	}

}
