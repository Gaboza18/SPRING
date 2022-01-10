package com.green.biz;

import javax.annotation.Resource;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // ������ ���� ����

public class SamsungTV implements TV {
	

	// @Autowired // ������ Ÿ���� ��ü�� ã�Ƽ� �ڵ����� ������ ���� �Ͼ
	// @Qualifier("bose") // ������ ������ bean�� id�� ������ �ش�(������ ���� ����� Speaker Ÿ���� ��ü�� �ΰ� �̻��϶� �����߻�)
	
	@Resource(name="jbl") // ��ü�� �̸��� �̿��Ͽ� ������ ����ó��
	
	private Speaker speaker;
	private int price; // ���� �Ķ���� ���� ����

	public SamsungTV() { // �⺻������ ����
	}

	public SamsungTV(Speaker speaker) { // �⺻������ ���� BOSE ����Ŀ ��ü�� ����ϱ� ���� ����
		this.speaker = speaker;
	}

	private SamsungTV(Speaker speaker, int price) { // ���� �Ķ���� ������ ����
		this.speaker = speaker;
		this.price = price;
	}

	public void initMethod() {
		System.out.println("��ü �ʱ�ȭ �۾� ó��...");
	}

	public void destoryMethod() {
		System.out.println("��ü ���� �� ó���� ���� ó��...");
	}

	@Override
	public void powerOn() {
		System.out.println("�Ｚ TV -- ������ �Ҵ�");
		System.out.println("����: " + price);
	}

	@Override
	public void powerOff() {
		System.out.println("�Ｚ TV -- ������ ����");
	}

	@Override
	public void volumeUp() {
		// speaker = new BoseSpeaker(); // ���������� �����ؼ� ����
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// speaker = new BoseSpeaker(); // ���������� �����ؼ� ����
		speaker.volumeDown();
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
