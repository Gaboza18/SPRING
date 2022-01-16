package com.green.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * ����Ʈ �� ����
 */

@Aspect
public class PointcutCommon {
	
	@Pointcut("execution(* com.green.biz..*Impl.get*(..))") // ~biz�� �����ϴ� ��� ��Ű�� ���� ~impl�� ������ get��� ��� �޼ҵ�
	public void getPointcut() { 		
	}
	
	@Pointcut("execution(* com.green.biz..*Impl.*(..))")
	public void allPointcut() {
	}
	
	
}
