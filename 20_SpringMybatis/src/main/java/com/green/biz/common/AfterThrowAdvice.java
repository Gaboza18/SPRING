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
		
		System.out.printf("[���� ó��] %s()\n�޼ҵ� ������ ���� �߻�!\n"
				, method);
		
		if (exceptObj instanceof IllegalArgumentException) {
			System.out.println("�������� ���� �ԷµǾ����ϴ�.");
		} else if (exceptObj instanceof NumberFormatException) {
			System.out.println("�߸��� ���� ������ ���� �ԷµǾ����ϴ�.");
		} else if (exceptObj instanceof Exception) {
			System.out.println("���α׷� ���� ��, ������ �߻��߽��ϴ�.");
		}
	}
}
