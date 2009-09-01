package ch.hsr.ifs.liquids.controller;

public abstract class Event {

	private long originTime;

	public Event() {
		originTime = System.currentTimeMillis();
	}

	public long getOriginTime() {
		return originTime;
	}

}
