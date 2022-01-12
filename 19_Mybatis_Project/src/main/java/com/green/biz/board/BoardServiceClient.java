package com.green.biz.board;

import java.util.List;

import com.green.biz.dao.BoardDAO;
import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		
		BoardVO vo = new BoardVO(); // BoardVO ��ü ����
		BoardDAO bDao = new BoardDAO(); // BoardDAO ��ü ����
		

		vo.setTitle("Mybatis �����ӿ�ũ"); // vo ��ü�� '����'�� ����
		vo.setWriter("ȫ�浿");
		vo.setContent("Mybatis�� �̿��� ������MVC ���α׷� ����");
		
		bDao.insertBoard(vo); // vo�� �Ű������� �޾� insert�� ����
		
		// �Խñ� ��ȸ
		vo.setSearchCondition("TITLE"); // ������ ��������
		vo.setSearchKeyword(""); // ��ü��ȸ
		
		List<BoardVO> boardList = bDao.getBoardList(vo); // �Խù� �Է°� boardList �迭�� ����
		
		for(BoardVO board : boardList) { // boardList �迭���� �ϳ��� ���� board ��� ������ ��Ƽ� ����Ѵ�
			System.out.println(board);
		}
	}

}
