package com.mj.concurrent.masterworker;

import java.util.Random;

public class Test {

	public static void main(String[] args) {

		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(availableProcessors);
		Master master = new Master(new Worker(), 10);

		Random r = new Random();
		for (int i = 1; i <= 100; i++) {
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		master.execute();
		long start = System.currentTimeMillis();

		while (true) {
			if (master.isComplete()) {
				long end = System.currentTimeMillis() - start;
				int priceResult = master.getResult();
				System.out.println("最终结果：" + priceResult + ", 执行时间：" + end + " ms");
				break;
			}
		}

	}
}
