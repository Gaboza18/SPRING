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
	// public void allPointcut() { } // ����Ʈ �� ID ����
	
	@Before("PointcutCommon.allPointcut()") // ��Ű�����.�޼ҵ��̸�
	
	
	public void beforeLog(JoinPoint jp) {

		Signature sig = jp.getSignature(); // ����ó�� �޼ҵ��� ����� ������ ���´�.
		String method = sig.getName(); // �޼ҵ� �̸��� ���´�
		Object[] args = jp.getArgs(); // �޼ҵ� ȣ��� ���� ����

		if (args.length == 0) { // �Ű� ���� ������
			System.out.println("[����ó��] ����Ͻ� ���� ���� �� �۾�...");
			System.out.printf("�޼ҵ��: %s, args: ����\n", method);
		} else {
			System.out.println("[����ó��] ����Ͻ� ���� ���� �� �۾�...");
			System.out.printf("�޼ҵ��: %s, args: %s\n", method, args[0].toString());
		}
	}
}
