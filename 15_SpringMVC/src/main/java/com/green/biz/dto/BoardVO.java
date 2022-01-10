package com.green.biz.dto;

import java.util.Date;

/*
 *  DB board 테이블의 컬럼값
 */
public class BoardVO {

	private int seq; // 시퀀스 번호
	private String title; // 게시판 제목
	private String writer; // 게시판 작성자
	private String content; // 게시판 내용
	private Date regDate; // 게시판 작성시간(시스템 상 시간을 적용한다)
	private int cnt; // 게시판 조회수

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}

}
