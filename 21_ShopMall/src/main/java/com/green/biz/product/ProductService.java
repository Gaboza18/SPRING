package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;

public interface ProductService {

	// 신상품 조회
	List<ProductVO> getNewProductList();

	// 베스트 상품 조회
	List<ProductVO> getBestProductList();

	// 상품번호로 상품 상세조회
	ProductVO getProduct(ProductVO vo);

	// 카테고리별 상품 이름순으로 조회
	List<ProductVO> getProductListByKind(ProductVO vo);

}