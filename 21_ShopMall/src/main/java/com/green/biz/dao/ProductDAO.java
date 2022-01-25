package com.green.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductVO;
import com.green.biz.dto.SalesQuantity;

import utils.Criteria;

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
		return mybatis.selectOne("mappings.product-mapping.countProductlist", name);
	}

	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("mappings.product-mapping.listProduct", name);
	}

	// �������� ��ǰ��� ��ȸ
	public List<ProductVO> getListWithPaging(Criteria criteria, String name) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("name", name);

		return mybatis.selectList("mappings.product-mapping.listWithPaging", map);

	}

	// ��ǰ �߰�
	public void insertProduct(ProductVO vo) {
		mybatis.insert("mappings.product-mapping.insertProduct", vo);
	}

	// ��ǰ���� ����
	public void updateProduct(ProductVO vo) {
		mybatis.update("mappings.product-mapping.updateProduct", vo);
	}

	// ��ǰ�� �Ǹ� ���� ��ȸ
	public List<SalesQuantity> getProductSales() {
		return mybatis.selectList("mappings.product-mapping.getProductSales");
	}

}
