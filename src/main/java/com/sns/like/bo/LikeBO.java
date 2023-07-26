package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;

@Service
public class LikeBO {

		@Autowired
		private LikeMapper likeMapper;
	
		
		public void likeToggle(int postId, int userId) {
			if (getLikeToggle(postId, userId) > 0) {
				// 삭제
				deleteLikeToggle(postId, userId);
			} else {
				// 등록
				addLikeToggle(postId, userId);
			}
		}
		
		// 좋아요 여부 확인 (select)
		public int getLikeToggle(int postId, int userId) {
			return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		}
		
		// 좋아요 insert
		public void addLikeToggle(int postId, int userId) {
			likeMapper.insertLikeToggle(postId, userId);
		}
		
		// 좋아요 delete
		public void deleteLikeToggle(int postId, int userId) {
			likeMapper.deleteLikeToggle(postId, userId);
		}
		
		
		// 2. post별 좋아요 갯수 확인 
		// input: postId
		// output: 좋아요 개수(int)
		public int getCountLikeByPostId(int postId) {
			return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
		};
		
		// 3. 좋아요 클릭 여부 (하트)
		public boolean generateLikeView(int postId, int userId) {
			if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
				// 좋아요 존재
				return true;
			} else {
				return false;
			}
		}
		
		/* 강사님 풀이 (책임을 likeBO에게 넘긴다) */
//		public boolean filledLike(int postId, Integer userId) {
//			// 비로그인
//			if (userId == null) {
//				return false;
//			} 
//			
//			// 로그인 
//			return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
//		}
		
		
}




