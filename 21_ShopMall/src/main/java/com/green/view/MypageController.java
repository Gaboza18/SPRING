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

	/*
	 * 장바구니 항목 삭제 요청 처리
	 */
	@PostMapping(value = "/cart_delete")
	public String cartDelete(@RequestParam(value = "cseq") int[] cseq) {

		for (int i = 0; i < cseq.length; i++) {
			System.out.println(("삭제할 cart seq=" + cseq[i]));
			cartService.deleteCart(cseq[i]);
		}
		return "redirect:cart_list";
	}

	/*
	 * 장바구니 내역의 주문처리
	 */
	@PostMapping(value = "/order_insert")
	public String orderInsert(OrderVO vo, HttpSession session, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());

			int oseq = orderService.insertOrder(vo);

			// TODO: 주문번호 전달
			model.addAttribute("oseq", oseq);

			return "redirect:order_list";
		}
	}

	/*
	 * 진행중인 주문내역 조회 입력 파라미터: oseq, result = '1'
	 */

	@GetMapping(value = "/order_list")
	public String orderList(@RequestParam(value = "oseq") int oseq, HttpSession session, Model model, OrderVO order) {

		// (1) 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// (2) 주문번호별 진행중인 주문 내역 조회
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			List<OrderVO> orderList = orderService.listOrderById(order);

			// (3) 주문 총액 계산
			int totalAmount = 0;
			for (OrderVO vo : orderList) {
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}

			// (4) 내장 객체에 결과 저장
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);

			// (5) 화면 호출
			return "mypage/orderList";
		}
	}

	@GetMapping(value = "/mypage")
	public String myPageView(HttpSession session, Model model) {

		// 세션에 저장된 로그인 정보 읽어옴
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// 사용자가 주문한 모든 주문번호 조회
			OrderVO vo = new OrderVO();

			vo.setId(loginUser.getId()); // 회원 ID
			vo.setResult("1"); // 주문 처리완료 -- 여부 완료:1 미완료:2 -- 완료만 가져온다

			List<Integer> oseqList = orderService.selectSeqOrdering(vo); // 사용자별 주문번호 목록

			// 각 주문번호를 조회하여 주문요약정보 생성

			// (1) 주문 요약 정보 저장 변수
			List<OrderVO> orderList = new ArrayList<OrderVO>(); // 주문번호 목록 저장

			// (2) 모든 주문번호에 대한 요약정보 생성
			for (int oseq : oseqList) {

				OrderVO orderVO = new OrderVO();

				orderVO.setId(loginUser.getId()); // 사용자 ID
				orderVO.setOseq(oseq); //
				orderVO.setResult("1"); //

				// 각 주문에 대한 주문내역 조회(사용자 ID에 대한 주문내역)
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);

				// 위에서 주문한 주문내역의 요약정보 생성
				OrderVO order = new OrderVO();

				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate());

				if (listByOseq.size() > 1) { // 주문내역 2건 이상 일경우
					order.setPname(listByOseq.get(0).getPname() + " 외" + (listByOseq.size() - 1) + "건");
				} else {
					order.setPname(listByOseq.get(0).getPname()); // 주문내역 1건 이하 일경우
				}

				// 주문번호별 총액 계산
				int amount = 0;
				for (int i = 0; i < listByOseq.size(); i++) { // n번 주문에 대해 계산(n번 주문 외 n건에 대해 계산)
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
				}

				order.setPrice2(amount);

				// 요약정보를 List 변수에 추가
				orderList.add(order); // n번 주문외 n건 n원 값 리스트로 담는다
			}
			model.addAttribute("title", "진행중인 주문내역"); // My Page(${title})
			model.addAttribute("orderList", orderList); // items="${orderList}"
		}
		return "mypage/mypage";
	}

	// 진행중인 주문내역 상세보기

	@GetMapping(value = "/order_detail")
	public String orderDetail(OrderVO vo, HttpSession session, Model model) {

		// 세션 객체에서 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// 주문번호를 조건으로 주문 조회
			vo.setId(loginUser.getId());
			// vo.setResult("1");
			vo.setResult("");

			List<OrderVO> orderList = orderService.listOrderById(vo); // 주문 목록

			// 화면에 출력할 정보 생성

			// (1) 주문자 정보 생성
			OrderVO orderDetail = new OrderVO();

			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());

			// (2) 주문 합계 금액 계산
			int amount = 0;

			for (int i = 0; i < orderList.size(); i++) {
				amount += (orderList.get(i).getQuantity() * orderList.get(i).getPrice2());
			}

			model.addAttribute("title", "My Page(주문 상세 정보)"); // <h2> ${title} </h2>
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList", orderList);

			return "mypage/orderDetail";
		}

	}

	// 총 주문 내역 보기(처리결과에 관계없이 사용자의 모든 주문 조회)

	@GetMapping(value = "/order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {

		// 사용자 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {

			// 사용자의 전체 주문번호 확인

			vo.setId(loginUser.getId()); // 회원 ID
			vo.setResult(""); // 처리결과 지정 안함

			List<Integer> oseqList = orderService.selectSeqOrdering(vo);

			// 각 주문번호 별 주문내역 조회

			// (1) 주문 요약 정보 저장 변수
			List<OrderVO> orderList = new ArrayList<>(); // 주문번호 목록 저장

			// (2) 모든 주문번호에 대한 요약정보 생성
			for (int oseq : oseqList) {

				OrderVO orderVO = new OrderVO();

				orderVO.setId(loginUser.getId()); // 사용자 ID
				orderVO.setOseq(oseq); //
				orderVO.setResult(""); //

				// 각 주문에 대한 주문내역 조회(사용자 ID에 대한 주문내역)
				List<OrderVO> orders = orderService.listOrderById(orderVO);

				// 주문요약 정보 생성
				OrderVO summary = new OrderVO();

				summary = orders.get(0); // 첫번째 상품내역 정보를 복사

				if (orders.size() > 1) {
					summary.setPname(orders.get(0).getPname() + " 외" + (orders.size() - 1) + "건");
				} else {
					summary.setPname(orders.get(0).getPname());
				}

				// 주문번호별 총액 계산
				int amount = 0;
				for (OrderVO order : orders) {
					amount += (order.getQuantity() * order.getPrice2());
				}

				summary.setPrice2(amount);

				// 총합정보 List 변수에 추가
				orderList.add(summary);
			}

			// 결과 화면 전달
			model.addAttribute("title", "총 주문 내역"); // My Page(${title})
			model.addAttribute("orderList", orderList); // items="${orderList}"

			return "mypage/mypage";
		}
	}
}
