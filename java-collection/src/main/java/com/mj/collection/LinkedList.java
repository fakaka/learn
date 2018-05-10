package com.mj.collection;

import java.util.Iterator;

public class LinkedList<E> implements List<E> {

	Node<E> header;

	Node<E> footer;

	int size = 0;

	LinkedList() {

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containes(E ele) {
		return indexOf(ele) > 0;
	}

	@Override
	public void add(E ele) {
		Node<E> f = footer;
		Node<E> newNode = new Node<>(f, ele, null);
		footer = newNode;
		if (f == null) {
			header = newNode;
		} else {
			f.next = newNode;
		}
		size++;
	}

	@Override
	public void addAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	class Node<E> {

		private Node<E> prev;

		private E value;

		private Node<E> next;

		Node(Node<E> prev, E element, Node<E> next) {
			this.value = element;
			this.next = next;
			this.prev = prev;
		}
	}

}
