package com.green.biz.dto;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
 * XML 형식으로 여러건의 데이터 전달시
 * 하나의 List 객체로 전달하기 위한 클래스(Root Element) 필요
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
