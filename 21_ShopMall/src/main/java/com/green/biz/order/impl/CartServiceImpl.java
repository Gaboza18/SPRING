package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.CartDAO;
import com.green.biz.dto.CartVO;
import com.green.biz.order.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cartDao;
	
	@Override
	public void insertCart(CartVO vo) {
		cartDao.insertCart(vo);
	}

	@Override
	public List<CartVO> listCart(String userid) {
		return cartDao.listCart(userid);
	}

	@Override
	public void deleteCart(int cseq) {
		cartDao.deleteCart(cseq);
	}

}
