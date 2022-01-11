package com.green.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

/*
 *  로그인 컨트롤러
 */

@Controller
public class UserController {

	// 로그인 화면 표시(client 요청에 따라 get/post 방식으로 실행)
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {

		System.out.println("로그인 화면으로 이동");

		// Command의 객체의 저장 내용을 jsp화면에 전달(실행시 로그인 창에 자동으로 생성한다)
		vo.setId("test");
		vo.setPassword("test123");

		return "login.jsp";

	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDao, HttpSession session) { // 세션 객체 컨트롤러에 선언

		System.out.println("로그인 처리");

		// 예외처리
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력하세요.");
		}

		UserVO user = userDao.getUser(vo);

		// 화면 응답처리
		if (user != null) {
			session.setAttribute("userName", user.getName()); // 로그인시 로그인 계정 이름을 세션에서 가져온다
			return "getBoardList.do"; // 정상 로그인시
		} else {
			return "login.jsp"; // viewResolver 에서 .jsp 생성된다
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {

		System.out.println("로그아웃 처리");

		session.invalidate();

		return "login.jsp";
	}
}
