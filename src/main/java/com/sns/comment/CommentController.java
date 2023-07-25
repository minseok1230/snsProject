package com.sns.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.bo.CommentBO;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/delete_Comment")
	public String deleteComment(
			@RequestParam("id") int id,
			HttpSession session) {
		
		// session (userId)
		int userId = (int)session.getAttribute("userId");
		
		// db 삭제 
		commentBO.deleteCommentByIdByUserId(id, userId);
		
		return "redirect:/timeline/timeline_view";
	}
}
