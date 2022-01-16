package com.green.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * 포인트 컷 선언
 */

@Aspect
public class PointcutCommon {
	
	@Pointcut("execution(* com.green.biz..*Impl.get*(..))") // ~biz로 시작하는 모든 패키지 참조 ~impl로 끝나는 get방식 모든 메소드
	public void getPointcut() { 		
	}
	
	@Pointcut("execution(* com.green.biz..*Impl.*(..))")
	public void allPointcut() {
	}
	
	
}
