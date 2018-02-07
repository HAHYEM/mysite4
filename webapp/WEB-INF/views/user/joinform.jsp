<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	
	<title>joinform</title>
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
	
					<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="">
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						<input type="button" id=btnEmailCheck value="id 중복체크">	
						<div id="checkMessage"></div>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>

						</fieldset>
						<input type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div> <!-- /container -->

</body>
<script type="text/javascript">
	$("#btnEmailCheck").on("click",function(){
		var email = $("#email").val();
		var password = "1111";
		var userVo = {
				email : email
		}
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/user/api/emailcheck",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(userVo),
			
			//받을 때 데이터 타입
			dataType : "json",
			success : function(result){
				console.log(result);
				if(result == true){
					$("#checkMessage").text("사용할 수 있는 이메일입니다.")
					$("#checkMessage").css("color", "blue");
				}else{
					$("#checkMessage").text("사용중인 이메일입니다.")
					$("#checkMessage").css("color", "red");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

</script>


</html>