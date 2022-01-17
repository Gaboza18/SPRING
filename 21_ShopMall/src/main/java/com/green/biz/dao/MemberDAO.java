package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

@Repository // 스프링 객체로 등록
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 회원 id를 조건으로 상세조회
	public MemberVO getMember(String id) {
		return mybatis.selectOne("mappings.member-mapping.getMember", id);
	}

	// 회원 존재여부 확인

	/*
	 * 리턴값 : 회원이 존재하면: 1, 회원이 존재하지 않으면: -1
	 */

	public int confirmID(String id) {

		String pwd = mybatis.selectOne("mappings.member-mapping.confirmID", id);

		if (pwd != null)
			return 1;
		else
			return -1;
	}

	/*
	 * 회원 인증
	 * 
	 * id가 존재하지 않을경우: -1 / pwd가 틀릴경우: 0 / id,pwd가 일치할 경우: 1 반환
	 */

	public int loginID(MemberVO vo) {

		int result = -1; // 조회 결과

		// id를 조건으로 pwd를 읽어온다
		String pwd_in_db = mybatis.selectOne("mappings.member-mapping.confirmID", vo.getId());

		// DB와 사용자가 입력한 id/pwd 비교
		if (pwd_in_db == null) { // id가 존재하지 않을경우
			result = -1;
		} else if (vo.getPwd().equals(pwd_in_db)) { // 입력한 pwd와 DB의 pwd 일치할 경우
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	// 회원 등록
	public void insertMember(MemberVO vo) {
		mybatis.insert("mappings.member-mapping.insertMember", vo);
	}

	// 동 이름으로 주소 찾기
	public List<AddressVO> selectAddressByDong(String dong) {
		return mybatis.selectList("mappings.member-mapping.selectAddressByDong", dong);
	}
}
