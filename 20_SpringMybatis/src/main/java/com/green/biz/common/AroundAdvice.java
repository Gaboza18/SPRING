package com.green.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {

	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		// ����Ͻ� �޼ҵ� ȣ��
		Object obj = pjp.proceed();
		stopWatch.stop();
		
		System.out.printf("�޼ҵ��: %s\n ���࿡ �ɸ� �ð�: %d (ms)\n", method, stopWatch.getTotalTimeMillis());
		
		return obj;
	}
}
