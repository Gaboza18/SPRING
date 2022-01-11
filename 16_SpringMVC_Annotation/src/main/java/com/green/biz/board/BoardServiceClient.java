package com.green.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	private static BoardService boardService;

	public static void main(String[] args) throws Exception {
		
		// �����̳� ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// IOC �����̳� ���� boardService ��ü�� ���� ����
		BoardService boardService = (BoardService) container.getBean("boardService");

		// �Խñ� ���
		BoardVO board = new BoardVO();
		
		// board.setSeq(100);
		board.setTitle("�ӽ� ����");
		board.setWriter("�׽���");
		board.setContent("Ʈ������ ó�� �׽�Ʈ��");
		
		boardService.insertBoard(board);
		
		// �Խñ� ����
		
		/*
		BoardVO boardDelete = new BoardVO();
		boardDelete.setSeq(510);
		boardService.deleteBoard(boardDelete);
		*/
		
		List<BoardVO> boardList = boardService.getBoardList();

		for (BoardVO vo : boardList) {
			System.out.printf("%3d. ����: %s  �ۼ���:%s  �ۼ���:%s\n ����:%s\n\n", vo.getSeq(), vo.getTitle(), vo.getWriter(),
					vo.getRegDate(), vo.getContent());
		}

		container.close();
	}

}
