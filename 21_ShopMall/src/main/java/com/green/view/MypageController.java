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
	 * ��ٱ��� ��� ��û ó��
	 */

	@PostMapping(value = "/cart_insert")
	public String insertCart(CartVO vo, Model model, HttpSession session) {

		// (1) ���ǿ� ����� ����� ������ �о� �´�
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// (2) �α����� �ȵǾ� ������ �α���, �α����� �Ǿ�������, ��ٱ��Ͽ� �׸� ����
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());

			cartService.insertCart(vo);
		}
		// (3) ��ٱ��� ��� ��ȸ�Ͽ� ȭ�� ǥ��
		return "redirect:cart_list";
	}

	/*
	 * ��ٱ��� ���
	 */

	@GetMapping(value = "cart_list")
	public String listCart(HttpSession session, Model model) {

		// (1) ���ǿ� ����� ����� ������ �о� �´�
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// (2) �α����� �ȵǾ� ������ �α���, �α����� �Ǿ�������, ��ٱ��� �׸� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			
			// �� ���̵� �޾� ��ٱ��� ����� �����´�
			List<CartVO> cartList = cartService.listCart(loginUser.getId());

			// �Ѿ� ���
			int totalAmount = 0;
			for (CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}

			// ��ٱ��� ����� ���尴ü�� ����
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);

			return "mypage/cartList";
		}

	}
}
