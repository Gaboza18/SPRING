package polymorphism;

public class LgTv implements TV { // LG TV는 TV 클래스 상속

	@Override
	public void powerOn() { // TV 클래스의 전원을 키는것을 상속
		System.out.println("엘지 TV -- 전원을 켠다");
	}

	@Override
	public void powerOff() { // TV 클래스의 전원을 끄는것을 상속
		System.out.println("엘지 TV -- 전원을 끈다");
	}

	@Override
	public void volumeUp() { // TV 클래스의 볼륨 올리는것을 상속
		System.out.println("엘지 TV -- 볼륨을 올린다");
	}

	@Override
	public void volumeDown() { // TV 클래스의 볼륨 내리는것을 상속
		System.out.println("엘지 TV -- 볼륨을 내린다");
	}

}
