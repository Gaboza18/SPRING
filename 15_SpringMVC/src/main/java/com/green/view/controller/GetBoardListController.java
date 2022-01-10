package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("게시글 목록조회 처리");
		
		BoardVO vo = new BoardVO();
		BoardDAO boardDao = new BoardDAO();
		List<BoardVO> boardList = boardDao.getBoardList();

		// 검색 결과를 세션에 저장하고 목록화면 호출
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("boardList", boardList);
		modelView.setViewName("getBoardList"); // viewResolver 에서 .jsp 생성된다
		
		return modelView; // = getBoardList.jsp
	}

}
