package polymorphism;

public class SamsungTV implements TV {

	public void initMethod() {
		System.out.println("객체 초기화 작업 처리...");
	}

	public void destoryMethod() {
		System.out.println("객체 삭제 전 처리할 로직 처리...");
	}

	@Override
	public void powerOn() {
		System.out.println("삼성 TV -- 전원을 켠다");
	}

	@Override
	public void powerOff() {
		System.out.println("삼성 TV -- 전원을 끈다");
	}

	@Override
	public void volumeUp() {
		System.out.println("삼성 TV -- 볼륨을 올린다");
	}

	@Override
	public void volumeDown() {
		System.out.println("삼성 TV -- 볼륨을 내린다");
	}

}
