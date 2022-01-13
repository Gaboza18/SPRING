<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.green.biz.dto.BoardVO" %>
<%@ page import="com.green.biz.dao.BoardDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1>게시글 상세</h1>
	<a href="logout.do">log-out</a>
	<hr>
	<form action="updateBoard.do" method="post">
		<input name="seq" type="hidden" value="${board.seq}"/>
		<table border="1">
			<tr>
				<th bgcolor="orange" width="150">제목</th>
				<td><input type="text" name="title" value="${board.title}"/></td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">작성자</th>
				<!-- <td><input type="text" name="writer" value="${board.writer}" readonly="readonly"/></td> -->
				<td>${board.writer}</td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">내용</th>
				<td>
					<textarea name="content" cols="40" rows="10">${board.content}</textarea>
				</td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">등록일</th>
				<td>${board.regDate}</td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">조회수</th>
				<td>${board.cnt}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="글 수정"/>
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;
	<a href="deleteBoard.do?seq=${board.seq}">글 삭제</a>&nbsp;&nbsp;
	<a href="getBoardList.do">글 목록</a>
</div>
</body>
</html>















