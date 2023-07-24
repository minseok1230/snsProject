package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.dao.CommentRepository;
import com.sns.comment.entity.CommentEntity;

@Service
public class CommentBO {

		@Autowired
		private CommentMapper commentMapper;
		
		@Autowired
		private CommentRepository commentRepository;
	
		// 댓글 db insert
		public int addComment(int postId, int userId, String content) {
			return commentMapper.insertComment(postId, userId, content);
		}
		
		
		/* 댓글 select */
		public List<CommentEntity> getCommentList(){
			return commentRepository.findAll();
		};
		
}
