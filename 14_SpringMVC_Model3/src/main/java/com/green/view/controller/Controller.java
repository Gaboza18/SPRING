package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  ���� ����ó�� �ϴ� �κ�
 */
public interface Controller {
	
	String handleRequest(HttpServletRequest request,HttpServletResponse response);
}
