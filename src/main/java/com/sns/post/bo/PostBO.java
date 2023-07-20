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
	
	public int addPost(String content, MultipartFile file) {
		
		String imagePath = null;
		return save(
				builder()
				.content
				.imagePath
				.build()
				);
				
		
	}
	
	
//	public CompanyEntity addCompany(String name, String business, String scale, int headcount) {
//		return companyRepository.save(
//			CompanyEntity.builder()
//				.name(name)
//				.business(business)
//				.scale(scale)
//				.headcount(headcount)
//				.build()
//		);
//	}
}
