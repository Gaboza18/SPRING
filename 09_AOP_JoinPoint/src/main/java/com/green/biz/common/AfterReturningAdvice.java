package com.green.biz.common;

import org.aspectj.lang.JoinPoint;

import com.green.biz.dto.UserVO;

public class AfterReturningAdvice {

	public void afterLog(JoinPoint jp, Object returnObj) {

		String method = jp.getSignature().getName();

		if (returnObj instanceof UserVO) { // returnObj�� UserVO ��ȯ�� �����ϸ�
			UserVO user = (UserVO) returnObj;

			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + "�α���(������)"); // ����� �̸�
			} else {
				System.out.println(user.getName() + "�α���");
			}
		}
		System.out.printf("[����ó��] %s() ���ϰ�: %s\n", method, returnObj.toString());
	}
}
