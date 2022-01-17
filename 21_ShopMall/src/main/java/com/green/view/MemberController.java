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
	 * 로그인 화면 표시
	 */

	@GetMapping(value = "/login_form")
	public String loginView() {
		return "member/login";
	}

	/*
	 * 사용자 로그인 처리
	 * 
	 * vo 객체에서 id, pwd 정보를 읽어와서 사용자 인증
	 */
	@PostMapping(value = "/login")
	public String loginAction(MemberVO vo, Model model) {

		MemberVO loginUser = null;

		int result = memberService.loginID(vo);

		if (result == 1) { // 사용자 인증 성공

			// 사용자 정보를 조회하여 Session 객체에 저장
			loginUser = memberService.getMember(vo.getId());
			model.addAttribute("loginUser", loginUser); // @SessionAttribute로 지정하여 세션에도 저장됨
			
			return "redirect:index";
			
		} else { // 사용자 인증 실패
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

		// 입력한 id값(vo)을 confirmID 함수에서 판별하여 result 변수에 넣는다
		int result = memberService.confirmID(vo.getId());

		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());

		return "member/idcheck";
	}

	/*
	 * 사용할 id를 join(회원가입) 화면에 전송
	 */

	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(MemberVO vo, Model model) {

		model.addAttribute("id", vo.getId());
		model.addAttribute("reid", vo.getId()); // id 중복확인 필드

		return "member/join";
	}

	/*
	 * 회원가입 처리
	 */
	@PostMapping(value = "/join")
	public String joinAction(@RequestParam(value = "addr1") String addr1, @RequestParam(value = "addr2") String addr2,
			MemberVO vo) {
		vo.setAddress(addr1 + " " + addr2);
		memberService.insertMember(vo);

		return "member/login";
	}

	/*
	 * 우편번호, 주소찾기 화면 출력
	 */
	@GetMapping(value = "/find_zip_num")
	public String findZipNumView() {

		return "member/findZipNum";

	}

	/*
	 * 동이름으로 주소 찾기
	 */
	@PostMapping(value = "/find_zip_num")
	public String findZipNumAction(AddressVO vo, Model model) {

		List<AddressVO> addrList = memberService.selectAddressByDong(vo.getDong());

		model.addAttribute("addressList", addrList);

		return "member/findZipNum";
	}
}
