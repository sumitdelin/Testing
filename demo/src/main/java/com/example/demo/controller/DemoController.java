package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@GetMapping("/test")
	public String getTestMessage() {
		return "test on ...";
	}
	public String getTestMessage1() {
		return "test on ...1";
	}
	public String getTestMessage2() {
		return "test on ...2";
	}
}
