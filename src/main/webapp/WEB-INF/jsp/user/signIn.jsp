<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="login-box">
		<h1 class="mb-4">로그인</h1>
		
		<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="input-group mb-3">
				<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>
	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			
			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<div class="d-flex align-items-center justify-content-between">
				<a class="btn  btn-dark h-100 w-50" href="/user/sign_up_view">회원가입</a>
				<input type="submit" id="loginBtn" class="btn  btn-primary h-100 w-50" value="로그인">
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		
		$("#loginForm").on('submit', function(e){
			e.preventDefault();
			
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			
			if (!loginId){
				alert("아이디를 입력하세요");
				return false;
			}
			if (!password){
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			$.post(url, params)
			.done(function(data){
				if (data.code == 1){
					// 로그인 성공하면 타임라인으로 이동 
					location.href = "/timeline/timeline_view"; 
				} else{
					alert(data.errorMessage); 
				}
			});
			
		});
	});
</script>














