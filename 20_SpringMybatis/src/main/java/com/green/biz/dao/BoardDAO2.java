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
 * SqlSessionTemplate�� �̿��� DAO ���� 
 */

@Repository("boardDAO")
public class BoardDAO2 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO board) {
		System.out.println("===> Mybatis�� insertBoard() ó��");
		
		mybatis.insert("BoardDAO.insertBoard", board);
	}
	
	public void updateBoard(BoardVO board) {
		System.out.println("===> Mybatis�� updateBoard() ��� ó��");
		
		mybatis.update("BoardDAO.updateBoard", board);
	}
	
	public void deleteBoard(BoardVO board) {
		System.out.println("===> Mybatis�� deleteBoard() ��� ó��");
		
		mybatis.delete("BoardDAO.deleteBoard", board);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� getBoard() ó��");
		
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> Mybatis�� getBoardList() ó��");
		
		return mybatis.selectList("BoardDAO.getBoardList_D", vo);
	}
}








