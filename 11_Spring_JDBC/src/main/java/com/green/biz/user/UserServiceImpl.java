package com.green.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

@Service("userService")

public class UserServiceImpl implements UserService {

	@Autowired // UserDAO ��ü�� ���� ������ ����(������ ������ ���� �ҷ��´�)
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
