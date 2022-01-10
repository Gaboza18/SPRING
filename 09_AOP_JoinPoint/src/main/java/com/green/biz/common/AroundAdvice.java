package com.green.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {

		String method = pjp.getSignature().getName();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start(); // �����ġ ����

		// ����Ͻ� �޼ҵ� ȣ��
		Object obj = pjp.proceed();
		stopWatch.stop(); // �����ġ ����

		System.out.printf("�޼ҵ��: %s\n ���࿡ �ɸ� �ð�: %d (ms)\n", method, stopWatch.getTotalTimeMillis());
		System.out.println();
		
		return obj;

	}
}
