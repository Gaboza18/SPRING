package com.green.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.green.view")
public class CommonExceptionHandler {

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", e); // �߻��� ���������� "exception" �Ӽ��� ����
		mav.setViewName("/common/arithmeticError.jsp");

		return mav;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView IllegalArgumentException(Exception e) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", e); // �߻��� ���������� "exception" �Ӽ��� ����
		mav.setViewName("/common/argumentError.jsp");

		return mav;
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView NullPointerException(Exception e) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", e); // �߻��� ���������� "exception" �Ӽ��� ����
		mav.setViewName("/common/nullPointerError.jsp");

		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", e); // �߻��� ���������� "exception" �Ӽ��� ����
		mav.setViewName("/common/error.jsp");

		return mav;
	}
}
