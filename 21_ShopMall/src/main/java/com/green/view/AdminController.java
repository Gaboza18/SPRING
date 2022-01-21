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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.admin.AdminService;
import com.green.biz.dto.OrderVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.WorkerVO;
import com.green.biz.order.OrderService;
import com.green.biz.product.ProductService;

@Controller
@SessionAttributes("adminUser") // 다른 세션에서도 사용하기 위한 선언
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	/*
	 * 관리자 로그인 폼 구현
	 */

	@GetMapping(value = "/admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}

	/*
	 * 관리자 로그인 (@RequestParam(value="workerId")String workerId = name="workerId"
	 * main.jsp 에서 설정한 name값을 받아온다 (* VO값에는 id로 설정)
	 */

	@PostMapping(value = "/admin_login")
	public String adminLogin(@RequestParam(value = "workerId") String workerId,
			@RequestParam(value = "workerPwd") String workerPwd, Model model) {

		WorkerVO vo = new WorkerVO();

		vo.setId(workerId); // 아이디
		vo.setPwd(workerPwd); // 비밀번호

		int result = adminService.workerCheck(vo); // 아이디 있으면 1 / 없으면 0 이기 떄문에 int 형으로 변수 선언

		/*
		 * 정상 로그인 - 상품 목록화면 이동 / 비정상 로그인 - 메세지 설정하고 로그인 페이지 이동
		 */

		if (result == 1) { // 정상 로그인

			WorkerVO adminUser = adminService.getEmployee(workerId); // 관리자 정보를 변수에 담는다(다른 페이지 에서도 사용)
			model.addAttribute("adminUser", adminUser);

			return "redirect:admin_product_list";

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
	 * 관리자 상품리스트 조회
	 */

	@GetMapping(value = "/admin_product_list")
	public String adminProductList(HttpSession session, Model model) {

		// 관리자 로그인 화면
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			// 상품목록 조회
			List<ProductVO> prodList = productService.listProduct("");

			model.addAttribute("productList", prodList);

			return "admin/product/productList";
		}
	}

	/*
	 * 상품 등록 페이지 표시
	 */
	@PostMapping(value = "/admin_product_write_form")
	public String adminProductWriteView(Model model) {

		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" }; // 상품 분류

		model.addAttribute("kindList", kindList);

		return "admin/product/productWrite";

	}

	/*
	 * 상품 등록 처리
	 * 
	 * @RequestParam(value=""): 이미지 파일을 입력받는다 / VO의 image는 이미지 파일 이름
	 */
	@PostMapping(value = "/admin_product_write")
	public String adminProductWrite(@RequestParam(value = "product_image") MultipartFile uploadFile, ProductVO vo,
			HttpSession session) {

		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			String fileName = "";

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

		productService.insertProduct(vo);

		return "redirect:admin_product_list";
	}

	/*
	 * 상품 상세 정보 출력
	 */

	@PostMapping(value = "/admin_product_detail")
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
		model.addAttribute("kindList", kindList);

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
	@GetMapping(value = "/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String key, Model model) { // key 갑을 입력받아 실행한다 key, null 값

		List<OrderVO> orderList = orderService.listOrder(key);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";
	}
}
