package ch.hsr.ifs.liquids.devices;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ch.hsr.ifs.liquids.util.Vector;

public abstract class Device {

	private final Lock mutex = new ReentrantLock();

	private Vector position = new Vector();

	public float getX() {
		try {
			mutex.lock();
			return position.x;
		} finally {
			mutex.unlock();
		}
	}

	public float getY() {
		try {
			mutex.lock();
			return position.y;
		} finally {
			mutex.unlock();
		}
	}

	public void setX(float x) {
		try {
			mutex.lock();
			position.x = x;
		} finally {
			mutex.unlock();
		}
	}

	public void setY(float y) {
		try {
			mutex.lock();
			position.y = y;
		} finally {
			mutex.unlock();
		}
	}

	public abstract void plug();

	public abstract void unplug();

}
