package com.green.biz;

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
