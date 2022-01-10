package com.green.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	private static BoardService boardService;

	public static void main(String[] args) throws Exception {
		
		// 컨테이너 설정
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// IOC 컨테이너 에서 boardService 객체를 제공 받음
		BoardService boardService = (BoardService) container.getBean("boardService");

		// 게시글 등록
		BoardVO board = new BoardVO();
		
		// board.setSeq(100);
		board.setTitle("임시 제목");
		board.setWriter("테스터");
		board.setContent("트랜젝션 처리 테스트용");
		
		boardService.insertBoard(board);
		
		// 게시글 삭제
		
		/*
		BoardVO boardDelete = new BoardVO();
		boardDelete.setSeq(510);
		boardService.deleteBoard(boardDelete);
		*/
		
		List<BoardVO> boardList = boardService.getBoardList();

		for (BoardVO vo : boardList) {
			System.out.printf("%3d. 제목: %s  작성자:%s  작성일:%s\n 내용:%s\n\n", vo.getSeq(), vo.getTitle(), vo.getWriter(),
					vo.getRegDate(), vo.getContent());
		}

		container.close();
	}

}
