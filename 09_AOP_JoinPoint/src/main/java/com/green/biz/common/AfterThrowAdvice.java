package com.green.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowAdvice {

	public void exceptionLog(JoinPoint jp, Exception exceptObj) {

		String method = jp.getSignature().getName();

		System.out.printf("[����ó��] %s()\n �޼ҵ� ������ ���� �߻� %s\n", method, exceptObj.getMessage());
		
	}
}
