package com.green.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.biz.dto.MemberVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.qna.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qnaService;

	/*
	 * 회원 id를 조건으로 모든 Qna 조회
	 */

	@GetMapping(value = "/qna_list")
	public String qnaList(HttpSession session, Model model) {

		// 회원 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());

			model.addAttribute("qnaList", qnaList);

			return "qna/qnaList";
		}
	}
	
	/*
	 * 게시판 글쓰기  
	 */
	
	@GetMapping(value = "/qna_write_form")
	public String qnaWrite(HttpSession session, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			return "qna/qnaWrite";
		}
	}

}
