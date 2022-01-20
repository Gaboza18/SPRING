package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.biz.admin.AdminService;
import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;

	/*
	 * �Է� �Ķ����: id- ������ id ��� �Ķ����: -1: id�� �������� ���� / 0: ��й�ȣ ���� ���� / 1:���� �α���
	 */

	@Override
	public int workerCheck(WorkerVO vo) {

		int result = -1;

		// worker ���̺� ���� id�� �������� pwd�� ��ȸ
		String pwd_in_db = adminDao.workerCheck(vo.getId());

		// ����� �Է� pwd�� ���̺��� ��ȸ�� pwd�� ��
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
