package com.green.biz.dto;

import java.util.Date;

/*
 *  DB board ���̺��� �÷���
 */
public class BoardVO {

	private int seq; // ������ ��ȣ
	private String title; // �Խ��� ����
	private String writer; // �Խ��� �ۼ���
	private String content; // �Խ��� ����
	private Date regDate; // �Խ��� �ۼ��ð�(�ý��� �� �ð��� �����Ѵ�)
	private int cnt; // �Խ��� ��ȸ��
	private String searchCondition;
	private String searchKeyword;

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

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

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
				+ ", getSeq()=" + getSeq() + ", getTitle()=" + getTitle() + ", getWriter()=" + getWriter()
				+ ", getContent()=" + getContent() + ", getRegDate()=" + getRegDate() + ", getCnt()=" + getCnt()
				+ ", getSearchCondition()=" + getSearchCondition() + ", getSearchKeyword()=" + getSearchKeyword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
