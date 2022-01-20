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

	// 전체 QnA 목록 조회
	public List<QnaVO> listQna(String id) {
		return mybatis.selectList("mappings.qna-mapping.listQna", id);
	}

	// 일렬번호별 게시글 한건 조회
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("mappings.qna-mapping.getQna", qseq);
	}

	// 게시물 등록
	public void insertQna(QnaVO vo) {
		mybatis.insert("mappings.qna-mapping.insertQna", vo);
	}

}
