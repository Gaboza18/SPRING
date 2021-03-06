package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("게시글 목록조회 처리");

		BoardVO vo = new BoardVO();
		BoardDAO boardDao = new BoardDAO();
		List<BoardVO> boardList = boardDao.getBoardList();

		// 검색 결과를 세션에 저장하고 목록화면 호출
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);

		return "getBoardList"; // = getBoardList.jsp
	}

}
