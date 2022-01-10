package com.green.view.controller;

public class ViewResolver {

	private String prefix; // view 이름 앞에 붙혀지는 '경로'를 저장
	private String suffix; // view 확장자를 저장

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/*
	 * 응답할 View를 반환하는 메소드
	 */

	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}

}
