package com.green.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping(value="/contract")
	public String contracView() {
		return "member/contract";
	}
}
