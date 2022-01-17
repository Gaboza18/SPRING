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
		return mybatis.selectOne("mappings.member-mapping", id);
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

	// 회원 등록
	public void insertMember(MemberVO vo) {
		mybatis.insert("mappings.member-mapping.insertMember", vo);
	}
	
	// 동 이름으로 주소 찾기
	public List<AddressVO> selectAddressByDong(String dong) {
		return mybatis.selectList("mappings.member-mapping.selectAddressByDong", dong);
	}
}
