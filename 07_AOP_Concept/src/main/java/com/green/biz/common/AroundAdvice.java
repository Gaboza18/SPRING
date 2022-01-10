package com.green.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("[BEFORE] ����Ͻ� �޼ҵ� ���� �� ó�� ����...");

		Object returnObj = pjp.proceed(); // ����Ͻ� �޼ҵ� ȣ���ϰ� ����

		System.out.println("[AFTER] ����Ͻ� �޼ҵ� ���� �� ó�� ����...");
		
		return returnObj;
	}
}
