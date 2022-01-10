package com.green.biz;

public class SamsungTV implements TV {

	private BoseSpeaker speaker;
	private int price; // 다중 파라메터 매핑 예제

	public SamsungTV() { // 기본생성자 생성
	}

	public SamsungTV(BoseSpeaker speaker) { // 기본생성자 에서 BOSE 스피커 객체를 사용하기 위한 선언
		this.speaker = speaker;
	}

	private SamsungTV(BoseSpeaker speaker, int price) { // 다중 파라메터 생성자 선언
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

}
