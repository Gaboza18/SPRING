package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	// ������ ����
	int workerCheck(WorkerVO vo);

	// ������ ���� ��ȸ
	WorkerVO getEmployee(String id);

}