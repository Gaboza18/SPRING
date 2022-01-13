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
		
		// IOC 컨테이너에서 boardService 객체를 제공 받음.
		boardService = (BoardService)container.getBean("boardService");

		// 게시글 등록
		BoardVO board = new BoardVO();
		//board.setSeq(100);
		board.setTitle("임시 제목3");
		board.setWriter("유관순2");
		board.setContent("임시 내용입니다.2");
		
		boardService.insertBoard(board);
		
		//displayBoardList();
		BoardVO vo1 = new BoardVO();
		List<BoardVO> boardList = boardService.getBoardList(vo1);
		
		for (BoardVO vo : boardList) {
			System.out.printf("%3d 제목:%s  작성자:%s  작성일:%s\n%s\n\n",
							vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getRegDate(),
							vo.getContent());
		}
	}

}








