<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.board.list.mainTitle"/></title>
</head>
<body>
	<div align="center">
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>
			<i style="color: blue">"${userName}"</i> <spring:message code="message.board.list.welcomeMsg"/> <a href="logout.do">log-out</a>
		</h3>

		<!-- 검색 파트 -->
		<form action="getBoardList.do" method="post">
			<table border="1" style="width: 700;">
				<tr>
					<td align="right">
					<select name="searchCondition">
						<c:forEach items="${conditionMap}" var="option">
							<option value="${option.value}">${option.key}</option>
						</c:forEach>
					</select> 
					<input type="text" name="searchKeyword" /> 
					<input type="submit" value='<spring:message code="message.board.list.search.condition.btn"/>'/></td>
				</tr>
			</table>
		</form>
		<!-- 검색 파트 종료 -->

		<!-- 게시글 목록 출력 -->
		<br>
		<table border="1" style="width: 700;">
			<tr>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>
				<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
			</tr>
			<!-- Java 코드를 통해 전달된 게시글 목록 데이터를 반복하여 읽어 출력 -->
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.seq}</td>
					<td><a href="getBoard.do?seq=${board.seq}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
					<td>${board.cnt}</td>
				</tr>
			</c:forEach>

		</table>
		<!-- 게시글 목록 출력 종료-->
		<br> <a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</div>
</body>
</html>