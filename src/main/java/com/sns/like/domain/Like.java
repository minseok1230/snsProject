package com.sns.like.domain;

import lombok.Data;

@Data
public class Like {

	private int postId;
	private int userId;
	private int createdAt;
}
