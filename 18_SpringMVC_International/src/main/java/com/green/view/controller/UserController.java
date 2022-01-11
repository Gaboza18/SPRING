package com.green.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

/*
 *  �α��� ��Ʈ�ѷ�
 */

@Controller
public class UserController {

	// �α��� ȭ�� ǥ��(client ��û�� ���� get/post ������� ����)
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {

		System.out.println("�α��� ȭ������ �̵�");

		// Command�� ��ü�� ���� ������ jspȭ�鿡 ����(����� �α��� â�� �ڵ����� �����Ѵ�)
		vo.setId("test");
		vo.setPassword("test123");

		return "login.jsp";

	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDao, HttpSession session) { // ���� ��ü ��Ʈ�ѷ��� ����

		System.out.println("�α��� ó��");

		// ����ó��
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("���̵�� �ݵ�� �Է��ϼ���.");
		}

		UserVO user = userDao.getUser(vo);

		// ȭ�� ����ó��
		if (user != null) {
			session.setAttribute("userName", user.getName()); // �α��ν� �α��� ���� �̸��� ���ǿ��� �����´�
			return "getBoardList.do"; // ���� �α��ν�
		} else {
			return "login.jsp"; // viewResolver ���� .jsp �����ȴ�
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {

		System.out.println("�α׾ƿ� ó��");

		session.invalidate();

		return "login.jsp";
	}
}
