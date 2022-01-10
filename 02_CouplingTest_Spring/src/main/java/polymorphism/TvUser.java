package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {

		// ������ �����̳ʸ� ����
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");

		// ������ �����̳ʿ��� �ʿ��� ��ü�� ��û
		TV tv = (TV) factory.getBean("tv");
		TV tv2 = (TV) factory.getBean("tv");
		TV tv3 = (TV) factory.getBean("tv");

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		factory.close();

	}

}
