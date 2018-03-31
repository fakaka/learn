package com.mj.tomcat.ex01;

import java.io.InputStream;

public class Request {

	private InputStream input;

	private String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	public void parse() {
		StringBuffer sb = new StringBuffer();
		int n = 0;
		byte[] buf = new byte[2048];
		try {
			n = input.read(buf);
		} catch (Exception e) {
			n = -1;
		}

		for (int i = 0; i < n; i++) {
			sb.append((char) buf[i]);
		}

		System.out.println(sb.toString());

		uri = parseUri(sb.toString());

	}

	private String parseUri(String string) {
		int start = string.indexOf(' ');
		int end;
		if (start > 0) {
			end = string.indexOf(' ', start + 1);
			if (end > start) {
				return string.substring(start + 1, end);
			}
		}
		return null;

	}

	public String getUri() {
		return uri;
	}

}
