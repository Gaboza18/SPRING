package com.green.biz.equipment;

import java.util.List;

import com.green.biz.vo.EquipmentVO;

public interface EquipmentService {

	public void insertEquipment(EquipmentVO vo);

	public void updateEquipment(EquipmentVO vo);

	public void deleteEquipment(EquipmentVO vo);

	public List<EquipmentVO> getEquipmentByRoomid(String roomId);

}
