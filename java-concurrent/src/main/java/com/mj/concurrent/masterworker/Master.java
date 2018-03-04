package com.mj.concurrent.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	// 任务的容器
	private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();

	// worker的集合
	private HashMap<String, Thread> workers = new HashMap<>();

	// 每一个worker执行任务的结果集合
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	public Master(Worker worker, int workerCount) {
		worker.setWorkQueue(this.taskQueue);
		worker.setResultMap(this.resultMap);

		for (int i = 0; i < workerCount; i++) {
			this.workers.put(Integer.toString(i), new Thread(worker));
		}

	}

	// 提交任务
	public void submit(Task task) {
		this.taskQueue.add(task);
	}

	// 启动所有的worker方法去执行任务
	public void execute() {
		for (Map.Entry<String, Thread> entry : workers.entrySet()) {
			entry.getValue().start();
		}
	}

	// 运行是否结束
	public boolean isComplete() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if (me.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}

	// 计算结果
	public int getResult() {
		int priceResult = 0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			priceResult += (Integer) me.getValue();
		}
		return priceResult;
	}

}
