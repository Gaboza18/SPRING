package com.green.biz.dao;

import java.util.*;
import java.sql.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;
import com.green.biz.dto.BoardVO;

// @Repository("boardDAO")
public class BoardDAO extends SqlSessionDaoSupport{

	/*
	 * 부모 클래스의 세션 생성 메소드를 호출하여 데이터베이스에 연결한 세션을 생성 
	 */
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardVO board) {
		System.out.println("===> Mybatis로 insertBoard() 처리");
		
		getSqlSession().insert("BoardDAO.insertBoard", board);
	}
	
	public void updateBoard(BoardVO board) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		
		getSqlSession().update("BoardDAO.updateBoard", board);
	}
	
	public void deleteBoard(BoardVO board) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		
		getSqlSession().delete("BoardDAO.deleteBoard", board);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoard() 처리");
		
		return getSqlSession().selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> Mybatis로 getBoardList() 처리");
		
		return getSqlSession().selectList("BoardDAO.getBoardList", vo);
	}
}








