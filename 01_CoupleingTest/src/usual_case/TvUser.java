package usual_case;

public class TvUser {

	public static void main(String[] args) {
		
		/*
		 * 결합도가 높은 자바 프로그램(유지보수가 힘들다)
		 */
		
		/*
		SamsungTV stv = new SamsungTV(); // 삼성 TV 객체 생성
		
		stv.powerOn(); 
		stv.volumeUp();
		stv.volumeDown();
		stv.powerOff();
		*/
		
		/*
		 * TV 객체를 삼성 -> 엘지 바꿀 경우
		 * 객체 생성과 메소드 호출을 모두 프로그래머가 변경해야 한다
		 */
		
		LgTV ltv = new LgTV();
		
		ltv.turnOff();
		ltv.turnOn();
		ltv.soundUp();
		ltv.soundDown();
	}

}
