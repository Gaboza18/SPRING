package com.green.biz.member;

import java.util.List;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {

	// 회원 id를 조건으로 상세조회
	MemberVO getMember(String id);

	int confirmID(String id);
	
	// 사용자 인증
	public int loginID(MemberVO vo);
	
	// 회원 등록
	void insertMember(MemberVO vo);
	
	// 동이름으로 주소 찾기
	public List<AddressVO> selectAddressByDong(String dong);
	

}