package com.mj.collection;

public interface List<E> extends Collection<E> {

	public int size();

	public boolean isEmpty();

	public int indexOf(E ele);

	public boolean containes(E ele);

	public void add(E ele);

	public void addAll();

	public E remove(int index);

	public E remove(E ele);

	public E set(int index, E ele);

	public E get(int index);

	public void clear();
}
