package com.mj.mq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

	private static final String DEFAULT_GROUP = "DEFAULT";

	private Map<String, BlockingQueue<String>> queues;

	public MessageQueue() {
		queues = new HashMap<>();
	}

	public void push(String message) {
		push(DEFAULT_GROUP, message);
	}

	public void push(String group, String message) {
		BlockingQueue<String> blockingQueue = queues.get(group);
		if (blockingQueue == null) {
			blockingQueue = new LinkedBlockingQueue<>(100);
		}
		blockingQueue.add(message);
	}

	public String pop() {
		return pop(DEFAULT_GROUP);
	}

	public String pop(String group) {
		BlockingQueue<String> blockingQueue = queues.get(group);
		if (blockingQueue != null) {
			return blockingQueue.remove();
		}
		return null;
	}

}
