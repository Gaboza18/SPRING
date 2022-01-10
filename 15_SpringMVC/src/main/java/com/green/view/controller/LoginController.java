package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("�α��� ó��");

		// 1. ����� �Է� ���� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 2. DB ����ó��
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);

		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo); // ����� ������ �˻��Ѵ�

		// ȭ�� ���� ó��
		ModelAndView modelView = new ModelAndView();

		if (user != null) {
			modelView.setViewName("redirect:getBoardList.do"); // ���� �α��ν�
		} else {
			modelView.setViewName("login"); // viewResolver ���� .jsp �����ȴ�
		}

		return modelView;
	}

}
