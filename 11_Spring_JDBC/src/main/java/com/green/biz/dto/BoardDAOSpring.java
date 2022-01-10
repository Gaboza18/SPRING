package com.green.biz.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOSpring {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* SQL 명령어 */

	// 입력

	private static final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content)"
			+ "VALUES(board_seq.NEXTVAL,?,?,?)";

	// 트랜젝션 처리 테스트용(입력)
	/*
	 * private static final String BOARD_INSERT =
	 * "INSERT INTO board(seq, title, writer, content)" + "VALUES(?,?,?,?)";
	 */

	// 수정
	private static final String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";

	// 삭제
	private static final String BOARD_DELETE = "DELETE board WHERE seq=?";

	// 조회
	private static final String BOARD_GET = "SELECT * FROM board WHERE seq=?";

	// 전체 조회
	private static final String BOARD_LIST = "SELECT * FROM board";

	/* 게시물 CRUD 메소드 */

	public void insertBoard(BoardVO board) { // 게시물 입력
		
		jdbcTemplate.update(BOARD_INSERT, board.getTitle(), board.getWriter(), board.getContent());
		
		// 트랜젝션 처리 테스트용
		// jdbcTemplate.update(BOARD_INSERT, board.getSeq(), board.getTitle(), board.getWriter(), board.getContent());
	}

	public void updateBoard(BoardVO board) { // 게시물 수정
		jdbcTemplate.update(BOARD_UPDATE, board.getTitle(), board.getWriter(), board.getSeq());
	}

	public void deleteBoard(BoardVO board) { // 게시물 삭제
		jdbcTemplate.update(BOARD_DELETE, board.getSeq());
	}

	public BoardVO getBoard(BoardVO vo) { // 게시물 조회(1건)

		Object[] args = { vo.getSeq() };

		// BoardRowMapper(): 조회결과를 담는 객체
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	public List<BoardVO> getBoardList() { // 게시물 전체 조회

		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

	/*
	 * 게시글을 저장하는 BoardVO 객체에 SQL 조회결과를 저장하여 반환
	 */
	class BoardRowMapper implements RowMapper<BoardVO> {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			BoardVO board = new BoardVO();

			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegDate(rs.getDate("regDate"));
			board.setCnt(rs.getInt("cnt"));

			return board;
		}
	}

}
