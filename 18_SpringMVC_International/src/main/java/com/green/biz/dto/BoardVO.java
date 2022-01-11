package com.green.biz.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

/*
 *  DB board 테이블의 컬럼값
 */

@XmlAccessorType(XmlAccessType.FIELD) // 이 클래스가 XML 문서로 변환할 수 있다는 의미

public class BoardVO {
	
	@XmlAttribute
	private int seq; // 시퀀스 번호
	private String title; // 게시판 제목
	private String writer; // 게시판 작성자
	private String content; // 게시판 내용
	private Date regDate; // 게시판 작성시간(시스템 상 시간을 적용한다)
	private int cnt; // 게시판 조회수
	@XmlTransient
	private String searchCondition; // 게시물 검색 조건
	@XmlTransient
	private String searchKeyword; // 게시물 조건 키워드
	@XmlTransient
	private MultipartFile uploadFile; // 파일 업로드

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
	// @JsonIgnore // 데이터 변환 배제시킨다
	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	// @JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	// @JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ ", uploadFile=" + uploadFile + "]";
	}

}
