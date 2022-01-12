package com.green.biz.board;

import java.util.List;

import com.green.biz.dao.BoardDAO;
import com.green.biz.dto.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		
		BoardVO vo = new BoardVO(); // BoardVO 객체 생성
		BoardDAO bDao = new BoardDAO(); // BoardDAO 객체 생성
		

		vo.setTitle("Mybatis 프레임워크"); // vo 객체의 '제목값'을 설정
		vo.setWriter("홍길동");
		vo.setContent("Mybatis를 이용한 스프링MVC 프로그램 구현");
		
		bDao.insertBoard(vo); // vo를 매개변수로 받아 insert문 실행
		
		// 게시글 조회
		vo.setSearchCondition("TITLE"); // 제목을 조건으로
		vo.setSearchKeyword(""); // 전체조회
		
		List<BoardVO> boardList = bDao.getBoardList(vo); // 게시물 입력값 boardList 배열에 저장
		
		for(BoardVO board : boardList) { // boardList 배열에서 하나씩 빼서 board 라는 변수에 담아서 출력한다
			System.out.println(board);
		}
	}

}
