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

		board.setTitle("����� ���� �����");
		board.setWriter("�����");
		board.setContent("����� ���� ���� ���ּ���");

		boardService.insertBoard(board);

		BoardVO vol = new BoardVO();
		vol.setSeq(1); // ������ ��ȣ: 1 / ������
		board = boardService.getBoard(vol);

		List<BoardVO> boardList = boardService.getBoardList();
		
		System.out.println();
		System.out.println("--------------------- �Խ��� ���� ---------------------");

		for (BoardVO vo : boardList) {
			System.out.printf("%3d ����: %s  �ۼ���:%s  �ۼ���:%s\n ����:%s\n\n", vo.getSeq(), vo.getTitle(), vo.getWriter(),
					vo.getRegDate(), vo.getContent());
		}

		container.close();
	}

}
