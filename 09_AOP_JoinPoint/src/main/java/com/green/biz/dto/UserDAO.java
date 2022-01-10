package com.green.biz.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;

@Repository("userDAO")

public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// SQL 명령어

	// 사용자 조회
	public static final String USER_GET = "SELECT * FROM users WHERE id=? AND password=?";

	public UserVO getUser(UserVO vo) {
		
		UserVO user = null;

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {

				user = new UserVO();

				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return user;
	}

}
