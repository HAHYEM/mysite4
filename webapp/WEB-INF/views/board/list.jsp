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
					<input type="hidden" name="crtPage" value="${bmap.crtPage}">
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
							
					<c:forEach items="${bmap.boardList}" var="b" varStatus="status">
						<tr>
							<td>${b.no}</td>
							<td><a href="${pageContext.request.contextPath }/board/view?no=${b.no}&crtPage=${bmp.crtPage}">${b.title}</a></td>
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
					
					<c:choose>
						<c:when test="${bmap.searchValue != null}">
							<c:if test="${bmap.prev}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${bmap.startPageBtnNo-1}&searchValue=${bmap.searchValue}">◀</a></li>
							</c:if>
							<c:forEach var="idx" begin="${bmap.startPageBtnNo }" end="${bmap.endPageBtnNo}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${idx}&searchValue=${bmap.searchValue}">${idx}</a></li>
							</c:forEach>
							<c:if test="${bmap.next}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${bmap.endPageBtnNo+1}&searchValue=${bmap.searchValue}">▶</a></li>
							</c:if>
						</c:when>
						
						<c:when test="${bmap.searchValue == null}">
							<c:if test="${bmap.prev}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${startPageBtnNo-1}">◀</a></li>
							</c:if>
							<c:forEach var="idx" begin="${bmap.startPageBtnNo }" end="${bmap.endPageBtnNo}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${idx}">${idx}</a></li>
							</c:forEach>
							<c:if test="${bmap.next}">
								<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${bmap.endPageBtnNo+1}">▶</a></li>
							</c:if>
						</c:when>
						
					</c:choose>
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