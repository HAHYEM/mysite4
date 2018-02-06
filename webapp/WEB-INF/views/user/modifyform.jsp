<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<!--header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<!--navigation-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="modifyForm" method="post" action="${pageContext.request.contextPath }/user/modify">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${user.name}" />
	
						<label class="block-label" for="email">이메일</label>
						<strong>${user.email}</strong>
						<input type="hidden" name="email" value="${user.email}" />
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="${user.password}" />
						
			<legend>성별</legend>
				<c:choose>
					<c:when test="${'male' == user.gender}">
						<fieldset>
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</fieldset>
					</c:when>
					<c:otherwise>
						<fieldset>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked" >
							<label>남</label> <input type="radio" name="gender" value="male" >
						</fieldset>
					</c:otherwise>
				</c:choose>
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div> <!-- /container -->

</body>
</html>
