package polymorphism;

public class TvUser {

	public static void main(String[] args) {

		/*
		 *  TV tv = new SamsungTv(); // TV Ŭ���� �� ������ �ϱ����� �ＺƼ���� ��ü ����
		 *  SamsungTV ��� LgTv ��ü�� ����� ���
		 *  �����ڸ� �ٲ��ش� -> ���� �ٲ� �ʿ����(���յ��� ���������� �����ڸ� �ٲ�� �ϴ� ���ŷο��� �߻�) 
		 */
		
		TV tv = new LgTv();

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
