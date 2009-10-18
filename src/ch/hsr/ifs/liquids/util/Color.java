package ch.hsr.ifs.liquids.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Color {

	private static final int COLOR_VALUES = 255;

	private final Lock rLock = new ReentrantLock();
	private final Lock gLock = new ReentrantLock();
	private final Lock bLock = new ReentrantLock();
	private final Lock aLock = new ReentrantLock();

	private float r;
	private float g;
	private float b;
	private float a;

	public Color() {
		r = g = b = 0;
	}

	public Color(int r, int g, int b) {
		this(r, g, b, COLOR_VALUES);
	}

	public Color(int r, int g, int b, int a) {
		this.r = convertToFloat(r);
		this.g = convertToFloat(g);
		this.b = convertToFloat(b);
		this.a = convertToFloat(a);
	}

	private float convertToFloat(int color) {
		return color / COLOR_VALUES;
	}

	public float getR() {
		try {
			rLock.lock();
			return r;
		} finally {
			rLock.unlock();
		}
	}

	public void setR(float r) {
		try {
			rLock.lock();
			this.r = r;
		} finally {
			rLock.unlock();
		}
	}

	public float getG() {
		try {
			gLock.lock();
			return g;
		} finally {
			gLock.unlock();
		}
	}

	public void setG(float g) {

		try {
			gLock.lock();
			this.g = g;
		} finally {
			gLock.unlock();
		}
	}

	public float getB() {
		try {
			bLock.lock();
			return b;
		} finally {
			bLock.unlock();
		}
	}

	public void setB(float b) {
		try {
			bLock.lock();
			this.b = b;
		} finally {
			bLock.unlock();
		}
	}

	public float getA() {
		try {
			aLock.lock();
			return a;
		} finally {
			aLock.unlock();
		}
	}

	public void setA(float a) {
		try {
			aLock.lock();
			this.a = a;
		} finally {
			aLock.unlock();
		}
	}

}
