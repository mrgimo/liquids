package ch.hsr.ifs.liquids.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vector {

	private final Lock xLock = new ReentrantLock();
	private final Lock yLock = new ReentrantLock();
	private final Lock zLock = new ReentrantLock();

	private float x, y, z;

	public Vector() {
		x = y = z = 0;
	}

	public Vector(float x) {
		this(x, 0, 0);
	}

	public Vector(float x, float y) {
		this(x, y, 0);
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		try {
			xLock.lock();
			return x;
		} finally {
			xLock.unlock();
		}
	}

	public void setX(float x) {
		try {
			xLock.lock();
			this.x = x;
		} finally {
			xLock.unlock();
		}
	}

	public float getY() {
		try {
			yLock.lock();
			return y;
		} finally {
			yLock.unlock();
		}
	}

	public void setY(float y) {
		try {
			yLock.lock();
			this.y = y;
		} finally {
			yLock.unlock();
		}
	}

	public float getZ() {
		try {
			zLock.lock();
			return z;
		} finally {
			zLock.unlock();
		}
	}

	public void setZ(float z) {
		try {
			zLock.lock();
			this.z = z;
		} finally {
			zLock.unlock();
		}
	}

	public Vector add(Vector vector) {
		float x = getX() + vector.getX();
		float y = getY() + vector.getY();
		float z = getZ() + vector.getZ();

		return new Vector(x, y, z);
	}

	public Vector subtract(Vector vector) {
		float x = getX() - vector.getX();
		float y = getY() - vector.getY();
		float z = getZ() - vector.getZ();

		return new Vector(x, y, z);
	}

}
