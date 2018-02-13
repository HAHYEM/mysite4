<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">



<title>Mysite</title>
</head>
<body>
	
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form method="post" action="${pageContext.request.contextPath }/fileupload/upload" enctype="multipart/form-data"><!-- 화면뿌려주는 controller -->
						<table>
							<tr>
								<td>첨부파일</td>
								<td><input type="file" name="file"></td>	<!-- html에서  file이라고 쓰면 파일찾기 가 나옴 -->
								<td><input type="submit" value="파일업로드"></td>
							</tr>
						</table>
					</form>
						<table>
							<c:forEach items="${gList }" var="gList">
							<tr>
								<td><img src="${pageContext.request.contextPath }/${url}${gList.saveName}" style="width:150px"></td>
								<td><a href="${pageContext.request.contextPath }/fileupload/delete?no=${gList.no}">삭제</a></td>
							</tr>
							</c:forEach>
						</table>
					
					
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
	
	
	
</body>
</html>		
		
