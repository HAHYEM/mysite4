<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>GuestBookList</title>
</head>
<body>

	<div id="container">
		
		<!--header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<!--navigation-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form method="post" action="${pageContext.request.contextPath }/gb/insert">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " />
								<input type = "hidden" name="a" value="insert"></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
							<c:forEach items="${gList }" var="guestbookVo"> <!-- controller에서 쓰는거 -->
								<table width=510 border=1>
									<tr>
										<td>${guestbookVo.no}</td>
										<td>${guestbookVo.name}</td>
										<td>${guestbookVo.regDate}</td>
										<td><a href="${pageContext.request.contextPath }/gb/deleteform?no=${guestbookVo.no}">삭제</a></td>
									</tr>
									<tr>
										<td colspan=4>${guestbookVo.content}</td>
									</tr>
								</table>
		   					 <br/>
							</c:forEach>
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div> <!-- /container -->

</body>
</html>