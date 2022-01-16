package com.green.view;


import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.ProductVO;
import com.green.biz.product.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	/**
	 * index.html���� ����ȭ�� ǥ�ø� ���� index URL ��ûó��
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		// �Ż�ǰ ��ȸ ���� ȣ��
		List<ProductVO> newProdList = productService.getNewProductList();
		model.addAttribute("newProductList", newProdList);
		
		// ����Ʈ ��ǰ ��ȸ ���� ȣ��
		List<ProductVO> bestProdList = productService.getBestProductList();
		model.addAttribute("bestProductList", bestProdList);
		
		return "index";
	}
	
}
