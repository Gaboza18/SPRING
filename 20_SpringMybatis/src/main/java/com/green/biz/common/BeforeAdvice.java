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
		Signature sig = jp.getSignature();	// ����ó�� �޼ҵ��� ����� ������ ���´�.
		String method = sig.getName();		// �޼ҵ� �̸��� ���´�.
		Object[] args = jp.getArgs();		// �޼ҵ� ȣ�� �� ���� ����
		
		if (args.length == 0) {
			System.out.println("[���� ó��] ����Ͻ� ���� ���� �� �۾�...");
			System.out.printf("SpringTemplate���� %s() ��� ó��, args:����\n", method);
		} else {
			System.out.println("[���� ó��] ����Ͻ� ���� ���� �� �۾�...");
			System.out.printf("SpringTemplate���� %s() ��� ó��, args:%s\n", method, args[0].toString());			
		}
	}
}
