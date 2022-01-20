package com.green.view;

import java.util.ArrayList;
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

	/*
	 * �������� �ֹ����� ��ȸ �Է� �Ķ����: oseq, result = '1'
	 */

	@GetMapping(value = "/order_list")
	public String orderList(@RequestParam(value = "oseq") int oseq, HttpSession session, Model model, OrderVO order) {

		// (1) �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// (2) �ֹ���ȣ�� �������� �ֹ� ���� ��ȸ
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			List<OrderVO> orderList = orderService.listOrderById(order);

			// (3) �ֹ� �Ѿ� ���
			int totalAmount = 0;
			for (OrderVO vo : orderList) {
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}

			// (4) ���� ��ü�� ��� ����
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);

			// (5) ȭ�� ȣ��
			return "mypage/orderList";
		}
	}

	@GetMapping(value = "/mypage")
	public String myPageView(HttpSession session, Model model) {

		// ���ǿ� ����� �α��� ���� �о��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// ����ڰ� �ֹ��� ��� �ֹ���ȣ ��ȸ
			OrderVO vo = new OrderVO();

			vo.setId(loginUser.getId()); // ȸ�� ID
			vo.setResult("1"); // �ֹ� ó���Ϸ� -- ���� �Ϸ�:1 �̿Ϸ�:2 -- �ϷḸ �����´�

			List<Integer> oseqList = orderService.selectSeqOrdering(vo); // ����ں� �ֹ���ȣ ���

			// �� �ֹ���ȣ�� ��ȸ�Ͽ� �ֹ�������� ����

			// (1) �ֹ� ��� ���� ���� ����
			List<OrderVO> orderList = new ArrayList<OrderVO>(); // �ֹ���ȣ ��� ����

			// (2) ��� �ֹ���ȣ�� ���� ������� ����
			for (int oseq : oseqList) {

				OrderVO orderVO = new OrderVO();

				orderVO.setId(loginUser.getId()); // ����� ID
				orderVO.setOseq(oseq); //
				orderVO.setResult("1"); //

				// �� �ֹ��� ���� �ֹ����� ��ȸ(����� ID�� ���� �ֹ�����)
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);

				// ������ �ֹ��� �ֹ������� ������� ����
				OrderVO order = new OrderVO();

				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate());

				if (listByOseq.size() > 1) { // �ֹ����� 2�� �̻� �ϰ��
					order.setPname(listByOseq.get(0).getPname() + " ��" + (listByOseq.size() - 1) + "��");
				} else {
					order.setPname(listByOseq.get(0).getPname()); // �ֹ����� 1�� ���� �ϰ��
				}

				// �ֹ���ȣ�� �Ѿ� ���
				int amount = 0;
				for (int i = 0; i < listByOseq.size(); i++) { // n�� �ֹ��� ���� ���(n�� �ֹ� �� n�ǿ� ���� ���)
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
				}

				order.setPrice2(amount);

				// ��������� List ������ �߰�
				orderList.add(order); // n�� �ֹ��� n�� n�� �� ����Ʈ�� ��´�
			}
			model.addAttribute("title", "�������� �ֹ�����"); // My Page(${title})
			model.addAttribute("orderList", orderList); // items="${orderList}"
		}
		return "mypage/mypage";
	}

	// �������� �ֹ����� �󼼺���

	@GetMapping(value = "/order_detail")
	public String orderDetail(OrderVO vo, HttpSession session, Model model) {

		// ���� ��ü���� �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// �ֹ���ȣ�� �������� �ֹ� ��ȸ
			vo.setId(loginUser.getId());
			// vo.setResult("1");
			vo.setResult("");

			List<OrderVO> orderList = orderService.listOrderById(vo); // �ֹ� ���

			// ȭ�鿡 ����� ���� ����

			// (1) �ֹ��� ���� ����
			OrderVO orderDetail = new OrderVO();

			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());

			// (2) �ֹ� �հ� �ݾ� ���
			int amount = 0;

			for (int i = 0; i < orderList.size(); i++) {
				amount += (orderList.get(i).getQuantity() * orderList.get(i).getPrice2());
			}

			model.addAttribute("title", "My Page(�ֹ� �� ����)"); // <h2> ${title} </h2>
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList", orderList);

			return "mypage/orderDetail";
		}

	}

	// �� �ֹ� ���� ����(ó������� ������� ������� ��� �ֹ� ��ȸ)

	@GetMapping(value = "/order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {

		// ����� �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// ������� ��ü �ֹ���ȣ Ȯ��

			vo.setId(loginUser.getId()); // ȸ�� ID
			vo.setResult(""); // ó����� ���� ����

			List<Integer> oseqList = orderService.selectSeqOrdering(vo);

			// �� �ֹ���ȣ �� �ֹ����� ��ȸ

			// (1) �ֹ� ��� ���� ���� ����
			List<OrderVO> orderList = new ArrayList<>(); // �ֹ���ȣ ��� ����

			// (2) ��� �ֹ���ȣ�� ���� ������� ����
			for (int oseq : oseqList) {

				OrderVO orderVO = new OrderVO();

				orderVO.setId(loginUser.getId()); // ����� ID
				orderVO.setOseq(oseq); //
				orderVO.setResult(""); //

				// �� �ֹ��� ���� �ֹ����� ��ȸ(����� ID�� ���� �ֹ�����)
				List<OrderVO> orders = orderService.listOrderById(orderVO);

				// �ֹ���� ���� ����
				OrderVO summary = new OrderVO();

				summary = orders.get(0); // ù��° ��ǰ���� ������ ����

				if (orders.size() > 1) {
					summary.setPname(orders.get(0).getPname() + " ��" + (orders.size() - 1) + "��");
				} else {
					summary.setPname(orders.get(0).getPname());
				}

				// �ֹ���ȣ�� �Ѿ� ���
				int amount = 0;
				for (OrderVO order : orders) {
					amount += (order.getQuantity() * order.getPrice2());
				}

				summary.setPrice2(amount);

				// �������� List ������ �߰�
				orderList.add(summary);
			}

			// ��� ȭ�� ����
			model.addAttribute("title", "�� �ֹ� ����"); // My Page(${title})
			model.addAttribute("orderList", orderList); // items="${orderList}"

			return "mypage/mypage";
		}
	}
}
