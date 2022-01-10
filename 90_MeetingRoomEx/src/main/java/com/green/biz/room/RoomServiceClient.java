package com.green.biz.room;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.green.biz.equipment.EquipmentService;
import com.green.biz.vo.EquipmentVO;
import com.green.biz.vo.RoomVO;

public class RoomServiceClient {

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		RoomService RoomService = (RoomService) container.getBean("RoomService");
		EquipmentService eqService = (EquipmentService) container.getBean("EquipmentService");
		
		// 1. ���ο� ȸ�ǽ� �߰� 
		/*
		RoomVO vo = new RoomVO();
			
		vo.setRoom_id("B00331");
		vo.setRoom_name("�׽�Ʈ2");
		vo.setCapacity(25);
		RoomService.insertRoom(vo);
		*/
		
		// ���� ȸ�ǽǿ� ���ο� ��� �߰�
		EquipmentVO eVo = new EquipmentVO();
		
		eVo.setEquipment_id("50-3");
		eVo.setEquipment_name("ȸ�ǿ� ���̺�");
		eVo.setEquipment_count(1);
		eVo.setEquipment_remarks("20�ο� ȸ�� ���̺�");
		eVo.setRoom_id("B003");
		
		eqService.insertEquipment(eVo);
		
		eVo.setEquipment_id("50-4");
		eVo.setEquipment_name("ȸ�ǿ� ����");
		eVo.setEquipment_count(20);
		eVo.setEquipment_remarks("���̺� ����");
		eVo.setRoom_id("B003");
		
		eqService.insertEquipment(eVo);
		
	
		// 2. ȸ�ǽ� ��ȣ�� ȸ�ǽ� �̸� ��ȸ
		
		String roomId = "B003";
		System.out.printf("%s : %s\n", roomId, RoomService.getRoomNameById(roomId));
		
		/*
		// 3. �Է��� ȸ�ǽ� �̸� ����

		vo.setRoom_name("��ȸ�ǽ�");
		RoomService.updateRoom(vo);
		*/
	
		// 4. ȸ�ǽ� ��ȣ�� ȸ�ǽ� ���� ��ȸ

		roomId = "B003";
		RoomVO room = RoomService.getRoomById(roomId);
		System.out.println("ȸ�ǽ� ID: " + roomId);
		System.out.println(room);
		
		// �ü���� ���
		for(EquipmentVO item : room.getEquipmentList()) {
			System.out.println(item);
		}
		System.out.println();
		

		// 5. ȸ�ǽ� ��ȣ�� ȸ�ǽ� ���� ����
		
		/*
		roomId = "B0033";
		RoomService.deleteRoom(roomId);
		System.out.printf("�Է��Ͻ� ȸ�ǽ� ��ȣ: %s �� ���� �Ǿ����ϴ�.",roomId);
		*/
		container.close();
		

	}

}
