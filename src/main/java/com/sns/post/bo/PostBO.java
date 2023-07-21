package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostEntity> getPostList(){
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public List<PostEntity> getPostListByUserId(int userId){
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = null;
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
			
	}
}
