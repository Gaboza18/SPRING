package polymorphism;

public class LgTv implements TV {

	@Override
	public void powerOn() {
		System.out.println("���� TV -- ������ �Ҵ�");
	}

	@Override
	public void powerOff() {
		System.out.println("���� TV -- ������ ����");
	}

	@Override
	public void volumeUp() {
		System.out.println("���� TV -- ������ �ø���");
	}

	@Override
	public void volumeDown() {
		System.out.println("���� TV -- ������ ������");
	}

}
