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
  	
  		
  		<c:forEach items="${cardList}" var="card">
	  		<div class="content-menu mt-5 d-flex align-items-center justify-content-between">
	  			<div class="font-weight-bold ml-2">${card.user.loginId}</div>
	  			<div class="d-flex">
	  			<%-- 팔로우 : 내가 이미 팔로우 하고있는사람도 안보이게 할것 --%>
	  				<c:if test="${card.user.id != userId }">
	  					<button class="followBtn w-btn bg-secondary" type="button">팔로우</button>
	  				</c:if>
	  				<a href="" class="mr-2"><img width="30" src="https://www.iconninja.com/files/860/824/939/more-icon.png"></a>
	  			</div>
	  		</div>
	  		
	  		<%-- 카드 이미지 --%>
	  		<div class="image">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지" width="300">
			</div>
	  		
	  		<%-- 좋아요 하트  --%>
	  		<div class="d-flex content-bottom">
	  			<c:choose>
	  				<c:when test = "${card.filledLike == false}">
				  		<a href="#" class="like-btn" data-like-id="${card.post.id}"><img width="20" src="https://www.iconninja.com/files/214/518/441/heart-icon.png"></a>
				  	</c:when>
				  	<c:otherwise>
				  		<a href="#" class="like-btn" data-like-id="${card.post.id}"><img width="20" src="https://www.iconninja.com/files/527/809/128/heart-icon.png"></a>
				  	</c:otherwise>
			  	</c:choose>
		  		<div class="like mx-3 mt-1 font-weight-bold">좋아요 ${card.likeCount}개</div>
		  	</div>
		  	
		  	
		  	<%-- 본문 내용 --%>
		  	<div class="d-flex">
		  		<div class="mr-2">${card.user.loginId}</div>
		  		<div>${card.post.content}</div>
		  	</div>
		  	
		  	<%-- 댓글 제목 --%>
		  	<div class="comment-menu mt-3 mb-1">
		  		<span class="ml-2">댓글</span>
		  	</div>
		  	
		  	<%-- 댓글 --%>
			<div class="card-comment m-1">
				<c:forEach items="${card.commentList}" var="commentView">
						<div class="d-flex">
							<div class="mr-2">
								<span class="font-weight-bold">${commentView.user.loginId}</span>
								<span>${commentView.comment.content}</span>
							</div>
							<%-- 댓글 삭제 버튼 - 로그인 된 사람의 댓글일 때 삭제 버튼 노출 --%>
							<c:if test="${commentView.comment.userId == userId}">
								<div>
									<a href="/comment/delete_Comment?id=${commentView.comment.id}" data-comment-id="${commentView.comment.id}" class="comment-del-btn">
										<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
									</a>
								</div>
							</c:if>
						</div>
				</c:forEach>		
			</div>
	  		
	  		<%-- 댓글 쓰기 --%>
	  		<div class="d-flex">
	  			<input type="text" class="comment form-control col-11" placeholder="댓글 내용을 입력해주세요.">
	  			<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
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
	
	
	// 댓글 달기 btn 클릭
	$('.comment-btn').on('click', function(){
		//alert("ddd");
		
		// postId 가져오기
		let postId = $(this).data('post-id');
		//alert(postId);
		
		// comment 내용 가져오기 (1) ★★★
		let comment = $(this).siblings('.comment').val().trim(); 
		
		// comment 내용 가져오기 (2) ★★★
		//let comment = $(this).prev().val().trim();
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
					location.href = "/timeline/timeline_view";
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
	
	// 댓글 삭제 (a태그 말고 ajax이용)
	/* $('.comment-del-btn').on('click', function(e){
		e.preventDefault();
		let commentId =  $(this).data('comment-id');
		
		
		$.ajax({
			type: "delete"
			, url: "/comment/delete"
			, data: {"commentId": commentId}
		
			, success: function(data){
				if (data.code == 1){
					location.reload(true);
				} else{
					alert(data.errorMessage);
				}
			}
			, error: function(request, status, error){
				alert("댓글 삭제 실패했습니다.");
			}
		});
	}); */
	
	
	
	$('.like-btn').on('click', function(e){
		e.preventDefault();
		//alert("dddd");
		
		// 로그인 여부 확인 
		/* let userId = "${userId}";
		alert(userId); */
		
		let likePostId =  $(this).data('like-id');
		// alert(likePostId);
		
		$.ajax({
			type: "get"
			, url: "/like/" + likePostId
			
			, success: function(data){
				if (data.code == 1){
					location.reload(true);
				} else if (data.code == 300) {
					// 비로그인 시 로그인 페이지로 이동 
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				}
			}
			, error: function(request, status, error){
				alert("좋아요 실패했습니다. 관리자 문의 부탁드립니다.");
			}
		});
	});
	
	
	// 팔로우 버튼 클릭
	$('.followBtn').on('click', function(){
		//alert("ddd");
	})
	
	
});
</script>






