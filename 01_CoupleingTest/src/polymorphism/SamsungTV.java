package polymorphism;

public class SamsungTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("�Ｚ TV -- ������ �Ҵ�");
	}

	@Override
	public void powerOff() {
		System.out.println("�Ｚ TV -- ������ ����");
	}

	@Override
	public void volumeUp() {
		System.out.println("�Ｚ TV -- ������ �ø���");
	}

	@Override
	public void volumeDown() {
		System.out.println("�Ｚ TV -- ������ ������");
	}

}
