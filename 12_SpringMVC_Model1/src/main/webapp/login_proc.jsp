<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.green.biz.dto.UserDAO"%>
<%@ page import="com.green.biz.dto.UserVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 인증 처리</title>
</head>
<body>
	<%
	// 1. 사용자가 입력한(요청)한 아이디/비밀번호 값을 받아온다

	String id = request.getParameter("id"); // 요청한 아이디 값을 String 타입의 아이디 값으로 저장한다 
	String password = request.getParameter("password"); // 요청한 비밀번호 값을 String 타입의 비밀번호 값으로 저장한다

	// 2. DB 연동처리(DB 테이블에 있는 사용자 아이디 비밀번호를 찾는다)

	UserVO vo = new UserVO(); // UserVO 클래스의 객체를 생성한다
	vo.setId(id); // UserVO 클래스의 객체의 아이디 값을 setter로 가져온다
	vo.setPassword(password); // UserVO 클래스의 객체의 비밀번호 값을 setter로 가져온다

	UserDAO userDAO = new UserDAO(); // UserDAO 클래스의 객체 생성한다
	UserVO user = userDAO.getUser(vo); // UserVO 클래스의 객체 생성 = UserDAO 객체의 사용자 정보를 Getter 형식으로 가져온후 저장한다

	// 3.화면 네비게이션(user = client(사용자)가 있는지 확인한후 게시판 화면으로 넘길지 로그인 화면을 유지할지 정하는 로직구현)
	if (user != null) { // 사용자의 정보가 null이 아니면
		response.sendRedirect("getBoardList.jsp"); // 응답해서 보낸다 -> 게시판.jsp 파일로 보낸다
	} else {
		response.sendRedirect("login.jsp"); // 사용자의 정보가 null이거나, 사용자의 정보가 틀리면 로그인.jsp 파일로 보낸다
	}
	%>
</body>
</html>