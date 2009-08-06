package ch.hsr.ifs.liquids.util.list;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {

	private List<T> list;

	private int index;

	public ListIterator(List<T> list) {
		this.list = list;
	}

	public boolean hasNext() {
		return index < list.size;
	}

	public T next() {
		return list.get(index++);
	}

	public void remove() {
		list.remove(index - 1);
	}

}
