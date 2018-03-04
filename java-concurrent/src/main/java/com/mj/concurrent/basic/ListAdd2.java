package com.mj.concurrent.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait() 和 notify()
 * 
 * @author MJ
 *
 */
public class ListAdd2 {

	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("mj");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final ListAdd2 list2 = new ListAdd2();
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread((Runnable) () -> {
			try {
				System.out.println("t1启动..");
				for (int i = 0; i < 10; i++) {
					list2.add();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
					Thread.sleep(500);
					if (list2.size() == 5) {
						System.out.println("已经发出通知..");
						countDownLatch.countDown();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}, "t1");

		Thread t2 = new Thread((Runnable) () -> {
			System.out.println("t2启动..");
			if (list2.size() != 5) {
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
			throw new RuntimeException();
		}, "t2");

		t2.start();
		t1.start();

	}

}
