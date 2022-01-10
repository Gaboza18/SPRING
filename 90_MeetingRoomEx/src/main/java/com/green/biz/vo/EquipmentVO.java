package com.green.biz.vo;


/*
 * DTO = VO = Data Transfer Object = 계층 간 데이터 교환하기 위한 자바 빈즈, DB 레고드의 데이터를 매핑하기 위한 데이터 객체
 */
public class EquipmentVO {
	
	private String equipment_id;
	private String room_id;
	private String equipment_name;
	private int equipment_count;
	private String equipment_remarks;

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public int getEquipment_count() {
		return equipment_count;
	}

	public void setEquipment_count(int equipment_count) {
		this.equipment_count = equipment_count;
	}

	public String getEquipment_remarks() {
		return equipment_remarks;
	}

	public void setEquipment_remarks(String equipment_remarks) {
		this.equipment_remarks = equipment_remarks;
	}

	@Override
	public String toString() {
		return "EquipmentVO [equipment_id=" + equipment_id + ", room_id=" + room_id + ", equipment_name="
				+ equipment_name + ", equipment_count=" + equipment_count + ", equipment_remarks=" + equipment_remarks
				+ "]";
	}

}
