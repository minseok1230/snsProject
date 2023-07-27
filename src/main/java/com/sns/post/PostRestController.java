package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		
		/**
		 * 게시물 등록
		 * @param content
		 * @param file
		 * @param session
		 * @return
		 */
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
		
		/**
		 * 게시물 수정(나의 게시물)
		 * @param content
		 * @param file
		 * @param postId
		 * @param preImagePath
		 * @param session
		 * @return
		 */
		@PostMapping("/revise")
		public Map<String, Object> revise(
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam("postId") int postId,
				HttpSession session){
			
			//session
			int userId = (int)session.getAttribute("userId");
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			Map<String, Object> result = new HashMap<>();
			
			// userId & postId 일치할때만 변경 가능 
			// db revise
			int isRevise =  postBO.revisePost(userId, postId , userLoginId, content, file);
			
			if (isRevise > 0) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "수정에 실패하였습니다.");
			}
			
			
			return result;
		}
		
		// 게시물 삭제
		@DeleteMapping("/delete")
		public Map<String, Object> delete(
				@RequestParam("postId") int postId,
				HttpSession session){
			
			// session(userId)
			int userId = (int)session.getAttribute("userId");
			
			// db 삭제
			
			int successDelete = postBO.deletePostByPostIdByUserId(postId, userId);
			Map<String, Object> result = new HashMap<>();
			
			// 결과
			if (successDelete > 0) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "게시물 삭제 실패하였습니다.");
			}
			
			return result;
		}
		
		
}





















