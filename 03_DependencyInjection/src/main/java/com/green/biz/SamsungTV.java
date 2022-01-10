package com.green.biz;

public class SamsungTV implements TV {

	private BoseSpeaker speaker;
	private int price; // ���� �Ķ���� ���� ����

	public SamsungTV() { // �⺻������ ����
	}

	public SamsungTV(BoseSpeaker speaker) { // �⺻������ ���� BOSE ����Ŀ ��ü�� ����ϱ� ���� ����
		this.speaker = speaker;
	}

	private SamsungTV(BoseSpeaker speaker, int price) { // ���� �Ķ���� ������ ����
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

}
