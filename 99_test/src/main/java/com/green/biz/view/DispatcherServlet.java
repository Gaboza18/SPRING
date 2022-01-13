package com.green.biz.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		if (path.equals("/listBook.do")) {

			System.out.println("도서목록 출력처리");

			BookVO vo = new BookVO();
			BookDAO bookDAO = new BookDAO();
			List<BookVO> bookList = bookDAO.getBookList();

			HttpSession session = request.getSession();
			session.setAttribute("bookList", bookList);
			response.sendRedirect("listBook.jsp");

		} else if (path.equals("/insertBook.do")) {

			System.out.println("도서정보 추가하기");

			request.setCharacterEncoding("UTF-8");
			
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			String publisher = request.getParameter("publisher");
			String image_url = request.getParameter("Image_url");
			String notes = request.getParameter("notes");
			String is_recommended = request.getParameter("is_recommended");
			String rating = request.getParameter("rating");

			BookVO vo = new BookVO();
			vo.setName(name);
			vo.setAuthor(author);
			vo.setCategory(category);
			vo.setPrice(Integer.parseInt(price));
			vo.setPublisher(publisher);
			vo.setImage_url(image_url);
			vo.setNotes(notes);
			vo.setIs_recommended(is_recommended);
			vo.setRating(Integer.parseInt(rating));

		} else if (path.equals("/detailBook.do")) {

			System.out.println("도서목록 상세보기");

			String item_id = request.getParameter("item_id");

			BookVO vo = new BookVO();
			vo.setItem_id(Integer.parseInt(item_id));

			BookDAO bookDao = new BookDAO();
			BookVO book = bookDao.getBook(vo);

			HttpSession session = request.getSession();
			session.setAttribute("book", book);
			response.sendRedirect("detailBook.jsp");

		}

	}

}
