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

	// (1) ����Ͻ� ��⿡�� �������� �α�ó�� ���� (����ڰ� �ڵ带 �� ã�ư��� ������ �ʿ��ϴ�)
	// private LogAdvice log;

	// (2) �α� ������Ʈ ���� (����ڰ� �ڵ带 �� ã�ư��� ������ �ʿ��ϴ�)
	// private Log4jAdvice log;

	public BoardServiceImpl() { // �⺻������
		// �α� ��ü ���� - 1
		// log = new LogAdvice();

		// �α� ��ü ���� - 2
		// log = new Log4jAdvice();
	}

	@Override
	public void insertBoard(BoardVO vo) {
		// log.printLogging();

		// ������ ���ܸ� �߻���Ŵ
		// if (vo.getSeq() == 0) {
			// throw new IllegalArgumentException("0�� ���� ����Ҽ� �����ϴ�.");
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
