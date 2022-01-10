package com.green.biz.room;

import java.util.List;

import com.green.biz.vo.RoomVO;

public interface RoomService {

	public int getMaxCapacity();

	public String getRoomNameById(String roomId);

	public RoomVO getRoomById(String roomId);

	public List<RoomVO> getAllRoom();

	public void insertRoom(RoomVO vo);

	public void updateRoom(RoomVO vo);

	public void deleteRoom(String roomId);
	
}
