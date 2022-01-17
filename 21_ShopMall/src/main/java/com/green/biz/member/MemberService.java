package com.green.biz.member;

import java.util.List;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� id�� �������� ����ȸ
	MemberVO getMember(String id);

	int confirmID(String id);
	
	// ����� ����
	public int loginID(MemberVO vo);
	
	// ȸ�� ���
	void insertMember(MemberVO vo);
	
	// ���̸����� �ּ� ã��
	public List<AddressVO> selectAddressByDong(String dong);
	

}