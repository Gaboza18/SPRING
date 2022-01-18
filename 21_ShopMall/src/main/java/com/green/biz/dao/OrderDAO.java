package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public int selectMaxOseq() {
		return mybatis.selectOne("mappings.order-mapping.selectMaxOseq");
	}

	public void insertOrder(OrderVO vo) {
		mybatis.insert("mappings.order-mapping.insertOrder", vo);
	}

	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("mappings.order-mapping.insertOrderDetail", vo);
	}

	public List<OrderVO> listOrderById(OrderVO vo) {
		return mybatis.selectList("mappings.order-mapping.listOrderById", vo);
	}

	public List<Integer> selectSeqOrdering(OrderVO vo) {
		return mybatis.selectList("mappings.order-mapping.selectSeqOrdering", vo);
	}

}
