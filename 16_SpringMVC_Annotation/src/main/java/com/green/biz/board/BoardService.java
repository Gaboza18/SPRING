package com.green.biz.board;

import java.util.*;

import com.green.biz.dto.BoardVO;

public interface BoardService {

	public void insertBoard(BoardVO vo);

	public void updateBoard(BoardVO vo);

	public void deleteBoard(BoardVO vo);

	public BoardVO getBoard(BoardVO vo);

	public List<BoardVO> getBoardList(BoardVO vo);
}
