package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

		@Autowired
		private PostBO postBO;
		
		// 게시물 등록 
		@PostMapping("/create")
		public Map<String, Object> create(
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file,
				HttpSession session){
			
			//session
			int userId = (int)session.getAttribute("userId");
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			//db insert
			postBO.addPost(userId, userLoginId, content, file);
			
			Map<String, Object> result = new HashMap<>();
			result.put("code", 1);
			result.put("result", "성공");
			
			return result;
		}
		
		@PostMapping("/revise")
		public Map<String, Object> revise(
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file,
				HttpSession session){
			
			//session
			int userId = (int)session.getAttribute("userId");
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			
			// db modify
			
			
			Map<String, Object> result = new HashMap<>();
			result.put("code", 1);
			result.put("result", "성공");
			
			return result;
		}
		
}








