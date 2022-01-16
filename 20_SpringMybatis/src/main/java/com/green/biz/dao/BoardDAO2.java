package com.green.biz.dao;

import java.util.*;
import java.sql.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;
import com.green.biz.dto.BoardVO;

/*
 * SqlSessionTemplate를 이용한 DAO 구현 
 */

@Repository("boardDAO")
public class BoardDAO2 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO board) {
		System.out.println("===> Mybatis로 insertBoard() 처리");
		
		mybatis.insert("BoardDAO.insertBoard", board);
	}
	
	public void updateBoard(BoardVO board) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		
		mybatis.update("BoardDAO.updateBoard", board);
	}
	
	public void deleteBoard(BoardVO board) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		
		mybatis.delete("BoardDAO.deleteBoard", board);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoard() 처리");
		
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> Mybatis로 getBoardList() 처리");
		
		return mybatis.selectList("BoardDAO.getBoardList_D", vo);
	}
}








