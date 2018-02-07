<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!--header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<!--navigation-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/list" method="get">
					<input type="text" id="kwd" name="searchValue" value="">
					<input type="submit" value="찾기">
				</form>

				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					<c:forEach items="${bList}" var="b" varStatus="status">
						<tr>
							<td>${b.no}</td>
							<td><a href="${pageContext.request.contextPath }/board/view?no=${b.no}">${b.title}</a></td>
							<td>${b.userName}</td>
							<td>${b.hit}</td>
							<td>${b.regDate}</td>
							<td><c:if test="${b.userNo == authUser.no}">
								<a href="${pageContext.request.contextPath }/board/delete?no=${b.no}&userNo=${b.userNo}" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				
				</table>
				<div class="pager">
					<ul>
						<c:if test="${currentPage-1>0}">
						<li><a href="/board/list&page=${currentPage-1}">◀</a></li>
						</c:if>
					
						<c:forEach var="i" begin="1" end="${lastPage}" step="1">
								<c:choose>
									<c:when test="${i == param.currentPage }">
										<li class="selected"><a href="board/list&page=${currentPage}">${i}</a></li>
									</c:when>
									 <c:otherwise>
									 <c:if test="${i <= currentPage +2 && i >= currentPage -2}">
										<a href="board/list&page=${i}">${i}</a>
									 </c:if>
									 </c:otherwise> 
								</c:choose>
						</c:forEach>
				
						<c:if test="${currentPage !=lastPage }">
						<li><a href="board/list&page=${currentPage+1}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="${pageContext.request.contextPath }/board/writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>			
	
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div>
</body>
</html>