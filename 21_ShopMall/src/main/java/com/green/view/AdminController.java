package com.green.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping(value="/admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}
}
