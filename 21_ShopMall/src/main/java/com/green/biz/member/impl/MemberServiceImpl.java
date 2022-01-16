package com.green.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.MemberDAO;
import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO getMember(String id) {
		return mDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {
		return mDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO vo) {
		mDao.insertMember(vo);
	}

}
