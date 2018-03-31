package com.mj.tomcat.ex02;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

public class ServletProcessor {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		File classPath = new File(System.getProperty("user.dir") + "/" + "target/classes/");

		try {
			URL[] urls = new URL[1];
			//			URLStreamHandler streamHandler = null;
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();

			urls[0] = new URL(null, repository, (URLStreamHandler) null);
			loader = new URLClassLoader(urls);

		} catch (IOException e) {
			e.printStackTrace();
		}

		Class clazz = null;
		try {
			clazz = loader.loadClass("com.mj.tomcat.ex02.servlet." + servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Servlet servlet = null;

		try {

			servlet = (Servlet) clazz.newInstance();
			servlet.service(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
