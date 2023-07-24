<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h-100 d-flex align-items-center justify-content-between">
	<div>
		<h1 class="font-weight-bold ml-4">Marondalgram</h1>
	</div>
	
	<div class="d-flex align-items-end">
	
	<%-- 로그인이 된 경우에만 로그인정보/로그아웃 노출 --%>
		<c:if test="${not empty userName}">
			<span class="mr-3">${userName}님 안녕하세요</span>
			<a href="/timeline/timeline_view" class="mr-3">전체 게시물</a>
			<a href="/timeline/myPost_view" class="mr-3">나의 게시물</a>
			<a href="/user/sign_out">로그아웃</a>
		</c:if>
	</div>
</div>

