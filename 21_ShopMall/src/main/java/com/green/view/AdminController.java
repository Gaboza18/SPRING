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
@SessionAttributes("adminUser") // �ٸ� ���ǿ����� ����ϱ� ���� ����
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	/*
	 * ������ �α��� �� ����
	 */

	@GetMapping(value = "/admin_login_form") // Get ����� amdmin_login_form action ��û
	public String adminLoginView() {
		return "admin/main"; // admin �� main.jsp ����
	}

	/*
	 * ������ �α��� (@RequestParam(value="workerId")String workerId = name="workerId"
	 * main.jsp ���� ������ name���� �޾ƿ´� (* VO������ id�� ����)
	 * ����ڰ� �Է��� id�� pwd���� �Է¹޴´�
	 */

	@PostMapping(value = "/admin_login") // Post ����� admin_login action ��û
	public String adminLogin(@RequestParam(value = "workerId") String workerId,
			@RequestParam(value = "workerPwd") String workerPwd, Model model) {

		WorkerVO vo = new WorkerVO();

		vo.setId(workerId); // DB ���̵�
		vo.setPwd(workerPwd); // DB ��й�ȣ

		int result = adminService.workerCheck(vo); // ���̵� ������ 1 / ������ 0  (int ������ ���� ����)

		/*
		 * ���� �α��� - ��ǰ ���ȭ�� �̵� / ������ �α��� - �޼��� �����ϰ� �α��� ������ �̵�
		 */

		if (result == 1) { // ���� �α���

			WorkerVO adminUser = adminService.getEmployee(workerId); // ������ ������ ������ ��´�(�ٸ� ������ ������ ���)
			model.addAttribute("adminUser", adminUser);

			return "redirect:admin_product_list"; // ������ ��ǰ���� �������� �̵�

		} else { // ������ �α���
			if (result == 0) {
				model.addAttribute("message", "��й�ȣ�� Ȯ���ϼ���"); // ȭ�鿡 ����
			} else {
				model.addAttribute("message", "���̵� Ȯ���ϼ���"); // ȭ�鿡 ����
			}
			return "admin/main";
		}
	}

	/*
	 * ������ ��ǰ����Ʈ ��ȸ
	 */

	@GetMapping(value = "/admin_product_list")
	public String adminProductList(HttpSession session, Model model) {

		// ������ �α��� ȭ��
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) { // �α����� �ȵǾ� �������
			return "admin/main";
		} else {

			// ��ǰ��� ��ȸ - ��ǰ��� ��ȸ ���� ����Ʈ �������� ���
			List<ProductVO> prodList = productService.listProduct("");

			model.addAttribute("productList", prodList); // ${productList} �Ӽ����� ��� ȭ�鿡 ȣ���Ѵ�

			return "admin/product/productList"; // ��ǰ ����Ʈ ȭ������ ����
		}
	}

	/*
	 * ��ǰ ��� ������ ǥ��
	 */
	@PostMapping(value = "/admin_product_write_form") // ��ǰ ����� ������ js -> action�� �Ѱ��ش� -> admin_product_write_form
	public String adminProductWriteView(Model model) {
		
		// ��ǰ ��� �������� �̵��ϸ� ��ǰ�з�  �׸� �迭�� ��´�
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		model.addAttribute("kindList", kindList); // �迭�� ���� �׸��� ȭ�鿡 ����Ѵ�

		return "admin/product/productWrite"; // ��ǰ��� �������� ���� 

	}

	/*
	 * ��ǰ ��� ó��
	 * 
	 * @RequestParam(value=""): �̹��� ������ �Է¹޴´� / VO�� image�� �̹��� ���� �̸�
	 */
	@PostMapping(value = "/admin_product_write") // ��ǰ ��Ͻ� �̹��� ������ ����ϴ� action ��û
	public String adminProductWrite(@RequestParam(value = "product_image") MultipartFile uploadFile, ProductVO vo,
			HttpSession session) {

		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			String fileName = ""; // ���ϰ�� �����ϴ� ���� ����

			if (!uploadFile.isEmpty()) { // ÷�� ������ ������� ������(�̹��� ���� �о��)
				
				fileName = uploadFile.getOriginalFilename();

				// vo ��ü�� �̹��� ���� ����
				vo.setImage(fileName);

				// �̹��� ������ ���� ������ ���ϱ�
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/"); // ������Ʈ�� ���� ���
																													
																													
				System.out.println("�̹��� ���: " + image_path);

				try {
					// �̹��� ������ ���� ��η� �̵�
					File dest = new File(image_path + fileName);
					uploadFile.transferTo(dest);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			}
		}

		productService.insertProduct(vo); // �̹��� ���� ���

		return "redirect:admin_product_list"; // ������ ��ǰ ����Ʈ action ����
	}

	/*
	 * ��ǰ �� ���� ���
	 */

	@PostMapping(value = "/admin_product_detail") // #prod_form -> js (admin_product_detail action ����)
	public String adminProductDetail(ProductVO vo, Model model) {

		String[] kindList = { "", "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);

		// ��ǰ ���� ����
		int index = Integer.parseInt(product.getKind()); // ��ǰ ���� ���ڷ� ��ȯ
		model.addAttribute("kind", kindList[index]);

		return "admin/product/productDetail";
	}

	/*
	 * ��ǰ ����ȭ�� ���
	 */

	@PostMapping(value = "/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {

		String[] kindList = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sales" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product); // ȭ�鿡 ������ ��ǰ������
		model.addAttribute("kindList", kindList); // ��ǰ ���� ȭ�鿡 ����

		return "admin/product/productUpdate";
	}

	/*
	 * ��ǰ���� ����
	 */
	@PostMapping(value = "/admin_product_update")
	public String adminProductUpdate(@RequestParam(value = "product_image") MultipartFile uploadFile,
			@RequestParam(value = "nonmakeImg") String origImage, ProductVO vo, HttpSession session) {

		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {

			String fileName = "";

			// �̹��� ������ ������ ����
			if (!uploadFile.isEmpty()) { // ÷�� ������ ������� ������(�̹��� ���� �о��)
				fileName = uploadFile.getOriginalFilename();

				// vo ��ü�� �̹��� ���� ����
				vo.setImage(fileName);

				// �̹��� ������ ���� ������ ���ϱ�
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/"); // ������Ʈ��
																													// ����
																													// ���
				System.out.println("�̹��� ���: " + image_path);

				try {
					// �̹��� ������ ���� ��η� �̵�
					File dest = new File(image_path + fileName);
					uploadFile.transferTo(dest);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			} else {
				// ���� �̹����� image �ʵ� ����
				vo.setImage(origImage); // �̹��� ���������� ������� ���� �̹��� ��ü
			}

			// ����Ʈ ��ǰ, �Ż�ǰ�� üũ���� ������ ���� null�� ����
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
	 * �ֹ���� ��ȸ ��û ó��
	 */
	@GetMapping(value = "/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String key, Model model) { // key ���� �Է¹޾� �����Ѵ� key, null ��

		List<OrderVO> orderList = orderService.listOrder(key);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";
	}
}