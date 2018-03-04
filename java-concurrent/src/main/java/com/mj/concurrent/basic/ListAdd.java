package com.mj.concurrent.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * wait() 和 notify()
 * 
 * @author MJ
 *
 */
public class ListAdd {

	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("mj");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd list = new ListAdd();
		final Object lock = new Object();
		Thread t1 = new Thread((Runnable) () -> {
			try {
				synchronized (lock) {
					System.out.println("t1启动..");
					for (int i = 0; i < 10; i++) {
						list.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
						if (list.size() == 5) {
							System.out.println("已经发出通知..");
							lock.notify();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "t1");

		Thread t2 = new Thread((Runnable) () -> {
			synchronized (lock) {
				System.out.println("t2启动..");
				if (list.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
				throw new RuntimeException();
			}
		}, "t2");
		t2.start();
		t1.start();

	}

}
