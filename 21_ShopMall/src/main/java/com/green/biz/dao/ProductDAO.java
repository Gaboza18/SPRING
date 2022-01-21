package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductVO;

@Repository // ������ ��ü�� ���
public class ProductDAO {

	@Autowired // applicationContext.xml�� ������ ��ü
	private SqlSessionTemplate mybatis;

	// �Ż�ǰ ��ȸ
	public List<ProductVO> getNewProductList() {
		return mybatis.selectList("mappings.product-mapping.getNewProductList");
	}

	// ����Ʈ ��ǰ ��ȸ
	public List<ProductVO> getBestProductList() {
		return mybatis.selectList("mappings.product-mapping.getBestProductList");
	}

	// ��ǰ��ȣ�� ��ǰ ����ȸ
	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("mappings.product-mapping.getProduct", vo);
	}

	// ī�װ��� ��ǰ �̸������� ��ȸ
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return mybatis.selectList("mappings.product-mapping.getProductListByKind", vo);
	}

	// ��ü ��ǰ�� ���� ��ȸ
	public int countProductList(String name) {
		return mybatis.selectOne("mappings.product-mapping.countProductList", name);
	}

	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("mappings.product-mapping.listProduct", name);
	}

	// ��ǰ �߰�
	public void insertProduct(ProductVO vo) {
		mybatis.insert("mappings.product-mapping.insertProduct", vo);
	}

	// ��ǰ���� ����
	public void updateProduct(ProductVO vo) {
		mybatis.update("mappings.product-mapping.updateProduct", vo);
	}

}
