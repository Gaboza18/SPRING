package com.green.view.controller;

public class ViewResolver {

	private String prefix; // view �̸� �տ� �������� '���'�� ����
	private String suffix; // view Ȯ���ڸ� ����

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/*
	 * ������ View�� ��ȯ�ϴ� �޼ҵ�
	 */

	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}

}
