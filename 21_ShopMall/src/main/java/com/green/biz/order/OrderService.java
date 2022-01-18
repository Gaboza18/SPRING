package com.green.biz.order;

import com.green.biz.dto.OrderVO;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);

}