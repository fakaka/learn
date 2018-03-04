package com.mj.concurrent.basic;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	private final LinkedList<Object> list = new LinkedList<>();

	private AtomicInteger counter = new AtomicInteger(0);

	private final int maxSize;

	private final int minSize = 0;

	private final Object lock = new Object();

	public MyQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	public void put(Object obj) {
		synchronized (lock) {
			if (maxSize == counter.get()) {
				try {
					System.out.println("集合已满，等待中。。。");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			System.out.println("元素 " + obj + " 被添加 ");
			counter.incrementAndGet();
			lock.notify();
		}
	}

	public Object take() {
		Object temp = null;
		synchronized (lock) {
			if (counter.get() == minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			temp = list.removeFirst();
			System.out.println("元素 " + temp + " 被消费 ");
			counter.decrementAndGet();
			lock.notify();
		}
		return temp;
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) throws InterruptedException {

		final MyQueue m = new MyQueue(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());

		Thread t1 = new Thread((Runnable) () -> {
			m.put("h");
			m.put("i");
		}, "t1");

		Thread t2 = new Thread((Runnable) () -> {
			try {
				Thread.sleep(1000);
				Object t11 = m.take();
				System.out.println("被取走的元素为：" + t11);
				Thread.sleep(1000);
				Object t21 = m.take();
				System.out.println("被取走的元素为：" + t21);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t2");

		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}
