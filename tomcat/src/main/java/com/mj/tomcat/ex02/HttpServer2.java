package com.mj.tomcat.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {

	private boolean shutdown = false;

	public void await() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1223, 1);
			System.out.println("server start at port : 1223");
			System.out.println("http://localhost:1223");
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;

			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				Request request = new Request(input);
				request.parse();

				Response response = new Response(output);
				response.setRequest(request);

				String uri = request.getUri();
				if (uri != null) {
					if (uri.startsWith("/servlet/")) {
						ServletProcessor processor = new ServletProcessor();
						output.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n".getBytes());
						processor.process(request, response);
					} else {
						response.sendStaticResource();
					}
				} else {
					System.out.println("uri == null");
				}
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		HttpServer2 httpServer = new HttpServer2();
		httpServer.await();
	}

}
