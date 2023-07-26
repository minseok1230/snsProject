package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	public List<Map<String, Object>> selectPostList();
	
	
	// 수정 게시글 update
	public int revisePost(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
}
