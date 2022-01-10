<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.green.biz.dto.BoardVO"%>
<%@ page import="com.green.biz.dto.BoardDAO"%>

<%
	request.setCharacterEncoding("UTF-8"); // 사용자가 입력한 값을 한글처리 하기 위한 선언

	String title = request.getParameter("title"); // 사용자가 수정할 제목값을 받아온다
	String writer = request.getParameter("writer"); // 사용자가 수정할 작성자값을 받아온다
	String content = request.getParameter("content"); // 사용자가 수정할 내용값을 받아온다
	String seq = request.getParameter("seq"); // 사용자가 요청한 시퀀스값을 받아온다
	
	BoardVO vo = new BoardVO();
	
	// 사용자가 입력한 수정할 값을 DB 각 칼럼에 저장한다
	
	vo.setTitle(title); 
	vo.setWriter(writer);
	vo.setContent(content);
	vo.setSeq(Integer.parseInt(seq));
	
	BoardDAO boardDao = new BoardDAO();
	boardDao.updateBoard(vo);
	
	response.sendRedirect("getBoardList.jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>