package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.CartVO;

public interface CartService {

	// ��ٱ��� ���
	void insertCart(CartVO vo);

	// ��ٱ��� ���
	List<CartVO> listCart(String userid);

	// ��ٱ��Ͽ��� �׸� ����
	void deleteCart(int cseq);

}