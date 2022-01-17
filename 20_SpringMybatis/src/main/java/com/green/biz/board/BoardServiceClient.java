package com.green.biz.board;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	private static BoardService boardService;
	
	public static void main(String[] args) throws BeansException {
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// IOC �����̳ʿ��� boardService ��ü�� ���� ����.
		boardService = (BoardService)container.getBean("boardService");

		// �Խñ� ���
		BoardVO board = new BoardVO();
		//board.setSeq(100);
		board.setTitle("�ӽ� ����3");
		board.setWriter("������2");
		board.setContent("�ӽ� �����Դϴ�.2");
		
		boardService.insertBoard(board);
		
		//displayBoardList();
		BoardVO vo1 = new BoardVO();
		List<BoardVO> boardList = boardService.getBoardList(vo1);
		
		for (BoardVO vo : boardList) {
			System.out.printf("%3d ����:%s  �ۼ���:%s  �ۼ���:%s\n%s\n\n",
							vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getRegDate(),
							vo.getContent());
		}
	}

}







