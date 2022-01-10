package com.green.biz;

import org.springframework.stereotype.Component;

@Component("jbl")

public class JBLSpeaker implements Speaker {

	public JBLSpeaker() {
		System.out.println("===> JBL Speaker ��ü ����");
	}

	@Override
	public void volumeUp() {

		System.out.println("JBL Speaker -- �Ҹ��� �ø���.");
	}

	@Override
	public void volumeDown() {

		System.out.println("JBL Speaker -- �Ҹ��� ������.");
	}

}
