package com.green.biz.member;

import com.green.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� id�� �������� ����ȸ
	MemberVO getMember(String id);

	int confirmID(String id);

	// ȸ�� ���
	void insertMember(MemberVO vo);

}