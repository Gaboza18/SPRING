package com.green.biz.common;

public class AfterReturningAdvice {
	public void afterLog() {
		System.out.println("[사후처리] 비즈니스 로직 수행 후 작업...");
		System.out.println();
	}
}
