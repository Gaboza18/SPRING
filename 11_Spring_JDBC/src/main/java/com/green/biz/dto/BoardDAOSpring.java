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

	/* SQL ��ɾ� */

	// �Է�

	private static final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content)"
			+ "VALUES(board_seq.NEXTVAL,?,?,?)";

	// Ʈ������ ó�� �׽�Ʈ��(�Է�)
	/*
	 * private static final String BOARD_INSERT =
	 * "INSERT INTO board(seq, title, writer, content)" + "VALUES(?,?,?,?)";
	 */

	// ����
	private static final String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";

	// ����
	private static final String BOARD_DELETE = "DELETE board WHERE seq=?";

	// ��ȸ
	private static final String BOARD_GET = "SELECT * FROM board WHERE seq=?";

	// ��ü ��ȸ
	private static final String BOARD_LIST = "SELECT * FROM board";

	/* �Խù� CRUD �޼ҵ� */

	public void insertBoard(BoardVO board) { // �Խù� �Է�
		
		jdbcTemplate.update(BOARD_INSERT, board.getTitle(), board.getWriter(), board.getContent());
		
		// Ʈ������ ó�� �׽�Ʈ��
		// jdbcTemplate.update(BOARD_INSERT, board.getSeq(), board.getTitle(), board.getWriter(), board.getContent());
	}

	public void updateBoard(BoardVO board) { // �Խù� ����
		jdbcTemplate.update(BOARD_UPDATE, board.getTitle(), board.getWriter(), board.getSeq());
	}

	public void deleteBoard(BoardVO board) { // �Խù� ����
		jdbcTemplate.update(BOARD_DELETE, board.getSeq());
	}

	public BoardVO getBoard(BoardVO vo) { // �Խù� ��ȸ(1��)

		Object[] args = { vo.getSeq() };

		// BoardRowMapper(): ��ȸ����� ��� ��ü
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	public List<BoardVO> getBoardList() { // �Խù� ��ü ��ȸ

		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

	/*
	 * �Խñ��� �����ϴ� BoardVO ��ü�� SQL ��ȸ����� �����Ͽ� ��ȯ
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
