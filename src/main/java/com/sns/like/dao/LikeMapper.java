package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {

		// 좋아요 확인
		public int selectLikeToggle(
				@Param("postId") int postId, 
				@Param("userId") int userId);
		
		// 좋아요 갯수 (counting)
		public int selectCountLikeByPostId(int postId);
		
		// by postId userId, by postId  => 하나의 쿼리로 합친다.
		public int selectLikeCountByPostIdOrUserId(
				@Param("postId") int postId, 
				@Param("userId") Integer userId);
		
		
		// 좋아요 추가
		public void insertLikeToggle(
				@Param("postId") int postId, 
				@Param("userId") int userId);
		
		// 좋아요 삭제 
		public void deleteLikeToggle(
				@Param("postId") int postId, 
				@Param("userId") int userId);
		
		
		
	    
}
