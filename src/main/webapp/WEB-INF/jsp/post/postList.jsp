<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 게시물 --%>
<div class="content d-flex justify-content-center">
  	<div class="w-50">
  		<c:forEach items="${cardList}" var="card">
	  		<div class="content-menu mt-5 d-flex align-items-center justify-content-between">
	  			<div class="font-weight-bold ml-2">${card.user.loginId}</div>
	  			<div class="d-flex">
		  			<a href="/post/post_detail_view?postId=${card.post.id}" id="postChangeBtn" class="btn btn-sm btn-secondary">수정</a>
		  			<a href="" class="mr-2"><img width="30" src="https://www.iconninja.com/files/860/824/939/more-icon.png"></a>
		  		</div>
	  		</div>
	  		
	  		<%-- 카드 이미지 --%>
	  		<div class="image bg-info">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
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
				<c:forEach items="${card.commentList}" var="comment">
						<div class="d-flex">
							<div class="mr-2">
								<span class="font-weight-bold">${comment.user.loginId}</span>
								<span>${comment.comment.content}</span>
							</div>
							<%-- 댓글 삭제 버튼 --%>
							<c:if test="${comment.comment.userId == userId}">
								<div>
									<a href="/comment/delete_Comment?id=${comment.comment.id}" class="comment-del-btn">
										<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
									</a>
								</div>
							</c:if>
						</div>
				</c:forEach>		
				
			</div>
	  		
	  		<%-- 댓글 쓰기 --%>
	  		<div class="d-flex">
	  			<input id="like" type="text" class="form-control col-11" placeholder="댓글 내용을 입력해주세요.">
	  			<a href="#" class="mt-1 ml-2">게시</a>
	  		</div>
	  	</c:forEach>
  	</div>
</div>

<script>
 $(document).ready(function(){
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
 });
</script>



















