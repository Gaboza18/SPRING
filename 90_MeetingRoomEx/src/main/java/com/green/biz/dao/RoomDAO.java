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

	@Autowired // �����̳ʰ� �ڵ����� ���� �����Ѵ�
	private JdbcTemplate jdbcTemplate;
	@Autowired // �����̳ʰ� �ڵ����� ���� �����Ѵ�
	private EquipmentDAO eDao;

	/* SQL ��ɾ� */
	private static final String SELECT_MAX_CAPACITY = "SELECT MAX(capacity) FROM room"; // ��ȸ(�ִ� �����ο�)

	private static final String GET_ROOM_NAME = "SELECT room_name FROM room WHERE room_id=?"; // ��ȸ(ȸ�ǽ� ��ȣ�� ȸ�ǽ� �̸� ��ȸ)

	private static final String GET_ROOM_INFO = "SELECT * FROM room WHERE room_id=?"; // ��ȸ(ȸ�ǽ� ��ȣ�� ȸ�ǽ� ���� ��ȸ)

	private static final String GET_ALL_ROOM = "SELECT * FROM room"; // ��ȸ(ȸ�ǽ� ��ü ���� ��ȸ)

	private static final String INSERT_ROOM = "INSERT INTO room values(?,?,?)"; // �߰�(ȸ�ǽ� ���� �߰�)

	private static final String UPDATE_ROOM = "UPDATE room SET room_name=?, capacity=? WHERE room_id=?"; // ȸ�ǽ� ���� ������Ʈ

	private static final String DELETE_ROOM = "DELETE room WHERE room_id=?"; // ȸ�ǽ� ���� ����

	/*
	 * ȸ�ǽ��� �ִ� �����ο� ��ȸ
	 */
	public int findMaxCapacity() {

		// jdbcTemplate �ν��Ͻ��� ���� ������ ������ �����ϴ� queryForObject �޼ҵ�� ȣ���Ѵ�
		return jdbcTemplate.queryForObject(SELECT_MAX_CAPACITY, Integer.class); // ���� ����� ������ ��ȯ(����, ������)
	}

	/*
	 * ȸ�ǽ��� ��ȣ�� ȸ�ǽ� �̸� ��ȸ
	 */
	public String findRoomNameById(String roomId) { // ȸ�ǽ� ��ȣ�� �Ű������� �޴´�

		Object[] args = { roomId }; // ��ȸ ������ ?�� ���ε� �Ǵ� ���� �迭��ü�� �����Ѵ�
		return jdbcTemplate.queryForObject(GET_ROOM_NAME, args, String.class); // (����, ȸ�ǽǹ�ȣ, ȸ�ǽǹ�ȣ�� ���� �÷��� ��ȯ)
	}

	/*
	 * ȸ�ǽ��� ��ȣ�� ȸ�ǽ� ���� ��ȸ
	 */
	public RoomVO getRoomById(String roomId) { // ȸ�ǽ� ��ȣ�� �Ű������� �޴´�

		Object[] args = { roomId };
		
		// ȸ�ǽ� ���� ��ȸ
		RoomVO room = jdbcTemplate.queryForObject(GET_ROOM_INFO, args, new RoomRowMapper());
		// �δ�ü� ���� ��ȸ
		List<EquipmentVO> equipList = eDao.getEquipmentByRoomid(roomId);
		room.setEquipmentList(equipList);
		
		return room;
	}

	/*
	 * ȸ�ǽ� ��ü ���� ��ȸ
	 */
	public List<RoomVO> getAllRoom() {

		return jdbcTemplate.query(GET_ALL_ROOM, new RoomRowMapper());

	}

	/*
	 * ȸ�ǽ� ���� �߰�
	 */
	public void insertRoom(RoomVO room) {

		Object[] args = { room.getRoom_id(), room.getRoom_name(), room.getCapacity() };
		jdbcTemplate.update(INSERT_ROOM, args);

	}

	/*
	 * ȸ�ǽ� ���� ������Ʈ
	 */
	public void updateRoom(RoomVO room) {

		Object[] args = { room.getRoom_name(), room.getCapacity(), room.getRoom_id() };
		jdbcTemplate.update(UPDATE_ROOM, args);

	}

	/*
	 * ȸ�ǽ� ���� ����
	 */
	public void deleteRoom(String roomId) {

		Object[] args = { roomId };
		jdbcTemplate.update(DELETE_ROOM, args);

	}

	/*
	 * RowMapper �������̽� �����ϴ� Ŭ����(���̺� ������ �ϳ��� ��ȸ�Ҷ� ���)
	 */
	class RoomRowMapper implements RowMapper<RoomVO> {

		@Override
		public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			RoomVO room = new RoomVO(); // Room �ν��Ͻ� ������ �����Ѵ�

			room.setRoom_id(rs.getString("room_id")); // ResultSet ��ü���� ȸ�ǽ� ���̵� ���� �����´�
			room.setRoom_name(rs.getString("room_name")); // ResultSet ��ü���� ȸ�ǽ� �̸� ���� �����´�
			room.setCapacity(rs.getInt("capacity")); // ResultSet ��ü���� ȸ�ǽ� �ο��� �����´�

			return room; // �迭���·� ����� ȸ�ǽ� ������ �����Ѵ�
		}

	}

}
