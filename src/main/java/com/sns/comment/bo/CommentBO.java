package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public List<CommentEntity> getCommentList() {
		return commentRepository.findAll();
	};

	// CardView 만들기
	// input: postId
	// output: 가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int postId) {
		// 결과 리스트
		List<CommentView> commentViewList = new ArrayList<>();

		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 comment => commentView => commentViewList에 담는다.
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			commentView.setComment(comment);

			int num = comment.getUserId();
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user); // 댓글쓴이

			commentViewList.add(commentView);
		}

		return commentViewList;
	}
	
	
	// 댓글 삭제버튼 누르고 댓글 삭제 
	public void deleteCommentByIdByUserId(int id, int userId) {
		commentMapper.deleteCommentByIdByUserId(id, userId);
	}

}
