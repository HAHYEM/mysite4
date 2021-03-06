<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">

		<!--header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<!--navigation-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form class="board-form" method="get" action="${pageContext.request.contextPath }/board/modify">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${boardVo.title}"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content">${boardVo.content}</textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/view&no=${boardVo.no}">취소</a> 
						<input type="submit" value="수정">
						<input type = "hidden" name="modify" value= "${boardVo.no }">
					</div>
				</form>
			</div>
		</div>

		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>