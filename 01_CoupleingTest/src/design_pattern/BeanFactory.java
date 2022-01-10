package design_pattern;

/*
 *  TVUser Ŭ�������� ��û�� ��ü�� �����Ͽ� �����ϴ� Ŭ����
 */

// test

public class BeanFactory {

	// beanName: ��û�ϴ� ��ü��(samsung, lg ��)

	public Object getBean(String beanName) {
		
		if (beanName.equals("samsung")) {
			return new SamsungTV();
		} else if (beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
	
}
