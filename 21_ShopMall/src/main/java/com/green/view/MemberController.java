package com.green.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/*
	 * �α��� ȭ�� ǥ��
	 */

	@GetMapping(value = "/login_form")
	public String loginView() {
		return "member/login";
	}

	/*
	 * ����� �α��� ó��
	 * 
	 * vo ��ü���� id, pwd ������ �о�ͼ� ����� ����
	 */
	@PostMapping(value = "/login")
	public String loginAction(MemberVO vo, Model model) {

		MemberVO loginUser = null;

		int result = memberService.loginID(vo);

		if (result == 1) { // ����� ���� ����

			// ����� ������ ��ȸ�Ͽ� Session ��ü�� ����
			loginUser = memberService.getMember(vo.getId());
			model.addAttribute("loginUser", loginUser); // @SessionAttribute�� �����Ͽ� ���ǿ��� �����
			
			return "redirect:index";
			
		} else { // ����� ���� ����
			return "member/login_fail";
		}
	}

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
	 * ȸ������ ó��
	 */
	@PostMapping(value = "/join")
	public String joinAction(@RequestParam(value = "addr1") String addr1, @RequestParam(value = "addr2") String addr2,
			MemberVO vo) {
		vo.setAddress(addr1 + " " + addr2);
		memberService.insertMember(vo);

		return "member/login";
	}

	/*
	 * �����ȣ, �ּ�ã�� ȭ�� ���
	 */
	@GetMapping(value = "/find_zip_num")
	public String findZipNumView() {

		return "member/findZipNum";

	}

	/*
	 * ���̸����� �ּ� ã��
	 */
	@PostMapping(value = "/find_zip_num")
	public String findZipNumAction(AddressVO vo, Model model) {

		List<AddressVO> addrList = memberService.selectAddressByDong(vo.getDong());

		model.addAttribute("addressList", addrList);

		return "member/findZipNum";
	}
}
