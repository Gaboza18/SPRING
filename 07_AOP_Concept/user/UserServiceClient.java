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

		if (user != null) { // ������ ������ id,password�� ����ڰ� �����ϸ�
			System.out.println("�α��� ����");
			System.out.println(user);
		} else { // ������ ������
			System.out.println("����� ���̵�, ��й�ȣ Ȯ�����ּ���");
		}

		container.close();
	}

}
