package ch.hsr.ifs.liquid.util;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class FastList<T> implements Iterable<T> {
	private static final int ENLARGE_FACTOR = 2;
	private static final int DEFAULT_ARRAY_LENGTH = 512;

	protected T[] objects = (T[]) new Object[DEFAULT_ARRAY_LENGTH];

	protected int currentIndex;

	public void add(T object) {
		if (currentIndex >= objects.length) {
			enlargeArray();
		}

		objects[currentIndex++] = object;
	}

	public T get(int index) {
		return objects[index];
	}

	public void trim() {
		trimArray();
	}

	public Iterator<T> iterator() {
		return new FastIterator<T>(this);
	}

	private void enlargeArray() {
		T[] enlargedArray = (T[]) new Object[objects.length * ENLARGE_FACTOR];
		System.arraycopy(objects, 0, enlargedArray, 0, currentIndex);
		objects = enlargedArray;
	}

	private void trimArray() {
		T[] trimmedArray = (T[]) new Object[currentIndex];
		System.arraycopy(objects, 0, trimmedArray, 0, currentIndex);
		objects = trimmedArray;
	}
}
