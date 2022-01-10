package com.green.ex1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation { // 어드바이스 동작

	public void myadvice(JoinPoint jp) {
		System.out.println("부가 기능 수행!");
	}

	public void myadvice2(JoinPoint jp) {
		System.out.println("[사후처리] 부가 기능 수행!");
	}

	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("[사전처리] 메소드 수행 전 처리내용...");

		Object returnObj = pjp.proceed();

		System.out.println("[사후처리] 메소드 수행 전 처리내용...");
		
		return returnObj;
	}
}
