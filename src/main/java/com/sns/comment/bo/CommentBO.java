package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.dao.CommentRepository;
import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.entity.CommentEntity;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

		@Autowired
		private CommentMapper commentMapper;
		
		@Autowired
		private CommentRepository commentRepository;
		
		@Autowired
		private UserBO userBO;
	
		// 댓글 db insert
		public int addComment(int postId, int userId, String content) {
			return commentMapper.insertComment(postId, userId, content);
		}
		
		
		/* 댓글 select */
		public List<CommentEntity> getCommentList(){
			return commentRepository.findAll();
		};
		
		
		// CardView 만들기 
		// input: postId
		// output: 가공된 댓글 리스트 
		public List<CommentView> generateCommentViewList(int postId){
			// 결과 리스트
			List<CommentView> commentViewList = new ArrayList<>();
			
			// 글에 해당하는 댓글들
			// postId:1 ; postId:2 ; postId:3 ...
			List<Comment> commentList = commentMapper.selectCommentByPostId(postId);
			
			// 반복문 순회 comment => commentView => commentViewList에 담는다.
			for (int i = 0; i < commentList.size(); i++) {
				CommentView commentView = new CommentView();
				commentView.setComment(commentList.get(i));
				
				// 글쓴이 
				int userId = commentView.getComment().getUserId();
				UserEntity user = userBO.getUserEntityById(userId);
				commentView.setUser(user);
				
				commentViewList.add(commentView);
			}
			
			return commentViewList;
		}
		
}








