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
	 * ȸ�� id�� �������� ��� Qna ��ȸ
	 */

	@GetMapping(value = "/qna_list")
	public String qnaList(HttpSession session, Model model) {

		// ȸ�� �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId()); // ȸ�����̵�� �ۼ��� �Խñ� ��ü ��ȸ �Ѵ�

			model.addAttribute("qnaList", qnaList); // �Խñ� �迭 ȭ�鿡 ����

			return "qna/qnaList"; // �Խ��� ������� �̵��Ѵ�
		}
	}

	/*
	 * �Խ��� �۾��� �ҷ�����
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
	 * �Խ��� �۵��
	 */

	@PostMapping(value = "/qna_write")
	public String qnaWrite(HttpSession session, QnaVO vo) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			vo.setId(loginUser.getId()); // ȭ�鿡�� �о�� �Ķ����(subject, content) ����� ���̵� ���� QnaVO ��ü ����
			qnaService.insertQna(vo); // qnaService ��ü���� insertQna(qnaVO id) ȣ�� �Խñ� ����

			return "redirect:qna_list";
		}
	}

	/*
	 * �Խñ� �󼼺���
	 */
	
	@GetMapping(value = "/qna_view")
	public String qnaView(HttpSession session, QnaVO vo, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			vo.setId(loginUser.getId());

			QnaVO qna = qnaService.getQna(vo.getQseq()); // qnaService ��ü���� getQna() �����Ͽ� ��ȸ�� ��� qnaVO Ű�� model ��ü�� ����
			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";
		}
	}
}
