package com.projectmanage.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/processesmanage.web")
public class ProcessesManageCtrl  extends BaseCtrl{

	@RequestMapping()
	public String openLader(Model mode, HttpServletRequest request, HttpSession  session) {
		System.out.println("========ProcessesManageCtrl:openLader(=============");
		return "processesmanage";
	}
}
