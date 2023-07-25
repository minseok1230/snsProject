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
		
		// 좋아요 여부 확인
		public int getLikeToggle(int postId, int userId) {
			return likeMapper.selectLikeToggle(postId, userId);
		}
		
		// 좋아요 insert
		public void addLikeToggle(int postId, int userId) {
			likeMapper.insertLikeToggle(postId, userId);
		}
		
		// 좋아요 delete
		public void deleteLikeToggle(int postId, int userId) {
			likeMapper.deleteLikeToggle(postId, userId);
		}
		
		
		// post별 좋아요 갯수 확인 
		public int countLikeByPostId(int postId) {
			return likeMapper.selectCountLikeByPostId(postId);
		};
		
		
}




