package com.green.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;
import com.green.biz.dto.UserDAO;
import com.green.biz.dto.UserVO;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;

	/*
	 * ���� ��ü�� ������ �Ŀ� �ڵ����� ����
	 */
	@Override
	public void init() throws ServletException {

		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();

		viewResolver.setPrefix("./"); // ������丮: webapp
		viewResolver.setSuffix(".jsp"); // webapp �ؿ� jsp ���� ����
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response); // GET ��� ��û, POST ��� ��û ��� process() �޼ҵ带 ���� ó��
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		process(request, response); // GET ��� ��û, POST ��� ��û ��� process() �޼ҵ带 ���� ó��
	}

	/*
	 * ����ڰ� ��û�� URI�� �ؼ��Ͽ� ���������� ȣ���Ѵ�.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// (1). Ŭ���̾�Ʈ ��û URI ������ �о�´�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		// (2) HandlerMapping ���� path(����)�� �ش��ϴ� Controller�� �˻�
		Controller controller = handlerMapping.getController(path);

		// (3) ������ �˻��� Controller�� ����
		String viewName = controller.handleRequest(request, response);

		// (4) ViewResolver�� ���� view�� �ϼ�
		String view = "";
		if (viewName.contains(".do")) { // ����.do �����ϸ� �״�� ����
			view = viewName;
		} else {
			view = viewResolver.getView(viewName); // jsp �����̸�, ��θ�viewResolver�� ���� ������ �����ش�
		}

		// Viewȭ���� �����Ѵ�
		response.sendRedirect(view);
	}

}
