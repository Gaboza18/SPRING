package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;

public interface ProductService {

	// �Ż�ǰ ��ȸ
	List<ProductVO> getNewProductList();

	// ����Ʈ ��ǰ ��ȸ
	List<ProductVO> getBestProductList();

	// ��ǰ��ȣ�� ��ǰ ����ȸ
	ProductVO getProduct(ProductVO vo);

	// ī�װ��� ��ǰ �̸������� ��ȸ
	List<ProductVO> getProductListByKind(ProductVO vo);
	
	// ��ü ��ǰ�� ���� ��ȸ
	public int countProductList(String name);
	
	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name);
	
	// ��ǰ �߰�
	public void insertProduct(ProductVO vo);
	
	// ��ǰ���� ����
	public void updateProduct(ProductVO vo); 

}