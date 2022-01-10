package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

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

		// 3.ȭ�� �׺���̼�
		if (user != null) {
			return "getBoardList.do"; // ���� �α��ν�
		} else {
			return "login"; // login.jsp ���Ͻ� .jsp �����ʴ´�
		}
	}

}
