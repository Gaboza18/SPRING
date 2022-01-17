package com.green.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zip_num;
	private String address;
	private String phone;
	private String useyn;
	private Timestamp regdate;
}
