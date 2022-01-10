package com.green.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		UserService userService = (UserService) container.getBean("userService");

		UserVO vo = new UserVO();

		vo.setId("test");
		vo.setPassword("test123");

		UserVO user = userService.getUser(vo);

		if (user != null) { // 위에서 저장한 id,password로 사용자가 존재하면
			System.out.println("로그인 성공");
			System.out.println(user);
		} else { // 유저가 없을때
			System.out.println("사용자 아이디, 비밀번호 확인해주세요");
		}

		container.close();
	}

}
