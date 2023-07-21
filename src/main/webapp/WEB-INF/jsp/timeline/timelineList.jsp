<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 게시물 입력 --%>
<div class="d-flex justify-content-center">
  	<div class="w-50">
  		<textarea id="content" class="content-box form-control" rows="5" placeholder="내용을 입력하세요"></textarea>
  		<div class="content-bottom d-flex justify-content-between">
  			<div class="d-flex">
  				<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 --%>
  				<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none"> 
  				
  				<%-- 파일 업로드 버튼 --%>
  				<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>	
  				
  				<%-- 업로드 된 임시 파일 이름 저장되는 곳 --%>
  				<div id="fileName" class="ml-2 mt-2"></div>
  				
  			</div>	
  			
  			<%-- 업로드 버튼  --%>
  			<button id="postBtn" class="w-btn w-btn-green2" type="button">업로드</button>
  		</div>
  	</div>
</div>


<%-- 게시물 --%>
<div class="content d-flex justify-content-center">
  	<div class="w-50">
  		<c:forEach items="${postList}" var="post">
	  		<div class="content-menu mt-5 d-flex align-items-center justify-content-between">
	  			<div class="font-weight-bold ml-2">${post.userId}</div>
	  			<a href="" class="mr-2"><img width="30" src="https://www.iconninja.com/files/860/824/939/more-icon.png"></a>
	  		</div>
	  		
	  		<%-- 카드 이미지 --%>
	  		<div class="image bg-info">
					<img src="${post.imagePath}" class="w-100" alt="본문 이미지">
			</div>
	  		
	  		<%-- 좋아요 하트  --%>
	  		<div class="d-flex content-bottom">
		  		<a href="#"><img width="20" src="https://www.iconninja.com/files/214/518/441/heart-icon.png"></a>
		  		<a href="#"><img width="20" src="https://www.iconninja.com/files/527/809/128/heart-icon.png"></a>
		  		<div class="like mx-3 mt-1 font-weight-bold">좋아요 11개</div>
		  	</div>
		  	
		  	<%-- 본문 내용 --%>
		  	<div class="d-flex">
		  		<div class="mr-2">${post.userId}</div>
		  		<div>${post.content}</div>
		  	</div>
		  	
		  	<%-- 댓글 제목 --%>
		  	<div class="comment-menu mt-3 mb-1">
		  		<span class="ml-2">댓글</span>
		  	</div>
		  	
		  	<%-- 댓글 --%>
			<div class="card-comment m-1">
				<c:forEach items="${commentList}" var="comment">
					<c:if text="${post.id == comment.postid}">
						<span class="font-weight-bold">${comment.userId}</span>
						<span>${comment.content}</span>
					</c:if>
				</c:forEach>		
				<%-- 댓글 삭제 버튼 --%>
				<a href="#" class="comment-del-btn">
					<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
				</a>
			</div>
	  		
	  		<%-- 댓글 쓰기 --%>
	  		<div class="d-flex">
	  			<input type="text" class="comment form-control col-11" placeholder="댓글 내용을 입력해주세요.">
	  			<button type="button" class="comment-btn btn btn-light" data-post-id="${post.id}">게시</button>
	  		</div>
	  	</c:forEach>
  	</div>
</div>


<script>
$(document).ready(function() {
	// 파일이미지 클릭 => 숨겨져 있는 type="file"을 동작시킨다.
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // a 태그의 스크롤 올라가는 현상 방지
		$('#file').click(); // input file을 클릭한 것과 같은 효과
	});
	
	// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드 된 파일명 노출
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name; // path-g6f39ad362_640.png
		console.log(fileName);
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		alert(ext);
		if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$('#file').val("");  // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
			$('#fileName').text(''); // 파일 이름 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$('#fileName').text(fileName);
	});
	
	
	// 파일 업로드 버튼 클릭
	$('#postBtn').on('click', function(){
		
		let content =  $('#content').val();
		let file = $('#file').val();
		
		// validation
		if(!content) {
			alert("내용을 입력하세요");
			return;
		}
		
		// AJAX 통신
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); 
		
		$.ajax({
			// request
			type: "post"
			, url: "/post/create" 
			, data: formData
			, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정
			, processData: false // 파일 업로드를 위한 필수 설정
			, contentType: false // 파일 업로드를 위한 필수 설정
			
			// response
			
			,success: function(data){
				if (data.code == 1){
					// 성공
					alert("타임라인글이 저장되었습니다.");
					location.href = "/timeline/timeline_view";
				} else{
					// 로직상 실패 
					alert(data.errorMessage);
				}
			}
		
			, error:function(request, status, error){
				alert("글을 저장하는데 실패했습니다");
		}
		});
	});
	
	$('.comment-btn').on('click', function(){
		//alert("ddd");
		
		// postId 가져오기
		let postId = $(this).data('post-id');
		//alert(postId);
		
		// comment 내용 가져오기 ★★★
		let comment = $(this).siblings('.comment').val(); 
		//alert(comment);
		
		// validation
		if (!comment){
			alert("댓글을 입력하세요");
			return false;
		}
		
		
		$.ajax({
			//request
			url: "/comment/create"
			, data: {"postId": postId, "content": comment}
			
			//response
			, success: function(data){
				if (data.code == 1){
					// 댓글 등록 성공
					alert(data.result)
				} else {
					// 댓글 등록 실패
					alert(data.errorMessage)
				}
			}
			
			,error: function(request, status, error){
				alert("댓글 관리자에게 문의해주세요.");
			}
		});
	});
});
</script>






