package com.green.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.green.biz.dto.BoardVO;
import com.green.biz.util.SqlSessionFactoryBean;

public class BoardDAO {

	private SqlSession mybatis; // DB ���ᰴü

	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}

	// ����
	public void insertBoard(BoardVO vo) {
		// ù��° ����: board-mapping ���ӽ����̽��� id�� ���ʷ� ����
		mybatis.insert("BoardDAO.insertBoard", vo); // SqlSession ��ü�� ����Ǿ� �ִ� SQL���� BoardDAO ��ü���� �Ķ���� ���� �޾� ���� �Ѵ�
		mybatis.commit(); // ����
	}

	// ����
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}

	// ����
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}

	// ��ȸ(�ϳ��� ������ �˻�)
	public BoardVO getBoard(BoardVO vo) {

		// �ϳ��� ������ �˻��� selectOne() ���� ���
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	// ��ȸ(�������� ������ �˻�)
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		// �������� ������ ����Ʈ�� ���
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
}
