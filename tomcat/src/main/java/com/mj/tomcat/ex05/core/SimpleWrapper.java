package com.mj.tomcat.ex05.core;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.catalina.Container;
import org.apache.catalina.Loader;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;

public class SimpleWrapper implements Wrapper, Pipeline {

	// the servlet instance
	private Servlet instance = null;

	private String servletClass;

	private Loader loader;

	private String name;

	private SimplePipeline pipeline = new SimplePipeline(this);

	protected Container parent = null;

	public SimpleWrapper() {
		pipeline.setBasic(new SimpleWrapperValve());
	}

	@Override
	public synchronized void addValve(Valve valve) {
		pipeline.addValve(valve);
	}

	@Override
	public Servlet allocate() throws ServletException {
		// Load and initialize our instance if necessary
		if (instance == null) {
			try {
				//				instance = loadServlet();
			} catch (ServletException e) {
				throw e;
			} catch (Throwable e) {
				throw new ServletException("Cannot allocate a servlet instance", e);
			}
		}
		return instance;
	}

}
