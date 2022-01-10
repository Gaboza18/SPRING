<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.green.biz.dto.BoardDAO"%>
<%@ page import="com.green.biz.dto.BoardVO"%>

<%
BoardVO vo = new BoardVO(); // BoardVO 타입의 객체 선언
BoardDAO boardDao = new BoardDAO(); // BoardDAO 타입의 객체 선언
List<BoardVO> boardList = boardDao.getBoardList(); // List<BoardVO> 타입의 객체 선언  

// 응답 화면 구성
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<div align="center">
		<h1>게시글 목록</h1>
		<h3>
			테스트님 환영합니다...<a href="login_proc.jsp">log-out</a>
		</h3>

		<!-- 검색 파트 -->
		<form action="getBoardList.jsp" method="post">
			<table border="1" style="width: 700;">
				<tr>
					<td align="right"><select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
					</select> <input type="text" name="searchKeyword" /> <input type="submit"
						value="검색" /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 파트 종료 -->

		<!-- 게시글 목록 출력 -->
		<br>
		<table border="1" style="width: 700;">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
			<!-- Java 코드를 통해 전달된 게시글 목록 데이터를 반복하여 읽어 출력 -->
			<%
			for (BoardVO board : boardList) {
			%>
			<tr>
				<td><%=board.getSeq()%></td>
				<td><a href="getBoard.jsp?seq=<%=board.getSeq()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
			<%
			}
			%>
		</table>
		<!-- 게시글 목록 출력 종료-->
		<br> <a href="insertBoard.jsp">새글 등록</a>
	</div>
</body>
</html>