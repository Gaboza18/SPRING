package com.green.biz.member;

import com.green.biz.dto.MemberVO;

public interface MemberService {

	// 회원 id를 조건으로 상세조회
	MemberVO getMember(String id);

	int confirmID(String id);

	// 회원 등록
	void insertMember(MemberVO vo);

}