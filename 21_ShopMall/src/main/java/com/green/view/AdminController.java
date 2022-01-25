package com.green.view;

import java.io.File;
import java.io.IOException;
import java.nio.channels.IllegalSelectorException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.admin.AdminService;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.dto.SalesQuantity;
import com.green.biz.dto.WorkerVO;
import com.green.biz.member.MemberService;
import com.green.biz.order.OrderService;
import com.green.biz.product.ProductService;
import com.green.biz.qna.QnaService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("adminUser") // 다른 세션에서도 사용하기 위한 선언
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private QnaService qnaService;

	/*
	 * 관리자 로그인 폼 구현
	 */

	@GetMapping(value = "/admin_login_form") // Get 방식의 amdmin_login_form action 요청
	public String adminLoginView() {
		return "admin/main"; // admin 밑 main.jsp 실행
	}

	/*
	 * 관리자 로그인 (@RequestParam(value="workerId")String workerId = name="workerId"
	 * main.jsp 에서 설정한 name값을 받아온다 (* VO값에는 id로 설정) 사용자가 입력한 id값 pwd값을 입력받는다
	 */

	@PostMapping(value = "/admin_login") // Post 방식의 admin_login action 요청
	public String adminLogin(@RequestParam(value = "workerId") String workerId,
			@RequestParam(value = "workerPwd") String workerPwd, Model model) {

		WorkerVO vo = new WorkerVO();

		vo.setId(workerId); // DB 아이디
		vo.setPwd(workerPwd); // DB 비밀번호

		int result = adminService.workerCheck(vo); // 아이디 있으면 1 / 없으면 0 (int 형으로 변수 선언)

		/*
		 * 정상 로그인 - 상품 목록화면 이동 / 비정상 로그인 - 메세지 설정하고 로그인 페이지 이동
		 */

		if (result == 1) { // 정상 로그인

			WorkerVO adminUser = adminService.getEmployee(workerId); // 관리자 정보를 변수에 담는다(다른 페이지 에서도 사용)
			model.addAttribute("adminUser", adminUser);

			return "redirect:admin_product_list"; // 관리자 상품관리 페이지로 이동

		} else { // 비정상 로그인
			if (result == 0) {
				model.addAttribute("message", "비밀번호를 확인하세요"); // 화면에 전송
			} else {
				model.addAttribute("message", "아이디를 확인하세요"); // 화면에 전송
			}
			return "admin/main";
		}
	}

	/*
	 * 관리자 계정 로그아웃 처리
	 */
	@GetMapping(value = "/admin_logout")
	public String adminLogout(SessionStatus status) {

		status.setComplete();

		return "admin/main";
	}

	/*
	 * 관리자 상품리스트 조회
	 */
/*
	@GetMapping(value = "/admin_product_list")
	public String adminProductList(HttpSession session, Model model) {

		// 관리자 로그인 화면
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) { // 로그인이 안되어 있을경우
			return "admin/main";
		} else {

			// 상품목록 조회 - 상품목록 조회 값을 리스트 형식으로 출력
			List<ProductVO> prodList = productService.listProduct("");

			model.addAttribute("productList", prodList); // ${productList} 속성값에 담고 화면에 호출한다

			return "admin/product/productList"; // 상품 리스트 화면으로 전송
		}
	}
*/
	/*
	 * 상품 등록 페이지 표시
	 */
	@PostMapping(value = "/admin_product_write_form") // 상품 등록을 누르면 js -> action을 넘겨준다 -> admin_product_write_form
	public String adminProductWriteView(Model model) {

		// 상품 등록 페이지로 이동하면 상품분류 항목 배열에 담는다
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		model.addAttribute("kindList", kindList); // 배열에 담은 항목을 화면에 출력한다

		return "admin/product/productWrite"; // 상품등록 페이지로 전송

	}

	/*
	 * 상품 등록 처리
	 * 
	 * @RequestParam(value=""): 이미지 파일을 입력받는다 / VO의 image는 이미지 파일 이름
	 */
	@PostMapping(value = "/admin_product_write") // 상품 등록시 이미지 파일을 등록하는 action 요청
	public String adminProductWrite(@RequestParam(value = "product_image") MultipartFile uploadFile, ProductVO vo,
			HttpSession session) {

		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			String fileName = ""; // 파일경로 저장하는 변수 선언

			if (!uploadFile.isEmpty()) { // 첨부 파일이 비어있지 않으면(이미지 파일 읽어옴)

				fileName = uploadFile.getOriginalFilename();

				// vo 객체에 이미지 파일 저장
				vo.setImage(fileName);

				// 이미지 파일의 실제 저장경로 구하기
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/"); // 프로젝트의
																													// 실제
																													// 경로

				System.out.println("이미지 경로: " + image_path);

				try {
					// 이미지 파일을 위의 경로로 이동
					File dest = new File(image_path + fileName);
					uploadFile.transferTo(dest);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			}
		}

		productService.insertProduct(vo); // 이미지 파일 등록

		return "redirect:admin_product_list"; // 관리자 상품 리스트 action 전송
	}

	/*
	 * 상품 상세 정보 출력
	 */

	@RequestMapping(value = "/admin_product_detail") // #prod_form -> js (admin_product_detail action 실행)
	public String adminProductDetail(ProductVO vo, Model model) {

		String[] kindList = { "", "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);

		// 상품 종류 설정
		int index = Integer.parseInt(product.getKind()); // 상품 종류 숫자로 변환
		model.addAttribute("kind", kindList[index]);

		return "admin/product/productDetail";
	}

	/*
	 * 상품 수정화면 출력
	 */

	@PostMapping(value = "/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {

		String[] kindList = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product); // 화면에 전달할 상품상세정보
		model.addAttribute("kindList", kindList); // 상품 종류 화면에 전달

		return "admin/product/productUpdate";
	}

	/*
	 * 상품정보 수정
	 */
	@PostMapping(value = "/admin_product_update")
	public String adminProductUpdate(@RequestParam(value = "product_image") MultipartFile uploadFile,
			@RequestParam(value = "nonmakeImg") String origImage, ProductVO vo, HttpSession session) {

		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			String fileName = "";

			// 이미지 파일을 수정시 설정
			if (!uploadFile.isEmpty()) { // 첨부 파일이 비어있지 않으면(이미지 파일 읽어옴)
				fileName = uploadFile.getOriginalFilename();

				// vo 객체에 이미지 파일 저장
				vo.setImage(fileName);

				// 이미지 파일의 실제 저장경로 구하기
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/"); // 프로젝트의
																													// 실제
																													// 경로
				System.out.println("이미지 경로: " + image_path);

				try {
					// 이미지 파일을 위의 경로로 이동
					File dest = new File(image_path + fileName);
					uploadFile.transferTo(dest);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			} else {
				// 기존 이미지로 image 필드 설정
				vo.setImage(origImage); // 이미지 수정사항이 없을경우 기존 이미지 대체
			}

			// 베스트 상품, 신상품을 체크하지 않으면 값이 null로 들어옴
			if (vo.getUseyn() == null) {
				vo.setUseyn("n");
			}
			if (vo.getBestyn() == null) {
				vo.setBestyn("n");
			}
			productService.updateProduct(vo);

			return "redirect:admin_product_list";
		}
	}

	/*
	 * 주문목록 조회 요청 처리
	 */
	@RequestMapping(value = "/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String key, Model model) { // key 갑을
																											// 입력받아 실행한다
																											// key, null
																											// 값

		List<OrderVO> orderList = orderService.listOrder(key);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";
	}

	/*
	 * 주문완료 처리(입금확인) 입력 파라미터: 입금확인한 result 필드의 상세주문번호(odseq) 배열이 전달됨
	 */
	@RequestMapping(value = "/admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result") int[] odseq) {

		for (int i = 0; i < odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}

		return "redirect:admin_order_list";
	}

	/*
	 * 회원목록 조회 처리
	 */

	@RequestMapping(value = "/admin_member_list")
	public String adminMemberList(@RequestParam(value = "key", defaultValue = "") String name, Model model) {

		List<MemberVO> listMember = memberService.listMember(name);

		model.addAttribute("memberList", listMember);

		return "admin/member/memberList";
	}

	/*
	 * 게시판 관리(QnA 목록조회 처리)
	 */
	@RequestMapping(value = "/admin_qna_list")
	public String adminQnaList(Model model) {

		// QnA 목록을 테이블에서 조회
		List<QnaVO> qnaList = qnaService.listAllQna();

		// 조회 결과를 model 객체에 저장
		model.addAttribute("qnaList", qnaList);

		// QnA 화면 호출
		return "admin/qna/qnaList";
	}

	/*
	 * QnA 게시글 상세보기(관리자)
	 */
	@PostMapping(value = "/admin_qna_detail")
	public String adminQnaDetail(QnaVO vo, Model model) {

		// 게시글 일련번호를 조건으로 게시글 상세 조회
		QnaVO qna = qnaService.getQna(vo.getQseq());

		// 조회 결과를 model 객체에 저장
		model.addAttribute("qnaVO", qna);

		// 게시글 상세화면 호출
		return "admin/qna/qnaDetail";
	}

	/*
	 * QnA 관리자 답변 요청 처리
	 */
	@PostMapping(value = "/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {

		// QnA 서비스의 Update 호출
		qnaService.updateQna(vo);

		// QnA 게시글 목록 호출
		return "redirect:admin_qna_list";
	}
	
	/*
	 * 상품별 판매 실적 화면 출력
	 */
	@RequestMapping(value="/admin_sales_record_form")
	public String adminProductSalesChart() {
		return "admin/order/salesRecord";
	}
	
	/*
	 * 차트를 위한 상품별 판매 실적 조회(JSON 데이터 포맷 전송)
	 */
	@RequestMapping(value="/sales_record_chart",
					produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<SalesQuantity> salesRecordChart(){
		
		List<SalesQuantity> listSales = productService.getProductSales();
		
		for(SalesQuantity item:listSales) {
			System.out.println(item);
		}
		return listSales;
	}
	
	/*
	 *  페이지별 상품 목록 조회요청 처리
	 */
	
	@RequestMapping(value = "/admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String name, Criteria criteria,
			HttpSession session, Model model) {

		// 관리자 로그인 화면
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) { // 로그인이 안되어 있을경우
			return "admin/main";
		} else {

			// 상품목록 조회 - 상품목록 10개만 조회(전체 제품을 이름으로 리스트 에서 1-10번까지 출력)
			List<ProductVO> prodList = productService.getListWithPaging(criteria, name);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = productService.countProductList(name);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 상품품목 갯수 설정 및 페이지 정보 초기화

			model.addAttribute("productList", prodList); // ${productList} 속성값에 담고 화면에 호출한다
			model.addAttribute("productListSize", prodList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "admin/product/productList"; // 상품 리스트 화면으로 전송
		}
	}
	
	
	/*
	 *  페이지별  주문 목록 조회요청 처리
	 */
}
