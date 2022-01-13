package com.green.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.board.BoardService;
import com.green.biz.dao.BoardDAO;
import com.green.biz.dto.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;
	
	public BoardServiceImpl() { }
	
	@Override
	public void insertBoard(BoardVO vo) {
		/*
		if (vo.getSeq() == 0) {
			throw new IllegalArgumentException();
		}
		*/
		boardDao.insertBoard(vo); // 등록 성공
		//boardDao.insertBoard(vo); // 예외 발생
	}

	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDao.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDao.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDao.getBoardList(vo);
	}

}
