package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;
import com.green.biz.dto.SalesQuantity;

import utils.Criteria;

public interface ProductService {

	// 신상품 조회
	List<ProductVO> getNewProductList();

	// 베스트 상품 조회
	List<ProductVO> getBestProductList();

	// 상품번호로 상품 상세조회
	ProductVO getProduct(ProductVO vo);

	// 카테고리별 상품 이름순으로 조회
	List<ProductVO> getProductListByKind(ProductVO vo);

	// 전체 제품의 갯수 조회
	public int countProductList(String name);

	// 제품 목록 조회
	public List<ProductVO> listProduct(String name);

	// 제품 추가
	public void insertProduct(ProductVO vo);

	// 제품정보 수정
	public void updateProduct(ProductVO vo);

	// 페이지별 상품목록 조회
	public List<ProductVO> getListWithPaging(Criteria criteria, String name);

	// 제품별 판매 실적 조회
	public List<SalesQuantity> getProductSales();

}