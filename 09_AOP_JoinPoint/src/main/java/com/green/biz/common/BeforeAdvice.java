package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class BeforeAdvice {

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
