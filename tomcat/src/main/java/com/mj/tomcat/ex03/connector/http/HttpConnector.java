package com.mj.tomcat.ex03.connector.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {

	private boolean shutdown = false;

	private String scheme = "http";

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1223, 1);
			System.out.println("http://localhost:1223");
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!shutdown) {
			Socket socket = null;

			try {
				socket = serverSocket.accept();

				HttpProcessor processor = new HttpProcessor();
				processor.process(socket);

				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

	}

	public void start() {
		new Thread(this).start();
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

}
