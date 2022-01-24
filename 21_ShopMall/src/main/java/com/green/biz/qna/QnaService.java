package com.green.biz.qna;

import java.util.List;

import com.green.biz.dto.QnaVO;

public interface QnaService {

	// 전체 QnA 목록 조회
	List<QnaVO> listQna(String id);

	// 일렬번호별 게시글 한건 조회
	QnaVO getQna(int qseq);

	// 게시물 등록
	void insertQna(QnaVO vo);
	
	// 게시물 조회(관리자)
	public List<QnaVO> listAllQna();
	
	// 게시물 답변처리(관리자)
	public void updateQna(QnaVO vo);

}