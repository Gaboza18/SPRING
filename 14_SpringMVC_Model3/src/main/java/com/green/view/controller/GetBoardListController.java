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

		System.out.println("�Խñ� �����ȸ ó��");

		BoardVO vo = new BoardVO();
		BoardDAO boardDao = new BoardDAO();
		List<BoardVO> boardList = boardDao.getBoardList();

		// �˻� ����� ���ǿ� �����ϰ� ���ȭ�� ȣ��
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);

		return "getBoardList"; // = getBoardList.jsp
	}

}
