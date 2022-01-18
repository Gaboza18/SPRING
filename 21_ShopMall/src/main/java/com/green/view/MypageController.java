package com.green.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;

@Controller
public class MypageController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

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

	/*
	 * ��ٱ��� �׸� ���� ��û ó��
	 */
	@PostMapping(value = "/cart_delete")
	public String cartDelete(@RequestParam(value = "cseq") int[] cseq) {

		for (int i = 0; i < cseq.length; i++) {
			System.out.println(("������ cart seq=" + cseq[i]));
			cartService.deleteCart(cseq[i]);
		}
		return "redirect:cart_list";
	}

	/*
	 * ��ٱ��� ������ �ֹ�ó��
	 */
	@PostMapping(value = "/order_insert")
	public String orderInsert(OrderVO vo, HttpSession session, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());

			int oseq = orderService.insertOrder(vo);

			// TODO: �ֹ���ȣ ����
			model.addAttribute("oseq", oseq);

			return "redirect:order_list";
		}
	}

	@GetMapping(value = "order_list")
	public String orderList(@RequestParam(value = "oseq") int oseq) {
		return "mypage/orderList";
	}
}
