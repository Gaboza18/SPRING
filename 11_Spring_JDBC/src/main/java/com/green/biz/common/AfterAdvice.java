package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect

public class AfterAdvice {

	@After("PointcutCommon.allPointcut()")
	public void finallyLog(JoinPoint jp) {

		String method = jp.getSignature().getName();
		System.out.printf("[����ó��] %s() ���ó�� ó���Ϸ�...\n\n", method);

	}
}
