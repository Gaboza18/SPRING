package com.green.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public int selectMaxOseq() {
		return mybatis.selectOne("mappings.order_mapping.selectMaxOseq");
	}

	public void insertOrder(OrderVO vo) {
		mybatis.insert("mappings.order_mapping.insertOrder", vo);
	}

	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("mappings.order_mapping.insertOrderDetail", vo);
	}
}
