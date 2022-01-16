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
	 * index.html에서 메인화면 표시를 위한 index URL 요청처리
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		// 신상품 조회 서비스 호출
		List<ProductVO> newProdList = productService.getNewProductList();
		model.addAttribute("newProductList", newProdList);
		
		// 베스트 상품 조회 서비스 호출
		List<ProductVO> bestProdList = productService.getBestProductList();
		model.addAttribute("bestProductList", bestProdList);
		
		return "index";
	}
	
}
