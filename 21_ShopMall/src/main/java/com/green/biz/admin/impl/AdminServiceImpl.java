package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.admin.AdminService;
import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;

	/*
	 * 입력 파라미터: id- 관리자 id 출력 파라미터: -1: id가 존재하지 않음 / 0: 비밀번호 맞지 않음 / 1:정상 로그인
	 */

	@Override
	public int workerCheck(WorkerVO vo) { // 관리자 로그인 구현

		int result = -1;

		// worker 테이블 에서 id를 조건으로 pwd를 조회
		String pwd_in_db = adminDao.workerCheck(vo.getId()); // adminDAO 객체의 workerCheck 메소드 호출값 변수에 담는다

		// 사용자 입력 pwd와 테이블에서 조회한 pwd를 비교
		if (pwd_in_db == null) {
			result = -1; // 아이디가 존재하지 않음
		} else if (vo.getPwd().equals(pwd_in_db)) { // DB와 입력한 id값을 비교하여 같으면
			result = 1; // 정상로그인 실행
		} else {
			result = 0; // 비밀번호가 맞지 않음 실행
		}

		return result; // 조건문의 결과를 리턴한다
	}

	@Override
	public WorkerVO getEmployee(String id) { // 관리자 정보 조회
		return adminDao.getEmployee(id);
	}

}
