package com.projectmanage.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test1Ctrl {

	@RequestMapping(value="/test1.web")
	public String getTest1Page() {
	System.out.println("=========Test1Ctrl::getTest1Page()==================");
		return "test1";
	}
}
