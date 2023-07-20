package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostRestController {

		@PostMapping("/create")
		public Map<String, Object> create(
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file,
				HttpSession session){
			
			//session
			int userId = (int)session.getAttribute("userId");
			
			//db insert
			
			Map<String, Object> result = new HashMap<>();
			result.put("code", 1);
			result.put("result", "성공");
			
			return result;
		}
}
