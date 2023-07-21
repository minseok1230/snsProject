package com.sns.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.comment.entity.CommentEntity;

@Repository
public interface CommentRepository {
	public List<CommentEntity> findAll();
}
