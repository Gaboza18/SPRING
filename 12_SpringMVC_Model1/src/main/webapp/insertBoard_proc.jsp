<%@page import="com.green.biz.dto.BoardDAO"%>
<%@page import="com.green.biz.dto.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 1. 사용자 입력 정보 추출
	request.setCharacterEncoding("UTF-8"); // 요청한 입력값이 한글일때
	String title = request.getParameter("title"); // 사용자가 입력할 제목 매개변수
	String writer = request.getParameter("writer"); // 사용자가 입력할 사용자 매개변수
	String content = request.getParameter("content"); // 사용자가 입력할 내용 매개변수
	
	// 2. DB 연동 처리
	BoardVO vo = new BoardVO(); // BoardVO 클래스 객체 선언
	vo.setTitle(title); // BoardVO 클래스의 제목값 setter 형식으로 저장
	vo.setWriter(writer); // BoardVO 클래스의 사용자 setter 형식으로 저장
	vo.setContent(content); // BoardVO 클래스의 게시판 내용 setter 형식으로 저장
	
	BoardDAO boardDAO = new BoardDAO(); // BoardDAO 클래스 객체 생성
	boardDAO.insertBoard(vo); // BoardDAO 객체의 insertBoard() 메소드에 실행후 저장한다
	
	// 3. 화면 네비게이션
	response.sendRedirect("getBoardList.jsp"); // 글쓰기 등록이 완료되면 게시물 목록화면으로 보낸다
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