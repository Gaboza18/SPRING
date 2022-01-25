package com.green.biz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesQuantity { // 제품별 판매 실적 조회
	
	private String pname;
	private int quantity;
}
