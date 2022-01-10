package com.green.biz.dto;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

// @Repository("userDao")

public class UserDAOSpring {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// SQL 명령어

	// 사용자 조회
	public static final String USER_GET = "SELECT * FROM users WHERE id=? AND password=?";

	public UserVO getUser(UserVO vo) {

		Object[] args = { vo.getId(), vo.getPassword() };

		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}

	class UserRowMapper implements RowMapper<UserVO> {

		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			UserVO user = new UserVO();

			user.setId(rs.getString("id"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setRole(rs.getString("role"));

			return user;

		}

	}

}
