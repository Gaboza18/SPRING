package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// ��ü QnA ��� ��ȸ
	public List<QnaVO> listQna(String id) {
		return mybatis.selectList("mappings.qna-mapping.listQna", id);
	}

	// �ϷĹ�ȣ�� �Խñ� �Ѱ� ��ȸ
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("mappings.qna-mapping.getQna", qseq);
	}

	// �Խù� ���
	public void insertQna(QnaVO vo) {
		mybatis.insert("mappings.qna-mapping.insertQna", vo);
	}

}
