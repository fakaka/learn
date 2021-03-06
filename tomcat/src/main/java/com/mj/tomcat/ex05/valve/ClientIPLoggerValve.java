package com.mj.tomcat.ex05.valve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Valve;
import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import com.mj.tomcat.ex05.ValveContext;

public class ClientIPLoggerValve implements Valve, Contained {

	protected Container container;

	public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {

		// Pass this request on to the next valve in our pipeline
		valveContext.invokeNext(request, response);
		System.out.println("Client IP Logger Valve");
		ServletRequest sreq = request.getRequest();
		System.out.println(sreq.getRemoteAddr());
		System.out.println("------------------------------------");
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public Valve getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNext(Valve valve) {
		// TODO Auto-generated method stub

	}

	@Override
	public void backgroundProcess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void event(Request request, Response response, CometEvent event) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}
}
