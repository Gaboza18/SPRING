package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 처리");

		HttpSession session = request.getSession();
		session.invalidate();

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:login.jsp"); // viewResolver 에서 .jsp 생성된다
		
		return modelView;
	}

}
