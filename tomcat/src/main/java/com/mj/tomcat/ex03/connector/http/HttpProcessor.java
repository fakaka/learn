package com.mj.tomcat.ex03.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.tomcat.util.res.StringManager;

import com.mj.tomcat.ex03.ServletProcessor;
import com.mj.tomcat.ex03.StaticResourceProcessor;

public class HttpProcessor {

	private SocketInputStream input = null;

	private OutputStream output = null;

	private HttpRequest request;

	private HttpRequestLine requestLine = new HttpRequestLine();

	private HttpResponse response;

	protected StringManager sm = StringManager.getManager("com.mj.tomcat.ex03.connector.http.HttpProcessor");

	public void process(Socket socket) {

		try {
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();

			request = new HttpRequest(input);

			response = new HttpResponse(output);
			response.setRequest(request);

			response.setHeader("Server", "Hairpin Servlet Container");

			parseRequest(input, output);
			parseHeaders(input);

			if (request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}

			socket.close();

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * String uri = request.getUri(); String servletName =
		 * uri.substring(uri.lastIndexOf("/") + 1); URLClassLoader loader = null; File
		 * classPath = new File(System.getProperty("user.dir") + "/" +
		 * "target/classes/");
		 * 
		 * try { URL[] urls = new URL[1]; // URLStreamHandler streamHandler = null;
		 * String repository = (new URL("file", null, classPath.getCanonicalPath() +
		 * File.separator)).toString();
		 * 
		 * urls[0] = new URL(null, repository, (URLStreamHandler) null); loader = new
		 * URLClassLoader(urls);
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * Class clazz = null; try { clazz =
		 * loader.loadClass("com.mj.tomcat.ex02.servlet." + servletName); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); }
		 * 
		 * Servlet servlet = null;
		 * 
		 * try {
		 * 
		 * servlet = (Servlet) clazz.newInstance(); servlet.service(request, response);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	private void parseRequest(SocketInputStream input2, OutputStream output2) throws IOException, ServletException {
		input.readRequestLine(requestLine);

		// Parse the incoming request line
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		if (method.length() < 1) {
			throw new ServletException("Missing HTTP request method");
		} else if (requestLine.uriEnd < 1) {
			throw new ServletException("Missing HTTP request URI");
		}
		String uri = null;

		int question = requestLine.indexOf("?");
		if (question >= 0) {
			request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
			uri = new String(requestLine.uri, 0, question);
		} else {
			request.setQueryString(null);
			uri = new String(requestLine.uri, 0, requestLine.uriEnd);
		}

		// Checking for an absolute URI (with the HTTP protocol)
		if (!uri.startsWith("/")) {
			int pos = uri.indexOf("://");
			if (pos != -1) {
				pos = uri.indexOf("/", pos + 3);
				if (pos == -1) {
					uri = "";
				} else {
					uri = uri.substring(pos);
				}
			}
		}

		// Parse any requested session ID out of the request URI
		String match = ";jsessionid=";
		int semicolonStart = uri.indexOf(match);
		if (semicolonStart >= 0) {
			String rest = uri.substring(semicolonStart + match.length());
			int semicolonEnd = rest.indexOf(';');
			if (semicolonEnd >= 0) {
				request.setRequestedSessionId(rest.substring(0, semicolonEnd));
				rest = rest.substring(semicolonEnd);
			} else {
				request.setRequestedSessionId(rest);
				rest = "";
			}
			request.setRequestedSessionURL(true);
			uri = uri.substring(0, semicolonStart) + rest;
		} else {
			request.setRequestedSessionId(null);
			request.setRequestedSessionURL(false);
		}

		// Normalize URI (using String operations at the moment)
		String normalizedUri = normalize(uri);

		// Set the corresponding request properties
		request.setMethod(method);
		request.setProtocol(protocol);
		if (normalizedUri != null) {
			request.setRequestURI(normalizedUri);
		} else {
			throw new ServletException("Invalid URI: " + uri + "'");
		}

	}

	/**
	 * Return a context-relative path, beginning with a "/", that represents the
	 * canonical version of the specified path after ".." and "." elements are
	 * resolved out. If the specified path attempts to go outside the boundaries of
	 * the current context (i.e. too many ".." path elements are present), return
	 * <code>null</code> instead.
	 *
	 * @param path
	 *            Path to be normalized
	 */
	protected String normalize(String path) {
		if (path == null) {
			return null;
		}
		// Create a place for the normalized path
		String normalized = path;

		// Normalize "/%7E" and "/%7e" at the beginning to "/~"
		if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e")) {
			normalized = "/~" + normalized.substring(4);
		}

		// Prevent encoding '%', '/', '.' and '\', which are special reserved
		// characters
		if ((normalized.indexOf("%25") >= 0) || (normalized.indexOf("%2F") >= 0) || (normalized.indexOf("%2E") >= 0)
				|| (normalized.indexOf("%5C") >= 0) || (normalized.indexOf("%2f") >= 0) || (normalized.indexOf("%2e") >= 0)
				|| (normalized.indexOf("%5c") >= 0)) {
			return null;
		}

		if (normalized.equals("/.")) {
			return "/";
		}

		// Normalize the slashes and add leading slash if necessary
		if (normalized.indexOf('\\') >= 0) {
			normalized = normalized.replace('\\', '/');
		}
		if (!normalized.startsWith("/")) {
			normalized = "/" + normalized;
		}

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0) {
				break;
			}
			if (index == 0) {
				return (null); // Trying to go outside our context
			}
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		// Declare occurrences of "/..." (three or more dots) to be invalid
		// (on some Windows platforms this walks the directory tree!!!)
		if (normalized.indexOf("/...") >= 0) {
			return (null);
		}

		// Return the normalized path that we have completed
		return (normalized);

	}

	private void parseHeaders(SocketInputStream input) throws IOException, ServletException {
		while (true) {

			HttpHeader header = new HttpHeader();

			// Read the next header
			input.readHeader(header);

			if (header.nameEnd == 0) {
				if (header.valueEnd == 0) {
					return;
				} else {
					throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
				}
			}

			String name = new String(header.name, 0, header.nameEnd);
			String value = new String(header.value, 0, header.valueEnd);
			request.addHeader(name, value);

			// do something for some headers, ignore others.
			if (name.equals("cookie")) {
				Cookie cookies[] = parseCookieHeader(value);
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("jsessionid")) {
						// Override anything requested in the URL
						if (!request.isRequestedSessionIdFromCookie()) {
							// Accept only the first session id cookie
							request.setRequestedSessionId(cookies[i].getValue());
							request.setRequestedSessionCookie(true);
							request.setRequestedSessionURL(false);
						}
					}
					request.addCookie(cookies[i]);
				}
			} else if (name.equals("content-length")) {
				int n = -1;
				try {
					n = Integer.parseInt(value);
				} catch (Exception e) {
					throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
				}
				request.setContentLength(n);
			} else if (name.equals("content-type")) {
				request.setContentType(value);
			}

		}
	}

	private Cookie[] parseCookieHeader(String header) {

		if ((header == null) || (header.length() < 1)) {
			return (new Cookie[0]);
		}

		ArrayList<Cookie> cookies = new ArrayList<>();
		while (header.length() > 0) {
			int semicolon = header.indexOf(';');
			if (semicolon < 0) {
				semicolon = header.length();
			}
			if (semicolon == 0) {
				break;
			}
			String token = header.substring(0, semicolon);
			if (semicolon < header.length()) {
				header = header.substring(semicolon + 1);
			} else {
				header = "";
			}
			try {
				int equals = token.indexOf('=');
				if (equals > 0) {
					String name = token.substring(0, equals).trim();
					String value = token.substring(equals + 1).trim();
					cookies.add(new Cookie(name, value));
				}
			} catch (Throwable e) {
			}
		}

		return (cookies.toArray(new Cookie[cookies.size()]));
	}

}
