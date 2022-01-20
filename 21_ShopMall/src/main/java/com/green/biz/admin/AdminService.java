package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	// 관리자 인증
	int workerCheck(WorkerVO vo);

	// 관리자 정보 조회
	WorkerVO getEmployee(String id);

}