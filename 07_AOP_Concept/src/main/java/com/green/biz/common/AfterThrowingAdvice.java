package com.green.biz.common;

public class AfterThrowingAdvice {
	
	public void exceptionLog() {
		
		System.out.println("[예외처리] 비즈니스 로직 수행중 예외발생...");
		System.out.println();
	}
	
}
