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
	 * �Է� �Ķ����: id- ������ id ��� �Ķ����: -1: id�� �������� ���� / 0: ��й�ȣ ���� ���� / 1:���� �α���
	 */

	@Override
	public int workerCheck(WorkerVO vo) { // ������ �α��� ����

		int result = -1;

		// worker ���̺� ���� id�� �������� pwd�� ��ȸ
		String pwd_in_db = adminDao.workerCheck(vo.getId()); // adminDAO ��ü�� workerCheck �޼ҵ� ȣ�Ⱚ ������ ��´�

		// ����� �Է� pwd�� ���̺��� ��ȸ�� pwd�� ��
		if (pwd_in_db == null) {
			result = -1; // ���̵� �������� ����
		} else if (vo.getPwd().equals(pwd_in_db)) { // DB�� �Է��� id���� ���Ͽ� ������
			result = 1; // ����α��� ����
		} else {
			result = 0; // ��й�ȣ�� ���� ���� ����
		}

		return result; // ���ǹ��� ����� �����Ѵ�
	}

	@Override
	public WorkerVO getEmployee(String id) { // ������ ���� ��ȸ
		return adminDao.getEmployee(id);
	}

}
