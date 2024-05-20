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
	public String getTestMessage3() {
		return "test on ...3";
	}
	public String getTestMessage4() {
		return "test on ...4";
	}
	public String getTestMessage5() {
		return "test on ...5";
	}
	public String getTestMessage6() {
		return "test on ...5";
	}
	public String getTestMessage7() {
		return "test on ...7";
	}
	
	public String getTestMessage9() {
		return "test on ...9";
	}
	public String getTestMessage10() {
		return "test on ...10";
	}	
	public String getTestMessage11() {
		return "test on ...11";
	}
}
