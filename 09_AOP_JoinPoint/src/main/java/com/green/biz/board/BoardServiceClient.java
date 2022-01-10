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

		board.setTitle("콩박이 인형 재출시");
		board.setWriter("콩박이");
		board.setContent("콩박이 인형 많이 사주세요");

		boardService.insertBoard(board);

		BoardVO vol = new BoardVO();
		vol.setSeq(1); // 시퀀스 번호: 1 / 관리자
		board = boardService.getBoard(vol);

		List<BoardVO> boardList = boardService.getBoardList();
		
		System.out.println();
		System.out.println("--------------------- 게시판 내용 ---------------------");

		for (BoardVO vo : boardList) {
			System.out.printf("%3d 제목: %s  작성자:%s  작성일:%s\n 내용:%s\n\n", vo.getSeq(), vo.getTitle(), vo.getWriter(),
					vo.getRegDate(), vo.getContent());
		}

		container.close();
	}

}
