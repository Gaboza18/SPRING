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
		
		System.out.printf("[사후 처리] %s() 기능처리 완료...\n", method);
	}
}
