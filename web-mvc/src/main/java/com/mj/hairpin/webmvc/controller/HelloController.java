package com.mj.hairpin.webmvc.controller;

import javax.servlet.http.HttpServletRequest;

import com.mj.hairpin.webmvc.annotation.Controller;
import com.mj.hairpin.webmvc.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("/hi")
	public String hi() {
		return "hi";
	}

	@RequestMapping("/hi2")
	public void hi2(HttpServletRequest req) {

		System.out.println("handle " + req.getRequestURI());

	}

}
