package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		Signature sig = jp.getSignature();	// 업무처리 메소드의 선언부 정보를 얻어온다.
		String method = sig.getName();		// 메소드 이름을 얻어온다.
		Object[] args = jp.getArgs();		// 메소드 호출 시 인자 정보
		
		if (args.length == 0) {
			System.out.println("[사전 처리] 비즈니스 로직 수행 전 작업...");
			System.out.printf("SpringTemplate으로 %s() 기능 처리, args:없음\n", method);
		} else {
			System.out.println("[사전 처리] 비즈니스 로직 수행 전 작업...");
			System.out.printf("SpringTemplate으로 %s() 기능 처리, args:%s\n", method, args[0].toString());			
		}
	}
}
