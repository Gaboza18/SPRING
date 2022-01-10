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

		// Ŭ���̾�Ʈ ��û URI ������ �о�´�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		// path�� ���뿡 ���� ����ó�� ���� �б�
		if (path.equals("/login.do")) { // login.do ����� ó��
			System.out.println("�α��� ó��");

			// 1. ����� �Է� ���� ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			// 2. DB ����ó��
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);

			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo); // ����� ������ �˻��Ѵ�.

			// 3.ȭ�� �׺���̼�
			if (user != null) {
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("�Խñ� �����ȸ ó��");
			BoardVO vo = new BoardVO();
			BoardDAO boardDao = new BoardDAO();
			List<BoardVO> boardList = boardDao.getBoardList();

			// �˻� ����� ���ǿ� �����ϰ� ���ȭ�� ȣ��
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			response.sendRedirect("getBoardList.jsp");

		} else if (path.equals("/getBoard.do")) {
			System.out.println("�Խñ� ����ȸ ó��");

			// �Խñ� ��ȣ �Է°� ����
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDao = new BoardDAO();
			BoardVO board = boardDao.getBoard(vo);

			// ����ȭ�� ����
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");

		} else if (path.equals("/insertBoard.do")) {
			System.out.println("�Խñ� ���ó��");

			// 1. ����� �Է� ���� ����
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);

			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/updateBoard.do")) {
			System.out.println("�Խñ� ����ó��");

			request.setCharacterEncoding("UTF-8");

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();

			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDao = new BoardDAO();
			boardDao.updateBoard(vo);

			// �����۾��� �Խù� ����Ʈ ȭ�� ���
			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/deleteBoard.do")) {
			System.out.println("�Խñ� ����ó��");

			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDao = new BoardDAO();
			boardDao.deleteBoard(vo);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ó��");

			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect("login.jsp");
		}
	}

}
