package com.green.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {

		// TV tv = new SamsungTV(); --> ������ �����̳� ����

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// tv ��ü�� ���� ��û

		TV tv = (TV) container.getBean("tv");

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		container.close();

	}

}
