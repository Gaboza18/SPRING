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

		System.out.println("로그인 처리");

		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 2. DB 연동처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);

		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo); // 사용자 정보를 검색한다

		// 화면 응답 처리
		ModelAndView modelView = new ModelAndView();

		if (user != null) {
			modelView.setViewName("redirect:getBoardList.do"); // 정상 로그인시
		} else {
			modelView.setViewName("login"); // viewResolver 에서 .jsp 생성된다
		}

		return modelView;
	}

}
