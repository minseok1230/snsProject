package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

	// 게시물작성 + 본인글
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
		
		//localhost:8080/timeline/timeline_view
		model.addAttribute("view", "/post/create");
		return "template/layout";
	}
}
