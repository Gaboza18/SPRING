package com.green.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId()); // 회원아이디로 작성한 게시글 전체 조회 한다

			model.addAttribute("qnaList", qnaList); // 게시글 배열 화면에 전송

			return "qna/qnaList"; // 게시판 목록으로 이동한다
		}
	}

	/*
	 * 게시판 글쓰기 불러오기
	 */

	@GetMapping(value = "/qna_write_form")
	public String qnaWriteView(HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			return "qna/qnaWrite";
		}
	}

	/*
	 * 게시판 글등록
	 */

	@PostMapping(value = "/qna_write")
	public String qnaWrite(HttpSession session, QnaVO vo) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			vo.setId(loginUser.getId()); // 화면에서 읽어온 파라미터(subject, content) 사용자 아이디 정보 QnaVO 객체 저장
			qnaService.insertQna(vo); // qnaService 객체에서 insertQna(qnaVO id) 호출 게시글 저장

			return "redirect:qna_list";
		}
	}

	/*
	 * 게시글 상세보기
	 */
	
	@GetMapping(value = "/qna_view")
	public String qnaView(HttpSession session, QnaVO vo, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			vo.setId(loginUser.getId());

			QnaVO qna = qnaService.getQna(vo.getQseq()); // qnaService 객체에서 getQna() 실행하여 조회한 결과 qnaVO 키로 model 객체에 저장
			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";
		}
	}
}
