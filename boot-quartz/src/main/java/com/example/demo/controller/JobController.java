package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MyScheduler;

@RestController
@RequestMapping(value = "/job")
public class JobController {

	@Autowired
	public MyScheduler myScheduler;

	@GetMapping
	public void addjob() throws Exception {
		myScheduler.scheduleJobs();
	}

}
