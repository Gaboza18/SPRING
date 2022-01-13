package com.green.biz.dao;

import java.sql.*;
import org.springframework.stereotype.Repository;

import com.green.biz.common.JDBCUtil;
import com.green.biz.dto.UserVO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	// SQL ¸í·É¾î
	public final static String USER_GET =
			"SELECT * FROM users WHERE id=? AND password=?";
	
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
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return user;
	}
}











