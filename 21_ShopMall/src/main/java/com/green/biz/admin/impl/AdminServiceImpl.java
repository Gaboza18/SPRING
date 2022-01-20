package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.biz.admin.AdminService;
import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;

	/*
	 * 입력 파라미터: id- 관리자 id 출력 파라미터: -1: id가 존재하지 않음 / 0: 비밀번호 맞지 않음 / 1:정상 로그인
	 */

	@Override
	public int workerCheck(WorkerVO vo) {

		int result = -1;

		// worker 테이블 에서 id를 조건으로 pwd를 조회
		String pwd_in_db = adminDao.workerCheck(vo.getId());

		// 사용자 입력 pwd와 테이블에서 조회한 pwd를 비교
		if (pwd_in_db == null) {
			result = -1;
		} else if (vo.getPwd().equals(pwd_in_db)) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

	@Override
	public WorkerVO getEmployee(String id) {

		return null;
	}

}
