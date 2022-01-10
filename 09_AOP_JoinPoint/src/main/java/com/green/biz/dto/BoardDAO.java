package com.green.biz.dto;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;


@Repository("BoardDao")
public class BoardDAO {

	Connection conn = null; // DB에 연결하기 위한 통로
	PreparedStatement pstmt = null; // SQL 문장을 사용하기 위한 선언
	ResultSet rs = null; // 결과값을 리턴하기 위한 선언

	// SQL 명령어

	// 입력
	private static final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content)"
			+ "VALUES(board_seq.NEXTVAL,?,?,?)";
	// 수정
	private static final String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";

	// 삭제
	private static final String BOARD_DELETE = "DELETE board WHERE seq=?";

	// 조회(시퀀스 값에 따라 조회한다)
	private static final String BOARD_GET = "SELECT * FROM board WHERE seq=?";

	// 전체 조회
	private static final String BOARD_LIST = "SELECT * FROM board";

	public BoardDAO() { // 기본생성자
	}
	
	/*
	 * 게시판 입력하는 메소드
	 */
	public void insertBoard(BoardVO board) {

		System.out.println("===> JDBC로 insertBoard() 처리");

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	public void updateBoard(BoardVO board) {

		System.out.println("===> JDBC로 updateBoard() 처리");

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getSeq());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	public void deleteBoard(BoardVO board) {

		System.out.println("===> JDBC로 deleteBoard() 처리");

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);

			pstmt.setInt(1, board.getSeq());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	public BoardVO getBoard(BoardVO vo) {

		System.out.println("===> JDBC로 getBoard() 처리");
		BoardVO board = null;

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			
			pstmt.setInt(1, vo.getSeq());

			rs = pstmt.executeQuery();

			if (rs.next()) {

				board = new BoardVO();

				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return board;
	}

	public List<BoardVO> getBoardList() {

		List<BoardVO> boardList = null;
		System.out.println("===> JDBC로 getBoardList() 처리");
		
		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);

			rs = pstmt.executeQuery();

			boardList = new ArrayList<>();

			while (rs.next()) {

				BoardVO board = new BoardVO();

				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));

				boardList.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return boardList;
	}
}
