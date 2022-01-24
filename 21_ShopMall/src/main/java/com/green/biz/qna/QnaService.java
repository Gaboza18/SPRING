package com.green.biz.qna;

import java.util.List;

import com.green.biz.dto.QnaVO;

public interface QnaService {

	// ��ü QnA ��� ��ȸ
	List<QnaVO> listQna(String id);

	// �ϷĹ�ȣ�� �Խñ� �Ѱ� ��ȸ
	QnaVO getQna(int qseq);

	// �Խù� ���
	void insertQna(QnaVO vo);
	
	// �Խù� ��ȸ(������)
	public List<QnaVO> listAllQna();
	
	// �Խù� �亯ó��(������)
	public void updateQna(QnaVO vo);

}