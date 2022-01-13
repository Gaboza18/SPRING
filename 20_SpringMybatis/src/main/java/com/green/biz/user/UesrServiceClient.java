package com.green.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.dto.UserVO;

public class UesrServiceClient {

	public static void main(String[] args) {
		
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService)container.getBean("userService");
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
		UserVO user = userService.getUser(vo);
		
		if (user != null) { // 위에서 지정한 id와 password로 사용자가 존재함.
			System.out.println("로그인에 성공하였습니다.");
			System.out.println(user);
		} else {
			System.out.println("사용자ID와 비밀번호를 확인해 주세요!");
		}
		
		container.close();
	}

}
