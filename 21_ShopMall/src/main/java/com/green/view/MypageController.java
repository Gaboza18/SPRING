package com.green.view;

import java.util.List;

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

	/*
	 * 장바구니 담기 요청 처리
	 */

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

	/*
	 * 장바구니 목록
	 */

	@GetMapping(value = "cart_list")
	public String listCart(HttpSession session, Model model) {

		// (1) 세션에 저장된 사용자 정보를 읽어 온다
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// (2) 로그인이 안되어 있으면 로그인, 로그인이 되어있으면, 장바구니 항목 페이지로 이동
		if (loginUser == null) {
			return "member/login";
		} else {
			
			// 고객 아이디를 받아 장바구니 목록을 가져온다
			List<CartVO> cartList = cartService.listCart(loginUser.getId());

			// 총액 계산
			int totalAmount = 0;
			for (CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}

			// 장바구니 목록을 내장객체에 저장
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);

			return "mypage/cartList";
		}

	}
}
