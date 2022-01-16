package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductVO;

@Repository // 스프링 객체로 등록
public class ProductDAO {
	

	@Autowired // applicationContext.xml에 설정한 객체
	private SqlSessionTemplate mybatis; 

	// 신상품 조회
	public List<ProductVO> getNewProductList() {
		return mybatis.selectList("mappings.product-mapping.getNewProductList");
	}
	
	// 베스트 상품 조회
	public List<ProductVO> getBestProductList() {
		return mybatis.selectList("mappings.product-mapping.getBestProductList");
	}
	
	// 상품번호로 상품 상세조회
	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("mappings.product-mapping.getProduct", vo);
	}
	
	// 카테고리별 상품 이름순으로 조회
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return mybatis.selectList("mappings.product-mapping.getProductListByKind", vo);
	}
	
}
