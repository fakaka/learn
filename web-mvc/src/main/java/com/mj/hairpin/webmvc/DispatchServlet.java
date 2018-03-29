package com.mj.hairpin.webmvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mj.hairpin.webmvc.annotation.Controller;
import com.mj.hairpin.webmvc.annotation.RequestMapping;

@WebServlet(name = "dispatchServlet", urlPatterns = "/", loadOnStartup = 1)
public class DispatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Method> handlerMappings = new HashMap<>();

	private Map<String, Object> classMapping = new HashMap<>();

	@Override
	public void init() throws ServletException {
		try {
			inithandlerMapping();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void inithandlerMapping() throws Exception {
		Class<?> ctrl = Class.forName("com.mj.hairpin.webmvc.controller.HelloController");
		Controller controller = ctrl.getAnnotation(Controller.class);
		if (controller != null) {
			RequestMapping requestMapping = ctrl.getAnnotation(RequestMapping.class);
			if (requestMapping != null) {
				String baseUrl = requestMapping.value();
				Object newInstance = ctrl.newInstance();
				Method[] methods = ctrl.getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method m = methods[i];
					RequestMapping methodRequestMapping = m.getAnnotation(RequestMapping.class);
					if (methodRequestMapping != null) {
						String methodUrl = methodRequestMapping.value();
						handlerMappings.put(baseUrl + methodUrl, m);
						classMapping.put(baseUrl + methodUrl, newInstance);
					}
				}
			}
		}

	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String requestPath = req.getServletPath();

		System.out.println(req.getMethod() + " " + requestPath);
		Method method = handlerMappings.get(requestPath);
		Object ctrl = classMapping.get(requestPath);
		if (method != null) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			Object[] args = new Object[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				String name = parameterTypes[i].getSimpleName();
				if ("HttpServletRequest".equals(name)) {
					args[i] = req;
				} else if ("HttpServletResponse".equals(name)) {
					args[i] = req;
				} else {
					args[i] = null;
				}
			}
			Object invoke = method.invoke(ctrl, args);
			String res = processResult(invoke);
			resp.getWriter().println(res);
		} else {
			resp.getWriter().println("no mapping");
		}

	}

	private String processResult(Object invoke) {
		if (invoke == null) {
			return "";
		}
		return invoke.toString();
	}

}
