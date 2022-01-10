package com.green.biz.room.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.RoomDAO;
import com.green.biz.room.RoomService;
import com.green.biz.vo.RoomVO;

@Service("RoomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomDao;

	@Override
	public int getMaxCapacity() {
		return roomDao.findMaxCapacity();
	}

	@Override
	public String getRoomNameById(String roomId) {
		return roomDao.findRoomNameById(roomId);
	}

	@Override
	public RoomVO getRoomById(String roomId) {
		return roomDao.getRoomById(roomId);
	}

	@Override
	public List<RoomVO> getAllRoom() {
		return roomDao.getAllRoom();
	}

	@Override
	public void insertRoom(RoomVO vo) {
		roomDao.insertRoom(vo);
	}

	@Override
	public void updateRoom(RoomVO vo) {
		roomDao.updateRoom(vo);

	}

	@Override
	public void deleteRoom(String roomId) {
		roomDao.deleteRoom(roomId);
	}
}
