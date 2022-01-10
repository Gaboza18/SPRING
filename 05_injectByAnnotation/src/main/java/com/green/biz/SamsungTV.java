package com.green.biz;

import javax.annotation.Resource;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // 의존성 주입 설정

public class SamsungTV implements TV {
	

	// @Autowired // 지정된 타입의 객체를 찾아서 자동으로 의존성 주입 일어남
	// @Qualifier("bose") // 의존성 주입할 bean의 id를 지정해 준다(의존성 주입 대상인 Speaker 타입의 객체가 두개 이상일때 문제발생)
	
	@Resource(name="jbl") // 객체의 이름을 이용하여 의존성 주입처리
	
	private Speaker speaker;
	private int price; // 다중 파라메터 매핑 예제

	public SamsungTV() { // 기본생성자 생성
	}

	public SamsungTV(Speaker speaker) { // 기본생성자 에서 BOSE 스피커 객체를 사용하기 위한 선언
		this.speaker = speaker;
	}

	private SamsungTV(Speaker speaker, int price) { // 다중 파라메터 생성자 선언
		this.speaker = speaker;
		this.price = price;
	}

	public void initMethod() {
		System.out.println("객체 초기화 작업 처리...");
	}

	public void destoryMethod() {
		System.out.println("객체 삭제 전 처리할 로직 처리...");
	}

	@Override
	public void powerOn() {
		System.out.println("삼성 TV -- 전원을 켠다");
		System.out.println("가격: " + price);
	}

	@Override
	public void powerOff() {
		System.out.println("삼성 TV -- 전원을 끈다");
	}

	@Override
	public void volumeUp() {
		// speaker = new BoseSpeaker(); // 스프링에서 생성해서 제공
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// speaker = new BoseSpeaker(); // 스프링에서 생성해서 제공
		speaker.volumeDown();
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
