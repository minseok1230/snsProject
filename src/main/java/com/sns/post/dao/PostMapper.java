package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	public List<Map<String, Object>> selectPostList();
	
	
	// 수정 게시글 update
	public int updatePostByUserIdAndPostId(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	// post 삭제시 comment 삭제
	public void deleteCommentByPostId(int postId);
	
	// post 삭제시 like 삭제
	public void deleteLikeByPostId(int postId);
	
	public int deletePostByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}
