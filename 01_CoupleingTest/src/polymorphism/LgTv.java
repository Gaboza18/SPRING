package polymorphism;

public class LgTv implements TV { // LG TV�� TV Ŭ���� ���

	@Override
	public void powerOn() { // TV Ŭ������ ������ Ű�°��� ���
		System.out.println("���� TV -- ������ �Ҵ�");
	}

	@Override
	public void powerOff() { // TV Ŭ������ ������ ���°��� ���
		System.out.println("���� TV -- ������ ����");
	}

	@Override
	public void volumeUp() { // TV Ŭ������ ���� �ø��°��� ���
		System.out.println("���� TV -- ������ �ø���");
	}

	@Override
	public void volumeDown() { // TV Ŭ������ ���� �����°��� ���
		System.out.println("���� TV -- ������ ������");
	}

}
