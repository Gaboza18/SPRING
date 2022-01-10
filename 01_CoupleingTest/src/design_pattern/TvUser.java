package design_pattern;

public class TvUser {

	public static void main(String[] args) {

		BeanFactory factory = new BeanFactory();

		// ���ϴ� ��ü�� �̸��� command parameter���� �Է¹޴´�
		// run_as -> argument ����

		TV tv = (TV) factory.getBean(args[0]);

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
