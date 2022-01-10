package com.green.biz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.green.biz.vo.EquipmentVO;
import com.green.biz.vo.RoomVO;

@Repository("RoomDAO")
public class RoomDAO {

	@Autowired // 컨테이너가 자동으로 빈을 주입한다
	private JdbcTemplate jdbcTemplate;
	@Autowired // 컨테이너가 자동으로 빈을 주입한다
	private EquipmentDAO eDao;

	/* SQL 명령어 */
	private static final String SELECT_MAX_CAPACITY = "SELECT MAX(capacity) FROM room"; // 조회(최대 수용인원)

	private static final String GET_ROOM_NAME = "SELECT room_name FROM room WHERE room_id=?"; // 조회(회의실 번호로 회의실 이름 조회)

	private static final String GET_ROOM_INFO = "SELECT * FROM room WHERE room_id=?"; // 조회(회의실 번호로 회의실 정보 조회)

	private static final String GET_ALL_ROOM = "SELECT * FROM room"; // 조회(회의실 전체 정보 조회)

	private static final String INSERT_ROOM = "INSERT INTO room values(?,?,?)"; // 추가(회의실 정보 추가)

	private static final String UPDATE_ROOM = "UPDATE room SET room_name=?, capacity=? WHERE room_id=?"; // 회의실 정보 업데이트

	private static final String DELETE_ROOM = "DELETE room WHERE room_id=?"; // 회의실 정보 삭제

	/*
	 * 회의실의 최대 수용인원 조회
	 */
	public int findMaxCapacity() {

		// jdbcTemplate 인스턴스를 통해 정의한 쿼리를 수행하는 queryForObject 메소드로 호출한다
		return jdbcTemplate.queryForObject(SELECT_MAX_CAPACITY, Integer.class); // 쿼리 결과를 정수로 반환(쿼리, 정수값)
	}

	/*
	 * 회의실의 번호로 회의실 이름 조회
	 */
	public String findRoomNameById(String roomId) { // 회의실 번호를 매개변수로 받는다

		Object[] args = { roomId }; // 조회 쿼리에 ?에 바인딩 되는 값을 배열객체에 지정한다
		return jdbcTemplate.queryForObject(GET_ROOM_NAME, args, String.class); // (쿼리, 회의실번호, 회의실번호에 따른 컬럼값 반환)
	}

	/*
	 * 회의실의 번호로 회의실 정보 조회
	 */
	public RoomVO getRoomById(String roomId) { // 회의실 번호를 매개변수로 받는다

		Object[] args = { roomId };
		
		// 회의실 정보 조회
		RoomVO room = jdbcTemplate.queryForObject(GET_ROOM_INFO, args, new RoomRowMapper());
		// 부대시설 정보 조회
		List<EquipmentVO> equipList = eDao.getEquipmentByRoomid(roomId);
		room.setEquipmentList(equipList);
		
		return room;
	}

	/*
	 * 회의실 전체 정보 조회
	 */
	public List<RoomVO> getAllRoom() {

		return jdbcTemplate.query(GET_ALL_ROOM, new RoomRowMapper());

	}

	/*
	 * 회의실 정보 추가
	 */
	public void insertRoom(RoomVO room) {

		Object[] args = { room.getRoom_id(), room.getRoom_name(), room.getCapacity() };
		jdbcTemplate.update(INSERT_ROOM, args);

	}

	/*
	 * 회의실 정보 업데이트
	 */
	public void updateRoom(RoomVO room) {

		Object[] args = { room.getRoom_name(), room.getCapacity(), room.getRoom_id() };
		jdbcTemplate.update(UPDATE_ROOM, args);

	}

	/*
	 * 회의실 정보 삭제
	 */
	public void deleteRoom(String roomId) {

		Object[] args = { roomId };
		jdbcTemplate.update(DELETE_ROOM, args);

	}

	/*
	 * RowMapper 인터페이스 구현하는 클래스(테이블 내용을 하나만 조회할때 사용)
	 */
	class RoomRowMapper implements RowMapper<RoomVO> {

		@Override
		public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			RoomVO room = new RoomVO(); // Room 인스턴스 변수를 생성한다

			room.setRoom_id(rs.getString("room_id")); // ResultSet 객체에서 회의실 아이디 값을 가져온다
			room.setRoom_name(rs.getString("room_name")); // ResultSet 객체에서 회의실 이름 값을 가져온다
			room.setCapacity(rs.getInt("capacity")); // ResultSet 객체에서 회의실 인원을 가져온다

			return room; // 배열형태로 저장된 회의실 정보를 리턴한다
		}

	}

}
