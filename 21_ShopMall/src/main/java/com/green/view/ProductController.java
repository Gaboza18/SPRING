package com.green.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.biz.dto.ProductVO;
import com.green.biz.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	// @RequestMapping(value="/product_detail", method=RequestMethod.GET) =
	// @GetMapping(value="/product_detail")
	
	@GetMapping(value = "/product_detail") // get������� ������ ��û��
	public String productDetailAction(ProductVO vo, Model model) {

		// ��ǰ �� ��ȸ
		ProductVO product = productService.getProduct(vo);

		// jsp�� ��ü�� �Ѱ��ֱ� ���� model ��ü ����
		model.addAttribute("productVO", product);

		return "product/productDetail"; // �󼼺���.jsp ���
	}

	@GetMapping(value = "/category") // get������� ������ ��û��
	public String productKindAction(ProductVO vo, Model model) {

		List<ProductVO> listProduct = productService.getProductListByKind(vo);

		model.addAttribute("productKindList", listProduct);
		
		return "product/productKind"; // �з��� ��ǰ��� ȭ�� ���̱�.jsp ���
	}
}
