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
	
	@GetMapping(value = "/product_detail") // get방식으로 들어오는 요청값
	public String productDetailAction(ProductVO vo, Model model) {

		// 제품 상세 조회
		ProductVO product = productService.getProduct(vo);

		// jsp에 객체를 넘겨주기 위해 model 객체 선언
		model.addAttribute("productVO", product);

		return "product/productDetail"; // 상세보기.jsp 경로
	}

	@GetMapping(value = "/category") // get방식으로 들어오는 요청값
	public String productKindAction(ProductVO vo, Model model) {

		List<ProductVO> listProduct = productService.getProductListByKind(vo);

		model.addAttribute("productKindList", listProduct);
		
		return "product/productKind"; // 분류별 상품목록 화면 보이기.jsp 경로
	}
}
