package com.green.biz;

import org.springframework.stereotype.Component;

@Component("bose")

public class BoseSpeaker implements Speaker{
	
	public BoseSpeaker() {
		System.out.println("===> Bose Speaker ��ü ����");
	}

	public void volumeUp() {
		System.out.println("BoseSpeaker -- �Ҹ��� �ø���.");
	}

	public void volumeDown() {
		System.out.println("BoseSpeaker -- �Ҹ��� ������.");
	}
	
}
