package com.green.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  角力 诀公贸府 窍绰 何盒
 */
public interface Controller {
	
	String handleRequest(HttpServletRequest request,HttpServletResponse response);
}
