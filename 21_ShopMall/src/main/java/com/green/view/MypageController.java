package com.green.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.order.CartService;

@Controller
public class MypageController {

	@Autowired
	private CartService cartService;

	@PostMapping(value = "/cart_insert")
	public String insertCart(CartVO vo, Model model, HttpSession session) {

		// (1) 세션에 저장된 사용자 정보를 읽어 온다
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// (2) 로그인이 안되어 있으면 로그인, 로그인이 되어있으면, 장바구니에 항목 저장
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());

			cartService.insertCart(vo);
		}
		// (3) 장바구니 목록 조회하여 화면 표시
		return "redirect:cart_list";
	}
	
	@GetMapping(value="cart_list")
	public String listCart() {
		return "mypage/cartList";
	}
}
