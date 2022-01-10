package design_pattern;

/*
 *  TVUser 클래스에서 요청한 객체를 생성하여 제공하는 클래스
 */

// test

public class BeanFactory {

	// beanName: 요청하는 객체명(samsung, lg 등)

	public Object getBean(String beanName) {
		
		if (beanName.equals("samsung")) {
			return new SamsungTV();
		} else if (beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
	
}
