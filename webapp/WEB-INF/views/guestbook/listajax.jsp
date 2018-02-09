<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
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
					<%-- 
					<form method="get" action="${pageContext.request.contextPath }/gb/list">
					 --%>	
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" id="name"/></td>
								<td>비밀번호</td><td><input type="password" name="password" id="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content" class="textContent"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" id="btnInsert" VALUE=" 확인 " />
								</td>
							</tr>
						</table>
						<input id = "btnDel" type="button" value="삭제예제버튼"></td>
					
					<!-- </form> -->
					<ul id = "listArea">
					</ul>
					
					<input id="btnNext" type="button" value=" 다음글 5개 가져오기" />
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div> <!-- /container -->

</body>

<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="hidden" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
<script type="text/javascript">
	var page = 1;
	$(document).ready(function() { //기존방식과 다르게 하기 위해 ready를 쓰는 것이다.
		fetchList();
	});
	/* 다음 글 버튼 클릭시 */
	$("#btnNext").on("click", function() {
		page = page + 1;
		console.log(page);
		fetchList();
	});
	/* scroll이 최하단에 왔을 때 계속 데이터를 보여주는 코드 */
	$(window).on("scroll", function(){
		console.log($(window).scrollTop() + "/" + $(document).height() + "/" + $(window).height());
		
		if($(window).scrollTop()==$(document).height()-$(window).height()){
			page += 1;
			fetchList()	
		}
	});
	
	/* 삭제버튼 예제 */
	$("#btnDel").on("click", function() {
		console.log("#btnDel");
		$("#del-pop").modal();
	});
	/* 삭제 버튼 클릭시 */
	$("#listArea").on("click", ".btnDelete", function(){
		var no = $(this).data("no");		//data-no 를 여기서 쓰는 것임!
		$("#modalNo").val(no);
		$("#del-pop").modal();
	});
	/* 팝업에 있는 삭제 버튼 클릭시 */	
	$("#btn_del").on("click", function() {
		console.log("#btn_del");
		var no = $("#modalNo").val();
		var password = $("#modalPassword").val();
		
		$("#modalPassword").val("");
		
		var modalGuestbookVo={
			no : no,
			password : password
		}
		console.log(modalGuestbookVo);
		
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/gb/api/delete",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(modalGuestbookVo),
			
			//받을 때 데이터 타입
			dataType : "json",
			success : function(no){
				console.log(modalGuestbookVo.no);
				if(no){
					$("#del-pop").modal("hide");
					$("#gbList"+modalGuestbookVo.no).remove();
					//window.location.reload()	 새로고침 하지 않고 지우는 방법으로 하는 것이 정석이라고..ㅎ
					console.log("제거 완료");
				} else {
					console.log("제거 실패");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	/* 글쓰기 버튼 클릭시 */
 	$("#btnInsert").on("click", function(){
		console.log("btnInsert");
		var guestbookVo = {
				no : 0,
				name : $("#name").val(),
				password : $("#password").val(),
				content : $(".textContent").val()
		}
		
		$.ajax({
			//보낼 때 데이터 타입
			url : "${pageContext.request.contextPath }/gb/api/insert",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),
			
			//받을 때 데이터 타입
			dataType : "json",
			success : function(guestbookVo){
				render(guestbookVo, "up");
				$("input[name='name']").val("");
				$("input[name='password']").val("");
				$("textarea[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	}); 
 	
	function fetchList(){
	
	
	$.ajax({
		//보낼 때 데이터 타입
		url : "${pageContext.request.contextPath }/gb/api/list",
		type : "post",
		/* 			contentType : "application/json",*/
		data : {
			page : page
		},

		//받을 때 데이터 타입
		dataType : "json",
		success : function(gList) {
			console.log(gList);

			for (var i = 0; i < gList.length; i++) {
				render(gList[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);

		}
	});
	}
	

	function render(gList, updown) {

		var str = "";
		str += "<li id='gbList"+gList.no+"'>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>[" + gList.no + "]</td>";
		str += "			<td>" + gList.name + "</td>";
		str += "			<td>" + gList.regDate + "</td>";
		str += "			<td><input class='btnDelete' type='button' value='삭제' data-no='"+ gList.no +"'/></a></td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4>" + gList.content + "</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "	<br>";
		str += "</li>";

		if (updown == "up") {
			$("#listArea").prepend(str);
		} else if (updown == "down") {
			$("#listArea").append(str);
		} else {
			console.log("updown 오류");
		}
	};
</script>



</html>