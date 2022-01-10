package com.green.ex1;

import org.springframework.stereotype.Component;

@Component("opBean")
public class Operation {

	public void message() {
		System.out.println("message() 메소드 호출됨\n");
	}

	public int m() {
		System.out.println("m() 메소드 호출됨\n");
		return 2;
	}

	public int k() {
		System.out.println("k() 메소드 호출\n");
		return 3;
	}

	public void throwException() {
		throw new RuntimeException("예외 발생...");
	}
}
