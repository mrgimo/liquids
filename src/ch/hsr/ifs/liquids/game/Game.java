package ch.hsr.ifs.liquids.game;

public class Game {

	protected Player[] players;
	protected Particle[] particles;

	protected PlayingField field;

	public Game() {
		players = Player.createPlayers();
		particles = Particle.createParticles(players);

		field = new PlayingField();
	}

}
