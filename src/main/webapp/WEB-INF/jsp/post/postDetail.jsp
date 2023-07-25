<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 게시물 --%>
<div class="content d-flex justify-content-center">
  	<div class="w-50">
  		
  			${post.id }
	  		<%-- 카드 이미지 --%>
	  		<c:if test="${not empty post.imagePath}">
		  		<div class="image bg-info">
						<img src="${post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
			</c:if>
			
			<textarea id="content" class="content-box form-control" rows="5" placeholder="내용을 입력하세요">${post.content}</textarea>
			<div class="content-bottom d-flex justify-content-between">
				<div class="d-flex">
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 --%>
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif"
						class="d-none">

					<%-- 파일 업로드 버튼 --%>
					<a href="#" id="fileUploadBtn"><img width="35"
						src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장되는 곳 --%>
					<div id="fileName" class="ml-2 mt-2"></div>
				</div>
				<%-- 업로드 버튼  --%>
				<div class="d-flex">
					<button id="listBtn" class="w-btn w-btn-green2" type="button">목록</button>
					<button id="revisePostBtn" class="w-btn w-btn-green2" type="button">수정</button>
				</div>
				
			</div>
	  	
  	</div>
</div>

<script>
	$(document).ready(function(){
		/* 목록 버튼*/
		$('#listBtn').on('click', function(){
			location.href = "/timeline/myPost_view";
		});
		
		// 파일이미지 클릭 => 숨겨져 있는 type="file"을 동작시킨다.
		$('#fileUploadBtn').on('click', function(e) {
			e.preventDefault(); // a 태그의 스크롤 올라가는 현상 방지
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		});
		
		// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드 된 파일명 노출
		$('#file').on('change', function(e) {
			let fileName = e.target.files[0].name; // path-g6f39ad362_640.png
			// console.log(fileName);
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			// alert(ext);
			if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val("");  // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$('#fileName').text(''); // 파일 이름 비우기
				return;
			}
			
			// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
			$('#fileName').text(fileName);
		});
		
		
		$('#revisePostBtn').on('click', function(){
			/* postId 값을 넘겨야 하는데...ㅠㅠㅠ*/
			let content = $('#content').val();
			let file = $('#file').val();
			
			// validation
			if(!content) {
				alert("내용을 입력하세요");
				return;
			}
			
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				// request
				type: "post"
				, url: "post/revise"
				, data: formData
				, enctype: "multipart/form-data"
				, processData: false
				, contentType: false
				
				// response
				
				, success: function(data){
					if (data.code == 1) {
						// 수정 성공
						alert("글 수정이 성공했습니다.")
						location.href = "/timeline/myPost_view"
					} else{
						alert(data.errorMessage);
					}
				}
			
				, error:function(request, status, error){
					alert("글을 저장하는데 실패했습니다");
				}
				
				
			});
		});
	});
</script>



