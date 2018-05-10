package com.mj.collection;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

	private Object[] elementData;

	private int size;

	public ArrayList() {
		this(16);
	}

	public ArrayList(int cap) {
		elementData = new Object[cap];
	}

	public ArrayList(Collection<E> collection) {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int indexOf(E ele) {
		if (ele == null) {
			for (int i = 0; i < size; i++) {
				if (ele == elementData[i]) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (ele.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean containes(E ele) {
		return indexOf(ele) >= 0;
	}

	@Override
	public void add(E ele) {
		ensureCap(size + 1);

		elementData[size++] = ele;

	}

	@Override
	public void addAll() {

	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("size: " + size + " index: " + index);
		}
		E obj = elementData(index);
		int moveNum = size - index - 1;
		if (moveNum > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, moveNum);
		}
		elementData[--size] = null;

		return obj;
	}

	@Override
	public E remove(E ele) {
		int idx = -1;
		if (ele == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					idx = i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (ele.equals(elementData[i])) {
					idx = i;
				}
			}
		}
		return remove(idx);
	}

	@Override
	public E set(int index, E ele) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("size: " + size + " index: " + index);
		}

		E oldEle = elementData(index);

		elementData[index] = ele;

		return oldEle;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("size: " + size + " index: " + index);
		}
		return elementData(index);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				sb.append(elementData[i]);
			} else {
				sb.append(",").append(elementData[i]);
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private void ensureCap(int num) {
		if (num > elementData.length) {
			int newCapacity = num << 1;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	@SuppressWarnings("unchecked")
	private E elementData(int index) {
		return (E) elementData[index];
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	class ListIterator implements Iterator<E> {

		int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public E next() {
			return elementData(cursor++);
		}

	}

}
