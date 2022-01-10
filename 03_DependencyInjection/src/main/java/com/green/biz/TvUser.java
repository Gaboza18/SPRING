package com.green.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {

		// TV tv = new SamsungTV(); --> 스프링 컨테이너 구동

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// tv 객체를 제공 요청

		TV tv = (TV) container.getBean("tv");

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		container.close();

	}

}
