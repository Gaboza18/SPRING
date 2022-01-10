package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.green.biz.dto.UserVO;

@Service
@Aspect

public class AfterReturningAdvice {
	// @Pointcut("execution(* com.green.biz..*Impl.get*(..))")
	
	// public void getPointcut() { } 
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {

		String method = jp.getSignature().getName();

		if (returnObj instanceof UserVO) { // returnOb�� UserVO ��ȯ�� �����ϸ�
			UserVO user = (UserVO) returnObj;

			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + " �α���(������)"); // ����� �̸�
			} else {
				System.out.println(user.getName() + " �α���");
			}
		}
		System.out.printf("[����ó��] %s() ���ϰ�: %s\n", method, returnObj.toString());
	}
}
