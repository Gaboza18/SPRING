package com.green.biz.dto;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;


@Repository("boardDao")
public class BoardDAO {

	Connection conn = null; 
	PreparedStatement pstmt = null; 
	ResultSet rs = null; 

	// SQL 명령문

	// 입력
	private static final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content)"
			+ "VALUES(board_seq.NEXTVAL,?,?,?)";
	// 수정
	private static final String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";

	// 삭제
	private static final String BOARD_DELETE = "DELETE board WHERE seq=?";

	// 게시물 하나만 조회
	private static final String BOARD_GET = "SELECT * FROM board WHERE seq=?";

	// 전체조회
	private static final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";

	public BoardDAO() { // 湲곕낯�깮�꽦�옄
	}
	
	/*
	 * 寃뚯떆�뙋 �엯�젰�븯�뒗 硫붿냼�뱶
	 */
	public void insertBoard(BoardVO board) {

		System.out.println("===> JDBC로 insertBoard() 실행");

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

		System.out.println("===> JDBC로 updateBoard() 실행");

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

		System.out.println("===> JDBC로 deleteBoard() 실행");

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

		System.out.println("===> JDBC로 getBoard() 실행");
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
		System.out.println("===> JDBC로 getBoardList() 실행");
		
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
