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
	 * 서블릿 객체가 생성된 후에 자동으로 실행
	 */
	@Override
	public void init() throws ServletException {

		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();

		viewResolver.setPrefix("./"); // 현재디렉토리: webapp
		viewResolver.setSuffix(".jsp"); // webapp 밑에 jsp 파일 생성
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

		process(request, response); // GET 방식 요청, POST 방식 요청 모두 process() 메소드를 통해 처리
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		process(request, response); // GET 방식 요청, POST 방식 요청 모두 process() 메소드를 통해 처리
	}

	/*
	 * 사용자가 요청한 URI를 해석하여 업무로직을 호출한다.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// (1). 클라이언트 요청 URI 정보를 읽어온다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		// (2) HandlerMapping 에서 path(업무)에 해당하는 Controller를 검색
		Controller controller = handlerMapping.getController(path);

		// (3) 위에서 검색한 Controller를 실행
		String viewName = controller.handleRequest(request, response);

		// (4) ViewResolver를 통해 view를 완성
		String view = "";
		if (viewName.contains(".do")) { // 파일.do 포함하면 그대로 실행
			view = viewName;
		} else {
			view = viewResolver.getView(viewName); // jsp 파일이름, 경로를viewResolver를 통해 조립을 시켜준다
		}

		// View화면을 응답한다
		response.sendRedirect(view);
	}

}
