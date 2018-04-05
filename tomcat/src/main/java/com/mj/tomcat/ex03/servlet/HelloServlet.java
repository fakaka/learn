package com.mj.tomcat.ex03.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class HelloServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		System.out.println("do service");
		resp.getWriter().write("hello Servlet3");
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("destory");
	}

}
