<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h2 class="mb-4 mt-3">회원가입</h2>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<div class="w-100">
				
				<!-- 아이디 -->
				<label for="loginId">ID</label>
				<div class="d-flex">
					<input type="text" class="form-control col-6" id="loginId" name="loginId" placeholder="ID를 입력하세요">
					<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button><br>
				</div>
				<%-- 아이디 체크 결과 --%>
				<%-- d-none 클래스: display none (보이지 않게) --%>
				<div>
					<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
					<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
				</div>
				
				<!-- 비밀번호 -->
				<label for="password">비밀번호</label>
				<div class="d-flex">
					<input type="password" class="form-control col-6" id="password" name="password" placeholder="비밀번호">
				</div>
				
				<!-- 비밀번호 확인 -->
				<label for="confirmPassword">비밀번호 확인</label>
				<div class="d-flex">
					<input type="password" class="form-control col-6" id="confirmPassword" name="confirmPassword" placeholder="비밀번호">
				</div>
				
				<!-- 이름 -->
				<label for="name">이름</label>
				<div class="d-flex">
					<input type="text" class="form-control col-6" id="name" name="name" placeholder="이름을 입력하세요">
				</div>
				
				<!-- 이메일 -->
				<label for="email">이메일</label>
				<div class="d-flex">
					<input type="text" class="form-control col-6" id="email" name="email" placeholder="이메일을 입력하세요">
				</div>
				
				<button type="submit" id="signUpBtn" class="btn btn-primary">가입하기</button>				
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		// 중복 확인 버튼 클릭
		$("#loginIdCheckBtn").on('click', function(){
			//alert("확인")
			
			// 경고 문구 초기화
			$('#idCheckLength').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			
			let loginId = $('#loginId').val().trim();
			if (loginId.length < 4){
				$('#idCheckLength').removeClass('d-none');
				return;
			}
			
			$.ajax({
				// request
				url: "/user/is_duplicated_id"
				, data: {"loginId": loginId}
			
				// response
				, success: function(data){
					if (data.isDuplicatedId) {
						// 중복
						$('#idCheckDuplicated').removeClass('d-none');
					} else {
						// 중복 아님 => 사용 가능
						$('#idCheckOk').removeClass('d-none');
					}
				}
				, error: function(request, status, error){
					alert("중복확인에 실패했습니다.");
				}
			});
		});
	});
</script>











