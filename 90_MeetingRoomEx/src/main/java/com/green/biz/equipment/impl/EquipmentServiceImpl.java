package com.green.biz.equipment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.EquipmentDAO;
import com.green.biz.equipment.EquipmentService;
import com.green.biz.vo.EquipmentVO;

@Service("EquipmentService")
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDAO equipmentDao;
	
	@Override
	public void insertEquipment(EquipmentVO vo) {
		equipmentDao.insertEquipment(vo);

	}

	@Override
	public void updateEquipment(EquipmentVO vo) {
		equipmentDao.updateEquipment(vo);

	}

	@Override
	public void deleteEquipment(EquipmentVO vo) {
		equipmentDao.deleteEquipment(vo);

	}

	@Override
	public List<EquipmentVO> getEquipmentByRoomid(String roomId) {

		return equipmentDao.getEquipmentByRoomid(roomId);
	}
}
