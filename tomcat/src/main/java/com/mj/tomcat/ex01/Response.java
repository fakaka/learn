package com.mj.tomcat.ex01;

import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private Request request;
	private OutputStream output;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendResponse() throws IOException {
		String msg = "HTTP/1.1 200 \r\n" + "" + "\r\n" + "helloworld";
		try {
			output.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

}
