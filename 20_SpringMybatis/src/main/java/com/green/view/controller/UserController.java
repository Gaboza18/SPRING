package com.green.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dao.UserDAO;
import com.green.biz.dto.UserVO;

@Controller
public class UserController {

	// �α��� ȭ�� ǥ��
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println("�α��� ȭ������ �̵�");

		
		// Command�� ��ü�� ���� ������ jspȭ������ ����
		vo.setId("user1");
		vo.setPassword("user1");
		
		return "login.jsp";
	}
	
	// �α��� ó��
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDao, HttpSession session) {
		System.out.println("�α��� ó��");

		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("���̵�� �ݵ�� �Է��ؾ� �մϴ�.");
		}
		
		UserVO user = userDao.getUser(vo);
		
		// ȭ������ ó��
		if (user != null) { // ���� �α���
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
	
	// �α׾ƿ� ó��
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("�α׾ƿ� ó��");
		
		session.invalidate();
		
		return "login.jsp";
	}
}
