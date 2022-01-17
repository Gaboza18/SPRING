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
	 * ID �ߺ� üũ ȭ�� ���
	 */

	@GetMapping(value = "/id_check_form") // action= id_check_form
	public String idCheckView(MemberVO vo, Model model) {

		model.addAttribute("id", vo.getId());

		return "member/idcheck";
	}

	/*
	 * ID �ߺ� üũ ����
	 */

	@PostMapping(value = "/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {

		// �Է��� id��(vo)�� confirmID �Լ����� �Ǻ��Ͽ� result ������ �ִ´�
		int result = memberService.confirmID(vo.getId());

		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());

		return "member/idcheck";
	}

	/*
	 * ����� id�� join(ȸ������) ȭ�鿡 ����
	 */

	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(MemberVO vo, Model model) {

		model.addAttribute("id", vo.getId());
		model.addAttribute("reid", vo.getId()); // id �ߺ�Ȯ�� �ʵ�

		return "member/join";
	}
	
	/*
	 *  ȸ������ ó��
	 */
	@PostMapping(value="/join")
	public String joinAction(MemberVO vo) {
		
		memberService.insertMember(vo);
		
		return "member/login";
	}
}
