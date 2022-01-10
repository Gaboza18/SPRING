package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�α׾ƿ� ó��");

		HttpSession session = request.getSession();
		session.invalidate();

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:login.jsp"); // viewResolver ���� .jsp �����ȴ�
		
		return modelView;
	}

}
