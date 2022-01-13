package com.green.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.green.biz..*Impl.get*(..))")
	public void getPointcut() { }
	
	@Pointcut("execution(* com.green.biz..*Impl.*(..))")
	public void allPointcut() { }
}
