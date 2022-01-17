package com.green.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping(value = "/contract")
	public String contracView() {
		return "member/contract";
	}

	@PostMapping(value = "/join_form")
	public String joinView() {
		return "member/join";
	}

	/*
	 * ID 중복 체크 화면 출력
	 */

	@GetMapping(value = "/id_check_form") // action= id_check_form
	public String idCheckView(MemberVO vo, Model model) {

		model.addAttribute("id", vo.getId());

		return "member/idcheck";
	}

	/*
	 * ID 중복 체크 수행
	 */

	@PostMapping(value = "/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {
		
		int result = memberService.confirmID(vo.getId());

		model.addAttribute("message", result);

		return "member/idcheck";
	}
}
