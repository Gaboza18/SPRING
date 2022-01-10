package com.green.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	private static BoardService boardService;

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// IOC 컨테이너 에서 boardService 객체를 제공 받음
		BoardService boardService = (BoardService) container.getBean("boardService");

		// 게시글 등록
		BoardVO boardInsert = new BoardVO();

		boardInsert.setTitle("똥순이 인형 재출시");
		boardInsert.setWriter("똥순이");
		boardInsert.setContent("똥순이 인형 많이 사주세요");

		boardService.insertBoard(boardInsert);

		// 게시글 삭제
		BoardVO boardDelete = new BoardVO();

		boardDelete.setSeq(163);

		boardService.deleteBoard(boardDelete);

		// CRUD 콘솔창 출력

		List<BoardVO> boardList = boardService.getBoardList();

		for (BoardVO vo : boardList) {
			System.out.printf("%3d 제목: %s  작성자:%s  작성일:%s\n 내용:%s\n\n", vo.getSeq(), vo.getTitle(), vo.getWriter(),
					vo.getRegDate(), vo.getContent());
		}

		container.close();
	}

}
