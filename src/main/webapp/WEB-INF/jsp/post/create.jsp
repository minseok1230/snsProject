<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 게시물 입력 --%>
<div class="d-flex justify-content-center">
  	<div class="w-50">
  		<textarea id="content" class="content-box form-control" rows="5" placeholder="내용을 입력하세요"></textarea>
  		<div class="content-bottom d-flex justify-content-between">
  			<img width="40" src="data:image/png;base64,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">
  			<button id="postBtn" class="w-btn w-btn-green2" type="button">업로드</button>
  		</div>
  	</div>
</div>

<%-- 삭제버튼 누르기 --%>
<div class="d-flex justify-content-center">
  	<div class="w-50">
  		<div class="content-menu mt-5 d-flex align-items-center justify-content-between">
  			<div class="font-weight-bold ml-2">marobiana</div>
  			<a href="" class="mr-2"><img width="30" src="https://www.iconninja.com/files/860/824/939/more-icon.png"></a>
  		</div>
  	</div>
</div>

<%-- 게시물 --%>
<div class="content d-flex justify-content-center">
  	<div class="w-50">
  		<div class="image bg-info">이미지게시물</div>
  		<div class="d-flex content-bottom">
	  		<a href="#"><img width="20" src="https://www.iconninja.com/files/214/518/441/heart-icon.png"></a>
	  		<a href="#"><img width="20" src="https://www.iconninja.com/files/527/809/128/heart-icon.png"></a>
	  		<div class="like mx-3 mt-1 font-weight-bold">좋아요 11개</div>
	  	</div>
	  	<div>(아이디) 내용</div>
	  	<div class="comment-menu mt-2">
	  		<span class="ml-2">댓글</span>
	  	</div>
	  	
	  	<!-- 댓글 -->
	  	<div>테이블로 만드나??</div>
	  	
	  	<div class="d-flex">
	  		<input id="like" type="text" class="form-control col-11" placeholder="댓글 내용을 입력해주세요.">
	  		<a href="#" class="mt-1 ml-2">게시</a>
	  	</div>
  	</div>
  	
</div>





