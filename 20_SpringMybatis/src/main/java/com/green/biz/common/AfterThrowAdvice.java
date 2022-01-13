package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowAdvice {
	
	@AfterThrowing(pointcut="PointcutCommon.allPointcut()", throwing="exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String method = jp.getSignature().getName();
		
		System.out.printf("[예외 처리] %s()\n메소드 수행중 예외 발생!\n"
				, method);
		
		if (exceptObj instanceof IllegalArgumentException) {
			System.out.println("부적합한 값이 입력되었습니다.");
		} else if (exceptObj instanceof NumberFormatException) {
			System.out.println("잘못된 숫자 형식의 값이 입력되었습니다.");
		} else if (exceptObj instanceof Exception) {
			System.out.println("프로그램 수행 중, 문제가 발생했습니다.");
		}
	}
}
