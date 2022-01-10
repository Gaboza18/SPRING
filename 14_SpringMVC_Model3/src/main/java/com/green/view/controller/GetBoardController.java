package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("�Խñ� ����ȸ ó��");

		// �Խñ� ��ȣ �Է°� ����
		String seq = request.getParameter("seq");

		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));

		BoardDAO boardDao = new BoardDAO();
		BoardVO board = boardDao.getBoard(vo);

		// ����ȭ�� ����
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		
		return "getBoard";
	}

}
