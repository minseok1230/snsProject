package com.sns.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer>{
		
		// 게시물 전체
		public List<PostEntity> findAllByOrderByIdDesc();
		
		// 나의 게시물
		public List<PostEntity> findByUserIdOrderByIdDesc(int userId); 
		
		// 새로운 게시물 insert
		
		
	
}
