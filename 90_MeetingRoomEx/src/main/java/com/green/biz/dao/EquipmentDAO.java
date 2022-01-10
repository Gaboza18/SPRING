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

	@Autowired // applicationContext.xml 컨테이너 에서 자동으로 생성할수 있게 설정한다
	private JdbcTemplate jdbcTemplate; // DB 객체를 제공받는다

	/* SQL 명령어 private= 싱글톤 생성자(외부 클래스에서 객체 생성하는것을 막는다) static=(클래스에 고정된 맴버로 객체를 생성하지 않고 사용) final=(프로그램 실행도중 수정할수 없다) */

	// 부대 시설 추가
	private static final String INSERT_EQUIPMENT = "INSERT INTO equipment VALUES(?,?,?,?,?)"; // equipment 테이블에 5개의 값을 삽입하는 SQL문 

	// 부대 시설 수정
	private static final String UPDATE_EQUIPMENT = "UPDATE equipment SET "
			+ "room_id=?, equipment_name=?, equipment_count=?, equipment_remarks=?  WHERE equipment_id=? "; // 장비 id를 조건으로 받아 4개의 컬럼 업데이트 하는 SQL문

	// 부대 시설 삭제
	private static final String DELETE_EQUIPMENT = "DELETE FROM equipment_id=?"; // 장비 id를 조건으로 받아 하나의 컬럼을 삭제하는 SQL문

	// 회의실에 속한 부대시설 조회
	private static final String GET_EQUIPMENT_BY_ROOMID = "SELECT * FROM equipment WHERE room_id=?"; // 방번호를 조건으로 받아 컬럼 조회하는 SQL문

	/* CRUD 메소드 */

	// 부대 시설 추가
	public void insertEquipment(EquipmentVO vo) {
		
		// Object 배열 객체에, args 변수에, EquipmentVO 클래스의 매개변수 vo를 받아 SQL문을 실행할 값 들을 바인딩 한다
		// vo 매개변수의 get메소드의 리턴값
		Object[] args = { vo.getEquipment_id(), vo.getRoom_id(), vo.getEquipment_name(), vo.getEquipment_count(),
				vo.getEquipment_remarks() };
		
		// 컨테이너의 DB객체의 update() 메소드 -> SQL쿼리문, SQL문에 바인딩 될 변수
		jdbcTemplate.update(INSERT_EQUIPMENT, args); // (쿼리, 바인딩 변수)
	}

	// 부대 시설 수정
	public void updateEquipment(EquipmentVO vo) {

		Object[] args = { vo.getRoom_id(), vo.getEquipment_name(), vo.getEquipment_count(), vo.getEquipment_remarks(),
				vo.getEquipment_id() };

		jdbcTemplate.update(UPDATE_EQUIPMENT, args); // (쿼리, 바인딩 변수)
	}

	// 부대 시설 삭제
	public void deleteEquipment(EquipmentVO vo) {

		Object[] args = { vo.getEquipment_id() }; // DELETE문은 바인딩 변수가 1개 이므로 하나만 받는다

		jdbcTemplate.update(DELETE_EQUIPMENT, args); // (쿼리, 바인딩 변수)
	}
	
	// 회의실에 속한 부대시설 조회
	public List<EquipmentVO> getEquipmentByRoomid(String roomId) {

		Object[] args = { roomId };

		return jdbcTemplate.query(GET_EQUIPMENT_BY_ROOMID, args, new EquipmentRowMapper()); // 리스트 형식으로 값을 반환(여러개의 목록 출력시 -> query)
																							
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
