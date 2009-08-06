package ch.hsr.ifs.liquids.util.list;

import java.util.Iterator;

public class List<T> implements Iterable<T> {

	private static final int DEFAULT_SIZE = 16;
	private static final int ENLARGE_FACTOR = 2;

	private static final String INVALID_INDEX_MESSAGE = "index is not valid!";

	protected T[] elements;
	protected int size;

	public List() {
		this(DEFAULT_SIZE);
	}

	public List(int size) {
		elements = createArray(size);
	}

	public void add(T element) {
		if (!hasSpace()) {
			enlargeList();
		}

		elements[size++] = element;
	}

	public void set(T element, int index) {
		if (!isValidIndex(index)) {
			throw new IllegalArgumentException(INVALID_INDEX_MESSAGE);
		}

		elements[index] = element;
	}

	public T get(int index) {
		if (!isValidIndex(index)) {
			throw new IllegalArgumentException(INVALID_INDEX_MESSAGE);
		}

		return elements[index];
	}

	public void remove(int index) {
		if (!isValidIndex(index)) {
			throw new IllegalArgumentException(INVALID_INDEX_MESSAGE);
		}
		
		--size;

		for (int i = 0; i < size; ++i) {
			if (i < index) {
				continue;
			}

			elements[i] = elements[i + 1];
		}
	}

	public Iterator<T> iterator() {
		return new ListIterator<T>(this);
	}

	protected boolean isValidIndex(int index) {
		return index >= 0 && index < size;
	}

	protected boolean hasSpace() {
		return size < elements.length;
	}

	protected void enlargeList() {
		T[] temp = createArray(size * ENLARGE_FACTOR);
		System.arraycopy(elements, 0, temp, 0, size);
		elements = temp;
	}

	@SuppressWarnings("unchecked")
	protected T[] createArray(int size) {
		return (T[]) new Object[size];
	}

}
