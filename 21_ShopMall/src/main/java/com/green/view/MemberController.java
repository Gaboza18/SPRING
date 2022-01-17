package com.green.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

	@GetMapping(value = "/contract")
	public String contracView() {
		return "member/contract";
	}

	@PostMapping(value = "join_form")
	public String joinView() {
		return "member/join";
	}
}
