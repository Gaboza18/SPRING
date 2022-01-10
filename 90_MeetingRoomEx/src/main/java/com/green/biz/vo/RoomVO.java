package com.green.biz.vo;

import java.util.*;

public class RoomVO {

	private String room_id;
	private String room_name;
	private int capacity;
	List<EquipmentVO> equipmentList;

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<EquipmentVO> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentVO> equipmentList) {
		this.equipmentList = equipmentList;
	}

	@Override
	public String toString() {
		return "RoomVO [room_id=" + room_id + ", room_name=" + room_name + ", capacity=" + capacity + ", equipmentList="
				+ equipmentList + "]";
	}

}
