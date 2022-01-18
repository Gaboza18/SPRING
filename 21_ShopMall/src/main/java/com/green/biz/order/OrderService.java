package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.OrderVO;

public interface OrderService {

   int selectMaxOseq();

   int insertOrder(OrderVO vo);

   void insertOrderDetail(OrderVO vo);

   public List<OrderVO> listOrderById(OrderVO vo);
   
   public List<Integer> selectSeqOrdering(OrderVO vo);
}