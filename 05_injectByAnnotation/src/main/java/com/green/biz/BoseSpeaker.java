package com.green.biz;

import org.springframework.stereotype.Component;

@Component("bose")

public class BoseSpeaker implements Speaker{
	
	public BoseSpeaker() {
		System.out.println("===> Bose Speaker 객체 생성");
	}

	public void volumeUp() {
		System.out.println("BoseSpeaker -- 소리를 올린다.");
	}

	public void volumeDown() {
		System.out.println("BoseSpeaker -- 소리를 내린다.");
	}
	
}
