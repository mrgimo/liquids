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

	public final float getX() {
		try {
			xLock.lock();
			return x;
		} finally {
			xLock.unlock();
		}
	}

	public final void setX(final float x) {
		try {
			xLock.lock();
			this.x = x;
		} finally {
			xLock.unlock();
		}
	}

	public final float getY() {
		try {
			yLock.lock();
			return y;
		} finally {
			yLock.unlock();
		}
	}

	public final void setY(final float y) {
		try {
			yLock.lock();
			this.y = y;
		} finally {
			yLock.unlock();
		}
	}

	public final float getZ() {
		try {
			zLock.lock();
			return z;
		} finally {
			zLock.unlock();
		}
	}

	public final void setZ(final float z) {
		try {
			zLock.lock();
			this.z = z;
		} finally {
			zLock.unlock();
		}
	}

}
