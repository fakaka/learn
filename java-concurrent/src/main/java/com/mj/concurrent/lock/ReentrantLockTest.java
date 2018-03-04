package com.mj.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private Lock lock = new ReentrantLock();

	public void method1() {
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入 method 1..");
			Thread.sleep(1000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出 method 1..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void method2() {
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入 method 2..");
			Thread.sleep(2000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出 method 2..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		final ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
		Thread t1 = new Thread((Runnable) () -> {
			reentrantLockTest.method1();
			reentrantLockTest.method2();
		}, "t1");

		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(ur.lock.getQueueLength());
	}

}
