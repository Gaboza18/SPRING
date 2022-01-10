package com.green.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

@Service("userService")

public class UserServiceImpl implements UserService {

	@Autowired // UserDAO 객체를 만들어서 의존성 주입(스프링 프레임 에서 불러온다)
	private UserDAO userDAO;

	public UserDAO getUserDao() {
		return userDAO;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDAO = userDao;
	}

	@Override
	public UserVO getUser(UserVO vo) {
		// TODO Auto-generated method stub
		return userDAO.getUser(vo);
	}

}
