package com.mj.concurrent.basic;

public class MyThreadLocal {

	public ThreadLocal<String> th = new ThreadLocal<>();

	public void setTh(String value) {
		th.set(value);
	}

	public void getTh() {
		System.out.println(Thread.currentThread().getName() + ":" + th.get());
	}

	public static void main(String[] args) throws InterruptedException {

		final MyThreadLocal ct = new MyThreadLocal();
		Thread t1 = new Thread((Runnable) () -> {
			ct.setTh("张三");
			ct.getTh();
		}, "t1");

		Thread t2 = new Thread((Runnable) () -> {
			try {
				Thread.sleep(1000);
				ct.setTh("李四");
				ct.getTh();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t2");

		t1.start();
		t2.start();
	}

}
