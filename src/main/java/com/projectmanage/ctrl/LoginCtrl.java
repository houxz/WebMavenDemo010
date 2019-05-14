package com.projectmanage.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectmanage.common.CommonConstants;
import com.projectmanage.common.RoleType;
import com.projectmanage.pojo.EmployeeModel;
import com.projectmanage.service.EmapgoAccountService;

@Controller
@RequestMapping("/login.web")
public class LoginCtrl  extends BaseCtrl{

	@Autowired
	private EmapgoAccountService emapgoAccountService;
	
	@RequestMapping()
	public String  LoadPage(Model model , HttpSession session, HttpServletRequest request) {
		System.out.println("==========LoginCtrl::LoadPage()==================");
		
		try {
			
			String account = getLoginAccount(session);
			Integer userid = 0;
			String realname = new String();
			
			if(hasRole(request, RoleType.ROLE_SUPERADMIN.toString() ) ) {
				userid = RoleType.ROLE_SUPERADMIN.getValue();
				realname = RoleType.ROLE_SUPERADMIN.getDes();
			} else if( hasRole( request, RoleType.ROLE_YANFAADMIN.toString())) {
				
			} else if( hasRole(request,RoleType.ROLE_ADMIN.toString())) {
				
			}
			else {
				
				EmployeeModel record = new EmployeeModel();
				record.setUsername(account);
				EmployeeModel user = emapgoAccountService.getOneEmployeeWithCache(record);
				
				
				userid = 417;
				realname = "hxz3";
			}
			
			System.out.println("====LoginCtrl:login2========");
			session.setAttribute(CommonConstants.SESSION_USER_ACC, account);
			session.setAttribute(CommonConstants.SESSION_USER_ID, userid);
			session.setAttribute(CommonConstants.SESSION_USER_NAME, realname);
			
			if( hasRole(request , RoleType.ROLE_ADMIN.toString())) {
				
			}else if( hasRole( request,RoleType.ROLE_POIVIDEOEDIT.toString())) {
				System.out.println("====LoginCtrl:login3========");
				
				return "redirect:processesmanage.web";
			}else {
				System.out.println("====LoginCtrl:login4========");
				return "redirect:processesmanage.web";
			}
			
			
		}catch(Exception e) {
			
		}
		
		
		return "login";
	}
}
