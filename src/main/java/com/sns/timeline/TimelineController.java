package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.entity.CommentEntity;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	
	/* 댓글 삭제하기 / 댓글 내용 뿌리기 */
	
	// 나중에 필요없게 된다.
	@Autowired
	private PostBO postBO;
	
	@Autowired 
	private CommentBO commentBO;
	
	// 게시물작성 + 전체글
	//localhost:8080/timeline/timeline_view
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
		
		/* 비효율적인 방법 */
		// postList (jpa)
		// List<PostEntity> postList = postBO.getPostList();
		
		// commentList (jpa)
		// List<CommentEntity> commentList = commentBO.getCommentList();
		
		// model.addAttribute("commentList", commentList);
		// model.addAttribute("postList", postList);
		
		List<CardView> cardList =  timelineBO.generateCardViewList();
		
		model.addAttribute("cardList", cardList);
		model.addAttribute("view", "/timeline/timelineList");
		return "template/layout";
	}
	
	
	
	
	// 추가적인 나의 옵션
	
	// 나의글 + 수정 (로그인된 사람만 볼수 있게)
	//localhost:8080/timeline/post_view
	
	@GetMapping("/myPost_view")
	public String postView(HttpSession session, Model model) {
		
		// 로그인된 사용자 id
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/timline/timeline_view";
		}
		
		// 나의 postList (jpa)
		List<PostEntity> myPostList = postBO.getPostListByUserId(userId);
		List<CommentEntity> commentList = commentBO.getCommentList();
				
		model.addAttribute("commentList", commentList);
		model.addAttribute("myPostList", myPostList);
		model.addAttribute("view", "/post/postList");
		return "template/layout";
	}
	
	
	
	
	
}
