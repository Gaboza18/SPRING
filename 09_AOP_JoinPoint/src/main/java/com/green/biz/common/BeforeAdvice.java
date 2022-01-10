package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class BeforeAdvice {

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
