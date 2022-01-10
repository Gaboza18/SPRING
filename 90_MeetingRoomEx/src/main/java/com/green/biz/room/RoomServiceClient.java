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
		
		// 1. 새로운 회의실 추가 
		/*
		RoomVO vo = new RoomVO();
			
		vo.setRoom_id("B00331");
		vo.setRoom_name("테스트2");
		vo.setCapacity(25);
		RoomService.insertRoom(vo);
		*/
		
		// 위의 회의실에 새로운 장비 추가
		EquipmentVO eVo = new EquipmentVO();
		
		eVo.setEquipment_id("50-3");
		eVo.setEquipment_name("회의용 테이블");
		eVo.setEquipment_count(1);
		eVo.setEquipment_remarks("20인용 회의 테이블");
		eVo.setRoom_id("B003");
		
		eqService.insertEquipment(eVo);
		
		eVo.setEquipment_id("50-4");
		eVo.setEquipment_name("회의용 의자");
		eVo.setEquipment_count(20);
		eVo.setEquipment_remarks("테이블 의자");
		eVo.setRoom_id("B003");
		
		eqService.insertEquipment(eVo);
		
	
		// 2. 회의실 번호로 회의실 이름 조회
		
		String roomId = "B003";
		System.out.printf("%s : %s\n", roomId, RoomService.getRoomNameById(roomId));
		
		/*
		// 3. 입력한 회의실 이름 변경

		vo.setRoom_name("소회의실");
		RoomService.updateRoom(vo);
		*/
	
		// 4. 회의실 번호로 회의실 정보 조회

		roomId = "B003";
		RoomVO room = RoomService.getRoomById(roomId);
		System.out.println("회의실 ID: " + roomId);
		System.out.println(room);
		
		// 시설목록 출력
		for(EquipmentVO item : room.getEquipmentList()) {
			System.out.println(item);
		}
		System.out.println();
		

		// 5. 회의실 번호로 회의실 정보 삭제
		
		/*
		roomId = "B0033";
		RoomService.deleteRoom(roomId);
		System.out.printf("입력하신 회의실 번호: %s 가 삭제 되었습니다.",roomId);
		*/
		container.close();
		

	}

}
