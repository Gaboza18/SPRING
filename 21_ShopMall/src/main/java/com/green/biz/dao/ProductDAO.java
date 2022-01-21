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

	// 전체 제품의 갯수 조회
	public int countProductList(String name) {
		return mybatis.selectOne("mappings.product-mapping.countProductList", name);
	}

	// 제품 목록 조회
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("mappings.product-mapping.listProduct", name);
	}

	// 제품 추가
	public void insertProduct(ProductVO vo) {
		mybatis.insert("mappings.product-mapping.insertProduct", vo);
	}

	// 제품정보 수정
	public void updateProduct(ProductVO vo) {
		mybatis.update("mappings.product-mapping.updateProduct", vo);
	}

}
