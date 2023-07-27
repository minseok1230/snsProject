package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostMapper;
import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {
	
	// logger 불러오기
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<PostEntity> getPostList(){
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public List<PostEntity> getPostListByUserId(int userId){
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	
	/**
	 * 게시물 등록 
	 * @param userId
	 * @param userLoginId
	 * @param content
	 * @param file
	 * @return
	 */
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = null;
		
		// 이미지가 있으면 업로드 후 imagePath 받아옴
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	/**
	 * 수정할 나의 게시물 가져오기
	 * @param postId
	 * @param userId
	 * @return
	 */
	public PostEntity getPostByPostIdAndUserId(int postId, int userId) {
		return postRepository.findAllByIdAndUserId(postId, userId);
	};
	
	
	
	// 게시물 수정하기(나의 게시글 보기)
	// postBO.revisePost(userId, postId , userLoginId, content, file);
	public int revisePost(int userId, int postId, String userLoginId, String content, MultipartFile file) {
		PostEntity postEntity = postRepository.findAllByIdAndUserId(postId, userId);
		// postEntity validation (글 존재 여부)
			if (postEntity == null) {
				logger.warn("###[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
				return 0;
			}
		
			String imagePath = null;
		
		// 이미지가 있으면 업로드 후 imagePath 받아옴
			if (file != null) {
				imagePath = fileManager.saveFile(userLoginId, file);
				
				// 기존 이미지 제거 
				if (imagePath != null && postEntity.getImagePath() != null) {
					fileManager.deleteFile(postEntity.getImagePath());
				}
			}
		return postMapper.updatePostByUserIdAndPostId(userId, postId, content, imagePath);
	}
	
	
	// postId: 7 userId: 2
	// 게시물 삭제
	public int deletePostByPostIdByUserId(int postId, int userId) {
		// 삭제할 게시물 가져오기
		PostEntity postEntity =  postRepository.findAllByIdAndUserId(postId, userId);
		
		// postEntity validation (글 존재 여부)
		if (postEntity == null) {
			logger.warn("###[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return 0;
		}
		
		// 이미지 존재하면 images에 있는 이미지도 삭제 
		if (postEntity.getImagePath() != null) {
			fileManager.deleteFile(postEntity.getImagePath());
		}
		
		// 댓글삭제 (postId)
		postMapper.deleteCommentByPostId(postId); 
		
		// 좋아요 삭제 (postId)
		postMapper.deleteLikeByPostId(postId);
		
		// post 삭제
		
		return postMapper.deletePostByPostIdAndUserId(postId, userId);
	}
	
};












