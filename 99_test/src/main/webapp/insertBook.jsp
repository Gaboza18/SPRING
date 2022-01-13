<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서정보 입력</title>
</head>
<body>
	<div align="center">
		<h1>도서 정보 입력</h1>
		<hr>
		<form action="insertBoard.do" method="post">
			<table border="1">
				<tr>
					<td bgcolor="orange" width="70">도서 제목</td>
					<td align="left"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="orange">저자</td>
					<td align="left"><input type="text" name="writer" size="10" /></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">카테고리</td>
					<td align="left"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">가격</td>
					<td align="left"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">출판사</td>
					<td align="left"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">이미지 URL</td>
					<td align="left"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="orange">요약정보</td>
					<td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="추가" /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getBoardList.jsp">게시글 목록 가기</a>
	</div>
</body>
</html>
