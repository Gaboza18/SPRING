package com.green.ex1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation { // �����̽� ����

	public void myadvice(JoinPoint jp) {
		System.out.println("�ΰ� ��� ����!");
	}

	public void myadvice2(JoinPoint jp) {
		System.out.println("[����ó��] �ΰ� ��� ����!");
	}

	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("[����ó��] �޼ҵ� ���� �� ó������...");

		Object returnObj = pjp.proceed();

		System.out.println("[����ó��] �޼ҵ� ���� �� ó������...");
		
		return returnObj;
	}
}
