package polymorphism;

public class TvUser {

	public static void main(String[] args) {

		/*
		 *  TV tv = new SamsungTv(); // TV 클래스 의 동작을 하기위한 삼성티비의 객체 생성
		 *  SamsungTV 대신 LgTv 객체를 사용할 경우
		 *  생성자만 바꿔준다 -> 변수 바꿀 필요없음(결합도는 낮아지지만 생성자를 바꿔야 하는 번거로움은 발생) 
		 */
		
		TV tv = new LgTv();

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
