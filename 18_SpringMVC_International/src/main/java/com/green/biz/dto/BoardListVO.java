package com.green.biz.dto;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
 * XML �������� �������� ������ ���޽�
 * �ϳ��� List ��ü�� �����ϱ� ���� Ŭ����(Root Element) �ʿ�
 */

@XmlRootElement(name = "boardList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardListVO {
	@XmlElement(name="board")
	private List<BoardVO> boardList;

	public List<BoardVO> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
}
