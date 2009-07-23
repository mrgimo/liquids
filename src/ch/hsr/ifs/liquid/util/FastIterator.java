package ch.hsr.ifs.liquid.util;

import java.util.Iterator;

public class FastIterator<T> implements Iterator<T>{
	private FastList<T> list;
	
	protected int index;
	
	public FastIterator(FastList<T> list) {
		this.list = list;
	}
	
	public boolean hasNext() {
		return index < list.currentIndex;
	}

	public T next() {
		return list.objects[index++];
	}

	public void remove() {
		list.objects[index - 1] = null;
	}
}
