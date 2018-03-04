package com.mj.concurrent.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

	private ConcurrentLinkedQueue<Task> workQueue;

	private ConcurrentHashMap<String, Object> resultMap;

	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			Task input = workQueue.poll();
			if (input == null) {
				break;
			}
			Object output = handle(input);
			resultMap.put(Integer.toString(input.getId()), output);
		}

	}

	private Object handle(Task input) {
		try {
			TimeUnit.SECONDS.sleep(1);
			return input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
