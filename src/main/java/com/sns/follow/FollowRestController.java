package com.sns.follow;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowRestController {

	
		// 팔로우 하기
		@RequestMapping("/create")
		public Map<String, Object> create(){
			
		}
	
	
	
}
