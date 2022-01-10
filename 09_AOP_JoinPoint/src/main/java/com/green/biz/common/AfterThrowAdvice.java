package com.green.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowAdvice {

	public void exceptionLog(JoinPoint jp, Exception exceptObj) {

		String method = jp.getSignature().getName();

		System.out.printf("[예외처리] %s()\n 메소드 수행중 예외 발생 %s\n", method, exceptObj.getMessage());
		
	}
}
