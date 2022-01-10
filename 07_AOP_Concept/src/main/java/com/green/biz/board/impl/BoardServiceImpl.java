package com.green.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.board.BoardService;
import com.green.biz.common.Log4jAdvice;
import com.green.biz.common.LogAdvice;
import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

@Service("boardService")

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	// (1) 비즈니스 모듈에서 수동으로 로깅처리 수행 (사용자가 코드를 다 찾아가며 수정이 필요하다)
	// private LogAdvice log;

	// (2) 로깅 컴포넌트 수정 (사용자가 코드를 다 찾아가며 수정이 필요하다)
	// private Log4jAdvice log;

	public BoardServiceImpl() { // 기본생성자
		// 로깅 객체 생성 - 1
		// log = new LogAdvice();

		// 로깅 객체 수정 - 2
		// log = new Log4jAdvice();
	}

	@Override
	public void insertBoard(BoardVO vo) {
		// log.printLogging();

		// 강제로 예외를 발생시킴
		// if (vo.getSeq() == 0) {
			// throw new IllegalArgumentException("0번 글을 등록할수 없습니다.");
		//}

		boardDao.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		// log.printLogging();
		boardDao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// log.printLogging();
		boardDao.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// log.printLogging();
		return boardDao.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		// log.printLogging();
		return boardDao.getBoardList();
	}

}
