package com.green.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {

		String method = pjp.getSignature().getName();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start(); // 스톱워치 시작

		// 비즈니스 메소드 호출
		Object obj = pjp.proceed();
		stopWatch.stop(); // 스톱워치 정지

		System.out.printf("메소드명: %s\n 수행에 걸린 시간: %d (ms)\n", method, stopWatch.getTotalTimeMillis());
		System.out.println();
		
		return obj;

	}
}
