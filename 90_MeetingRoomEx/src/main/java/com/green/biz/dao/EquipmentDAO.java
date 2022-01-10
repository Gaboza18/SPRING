package com.green.biz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.green.biz.vo.EquipmentVO;

@Repository("EquipmentDAO")
public class EquipmentDAO {

	@Autowired // applicationContext.xml �����̳� ���� �ڵ����� �����Ҽ� �ְ� �����Ѵ�
	private JdbcTemplate jdbcTemplate; // DB ��ü�� �����޴´�

	/* SQL ��ɾ� private= �̱��� ������(�ܺ� Ŭ�������� ��ü �����ϴ°��� ���´�) static=(Ŭ������ ������ �ɹ��� ��ü�� �������� �ʰ� ���) final=(���α׷� ���൵�� �����Ҽ� ����) */

	// �δ� �ü� �߰�
	private static final String INSERT_EQUIPMENT = "INSERT INTO equipment VALUES(?,?,?,?,?)"; // equipment ���̺� 5���� ���� �����ϴ� SQL�� 

	// �δ� �ü� ����
	private static final String UPDATE_EQUIPMENT = "UPDATE equipment SET "
			+ "room_id=?, equipment_name=?, equipment_count=?, equipment_remarks=?  WHERE equipment_id=? "; // ��� id�� �������� �޾� 4���� �÷� ������Ʈ �ϴ� SQL��

	// �δ� �ü� ����
	private static final String DELETE_EQUIPMENT = "DELETE FROM equipment_id=?"; // ��� id�� �������� �޾� �ϳ��� �÷��� �����ϴ� SQL��

	// ȸ�ǽǿ� ���� �δ�ü� ��ȸ
	private static final String GET_EQUIPMENT_BY_ROOMID = "SELECT * FROM equipment WHERE room_id=?"; // ���ȣ�� �������� �޾� �÷� ��ȸ�ϴ� SQL��

	/* CRUD �޼ҵ� */

	// �δ� �ü� �߰�
	public void insertEquipment(EquipmentVO vo) {
		
		// Object �迭 ��ü��, args ������, EquipmentVO Ŭ������ �Ű����� vo�� �޾� SQL���� ������ �� ���� ���ε� �Ѵ�
		// vo �Ű������� get�޼ҵ��� ���ϰ�
		Object[] args = { vo.getEquipment_id(), vo.getRoom_id(), vo.getEquipment_name(), vo.getEquipment_count(),
				vo.getEquipment_remarks() };
		
		// �����̳��� DB��ü�� update() �޼ҵ� -> SQL������, SQL���� ���ε� �� ����
		jdbcTemplate.update(INSERT_EQUIPMENT, args); // (����, ���ε� ����)
	}

	// �δ� �ü� ����
	public void updateEquipment(EquipmentVO vo) {

		Object[] args = { vo.getRoom_id(), vo.getEquipment_name(), vo.getEquipment_count(), vo.getEquipment_remarks(),
				vo.getEquipment_id() };

		jdbcTemplate.update(UPDATE_EQUIPMENT, args); // (����, ���ε� ����)
	}

	// �δ� �ü� ����
	public void deleteEquipment(EquipmentVO vo) {

		Object[] args = { vo.getEquipment_id() }; // DELETE���� ���ε� ������ 1�� �̹Ƿ� �ϳ��� �޴´�

		jdbcTemplate.update(DELETE_EQUIPMENT, args); // (����, ���ε� ����)
	}
	
	// ȸ�ǽǿ� ���� �δ�ü� ��ȸ
	public List<EquipmentVO> getEquipmentByRoomid(String roomId) {

		Object[] args = { roomId };

		return jdbcTemplate.query(GET_EQUIPMENT_BY_ROOMID, args, new EquipmentRowMapper()); // ����Ʈ �������� ���� ��ȯ(�������� ��� ��½� -> query)
																							
	}

	class EquipmentRowMapper implements RowMapper<EquipmentVO> {

		public EquipmentVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			EquipmentVO vo = new EquipmentVO();

			vo.setEquipment_id(rs.getString("equipment_id"));
			vo.setRoom_id(rs.getString("room_id"));
			vo.setEquipment_name(rs.getString("equipment_name"));
			vo.setEquipment_count(rs.getInt("equipment_count"));
			vo.setEquipment_remarks(rs.getString("equipment_remarks"));

			return vo;
		}

	}

}
