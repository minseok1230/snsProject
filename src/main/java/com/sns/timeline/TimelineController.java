package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	// 게시물작성 + 전체글
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
		
		//postList (jpa)
		List<PostEntity> postList = postBO.getPostList();
		
		//localhost:8080/timeline/timeline_view
		model.addAttribute("postList", postList);
		model.addAttribute("view", "/timeline/timelineList");
		return "template/layout";
	}
	
	// 나의글 + 수정 (로그인된 사람만 볼수 있게)
	//localhost:8080/timeline/post_view
	@GetMapping("/post_view")
	public String postView(HttpSession session, Model model) {
		
		// 로그인된 사용자 id
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/timline/timeline_view";
		}
		
		// 나의 postList (jpa)
		List<PostEntity> myPostList = postBO.getPostListByUserId(userId);
		
				
		model.addAttribute("myPostList", myPostList);
		model.addAttribute("view", "/post/postList");
		return "template/layout";
	}
	
	
}
