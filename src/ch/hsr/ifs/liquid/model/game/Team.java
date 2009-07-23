package ch.hsr.ifs.liquid.model.game;


public class Team {
	private static byte NUMBER_OF_TEAMS = 0;

	public byte team;

	public Team() {
		team = NUMBER_OF_TEAMS++;
	}
}
