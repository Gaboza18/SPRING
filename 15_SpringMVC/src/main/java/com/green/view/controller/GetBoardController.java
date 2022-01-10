package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("�Խñ� ����ȸ ó��");

		// �Խñ� ��ȣ �Է°� ����
		String seq = request.getParameter("seq");

		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));

		BoardDAO boardDao = new BoardDAO();
		BoardVO board = boardDao.getBoard(vo);

		// ����ȭ�� ����
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("board", board);
		modelView.setViewName("getBoard"); // viewResolver ���� .jsp �����ȴ�

		return modelView;
	}

}
