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
	
	// @Pointcut("execution(* com.green.biz..*Impl.*(..))")
	// public void allPointcut() { } // 포인트 컷 ID 지정
	
	@Before("PointcutCommon.allPointcut()") // 패키지경로.메소드이름
	
	
	public void beforeLog(JoinPoint jp) {

		Signature sig = jp.getSignature(); // 업무처리 메소드의 선언부 정보를 얻어온다.
		String method = sig.getName(); // 메소드 이름을 얻어온다
		Object[] args = jp.getArgs(); // 메소드 호출시 인자 정보

		if (args.length == 0) { // 매개 변수 없을때
			System.out.println("[사전처리] 비즈니스 로직 수행 전 작업...");
			System.out.printf("메소드명: %s, args: 없음\n", method);
		} else {
			System.out.println("[사전처리] 비즈니스 로직 수행 전 작업...");
			System.out.printf("메소드명: %s, args: %s\n", method, args[0].toString());
		}
	}
}
