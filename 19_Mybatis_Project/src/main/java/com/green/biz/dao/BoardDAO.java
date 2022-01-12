package com.green.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.green.biz.dto.BoardVO;
import com.green.biz.util.SqlSessionFactoryBean;

public class BoardDAO {

	private SqlSession mybatis; // DB 연결객체

	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}

	// 삽입
	public void insertBoard(BoardVO vo) {
		// 첫번째 인자: board-mapping 네임스페이스와 id를 차례로 지정
		mybatis.insert("BoardDAO.insertBoard", vo); // SqlSession 객체에 저장되어 있는 SQL문을 BoardDAO 객체에서 파라미터 값을 받아 실행 한다
		mybatis.commit(); // 저장
	}

	// 수정
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}

	// 삭제
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}

	// 조회(하나의 데이터 검색)
	public BoardVO getBoard(BoardVO vo) {

		// 하나의 데이터 검색시 selectOne() 구문 사용
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	// 조회(여러개의 데이터 검색)
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		// 여려개의 데이터 리스트로 출력
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
}
