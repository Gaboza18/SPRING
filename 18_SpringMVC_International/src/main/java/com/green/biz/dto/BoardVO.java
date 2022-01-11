package com.green.biz.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

/*
 *  DB board ���̺��� �÷���
 */

@XmlAccessorType(XmlAccessType.FIELD) // �� Ŭ������ XML ������ ��ȯ�� �� �ִٴ� �ǹ�

public class BoardVO {
	
	@XmlAttribute
	private int seq; // ������ ��ȣ
	private String title; // �Խ��� ����
	private String writer; // �Խ��� �ۼ���
	private String content; // �Խ��� ����
	private Date regDate; // �Խ��� �ۼ��ð�(�ý��� �� �ð��� �����Ѵ�)
	private int cnt; // �Խ��� ��ȸ��
	@XmlTransient
	private String searchCondition; // �Խù� �˻� ����
	@XmlTransient
	private String searchKeyword; // �Խù� ���� Ű����
	@XmlTransient
	private MultipartFile uploadFile; // ���� ���ε�

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
	// @JsonIgnore // ������ ��ȯ ������Ų��
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
