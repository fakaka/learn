package com.mj.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

	final static int PROT = 8766;

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(PROT)) {
			System.out.println("Server start .. PROT :" + PROT);
			// 进行阻塞
			while (true) {
				Socket socket = server.accept();
				// 新建一个线程执行客户端的任务
				new Thread(new ServerHandler(socket)).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
