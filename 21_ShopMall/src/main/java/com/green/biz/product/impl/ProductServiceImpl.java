package com.green.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.ProductDAO;
import com.green.biz.dto.ProductVO;
import com.green.biz.product.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO pDao;

	@Override
	public List<ProductVO> getNewProductList() {
		return pDao.getNewProductList();
	}

	@Override
	public List<ProductVO> getBestProductList() {
		return pDao.getBestProductList();
	}

	@Override
	
	public ProductVO getProduct(ProductVO vo) {
		return pDao.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return pDao.getProductListByKind(vo);
	}

}
