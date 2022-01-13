<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@ page import="com.green.biz.view.BookVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서목록</title>
</head>
<body>
	<div align="center">
		<h2>도서목록</h2>
		<br>
		<table border="1" style="width: 700;">
			<tr>
				<th bgcolor="green" width="100">도서제목</th>
				<th bgcolor="green" width="200">저자</th>
				<th bgcolor="green" width="150">가격</th>
				<th bgcolor="green" width="150">출판사</th>
				<th bgcolor="green" width="100">카테고리</th>
			</tr>
			
		<c:forEach var="book" items="${bookList}">
				<tr>
					<td>${book.name}</td>
					<td><a href="detailBook.do?item_id=${book.seq}">${board.title}</a></td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td>${book.publisher}</td>
					<td>${book.category}</td>
				</tr>
			</c:forEach>
		
		</table>
		<br>
		<a href="insertBook.jsp">도서추가</a>
	</div>

</body>
</html>