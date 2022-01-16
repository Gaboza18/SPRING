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
	 * �θ� Ŭ������ ���� ���� �޼ҵ带 ȣ���Ͽ� �����ͺ��̽��� ������ ������ ���� 
	 */
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardVO board) {
		System.out.println("===> Mybatis�� insertBoard() ó��");
		
		getSqlSession().insert("BoardDAO.insertBoard", board);
	}
	
	public void updateBoard(BoardVO board) {
		System.out.println("===> Mybatis�� updateBoard() ��� ó��");
		
		getSqlSession().update("BoardDAO.updateBoard", board);
	}
	
	public void deleteBoard(BoardVO board) {
		System.out.println("===> Mybatis�� deleteBoard() ��� ó��");
		
		getSqlSession().delete("BoardDAO.deleteBoard", board);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� getBoard() ó��");
		
		return getSqlSession().selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> Mybatis�� getBoardList() ó��");
		
		return getSqlSession().selectList("BoardDAO.getBoardList", vo);
	}
}








