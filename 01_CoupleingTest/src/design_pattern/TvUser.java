package design_pattern;

public class TvUser {

	public static void main(String[] args) {

		BeanFactory factory = new BeanFactory();

		// 원하는 객체의 이름을 command parameter에서 입력받는다
		// run_as -> argument 실행

		TV tv = (TV) factory.getBean(args[0]);

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
