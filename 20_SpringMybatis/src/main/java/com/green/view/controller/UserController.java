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

	// 로그인 화면 표시
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println("로그인 화면으로 이동");

		
		// Command의 객체의 저장 내용을 jsp화면으로 전달
		vo.setId("user1");
		vo.setPassword("user1");
		
		return "login.jsp";
	}
	
	// 로그인 처리
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDao, HttpSession session) {
		System.out.println("로그인 처리");

		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		UserVO user = userDao.getUser(vo);
		
		// 화면응답 처리
		if (user != null) { // 정상 로그인
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
	
	// 로그아웃 처리
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 처리");
		
		session.invalidate();
		
		return "login.jsp";
	}
}
