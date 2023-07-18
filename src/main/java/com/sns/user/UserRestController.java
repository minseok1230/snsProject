package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserRestController {

		@Autowired
		private UserBO userBO;
		
	
		/**
		 * 아이디 중복 확인
		 * @param loginId
		 * @return
		 */
		@RequestMapping("/is_duplicated_id")
		public Map<String, Object> isDuplicatedId(
				@RequestParam("loginId") String loginId){
			
			Map<String, Object> result = new HashMap<>();
			result.put("usDuplicatedId", false);
			
			// select
			UserEntity userEntity = userBO.getUserEntityByLoginId(loginId);
			result.put("code", 1);
			if (userEntity != null) {
				result.put("usDuplicatedId", true);
			}
			return result;
		}
		
		/**
		 * 회원가입
		 * @param loginId
		 * @param password
		 * @param name
		 * @param email
		 * @return
		 */
		@PostMapping("sign_up")
		public Map<String, Object> signUp(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				@RequestParam("name") String name,
				@RequestParam("email") String email){
			
			// 비밀번호 해싱
			String hashedPassword = EncryptUtils.md5(password);
			
			//db insert
			Integer userId = userBO.addUser(loginId, hashedPassword, name, email);
			
			Map<String, Object> result = new HashMap<>();
			if (userId != null) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "회원가입 하는데 실패했습니다.");
			}
			return result;
		}
		
		/**
		 * 로그인
		 * @param loginId
		 * @param password
		 * @param session
		 * @return
		 */
		// 로그인
		@PostMapping("/sign_in")
		public Map<String, Object> signIn(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				HttpSession session){
			
			// 비밀번호 해싱
			String hashedPassword =  EncryptUtils.md5(password);
			
			// 로그인 정보 확인
			UserEntity userEntity = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
			
			// 로그인 처리 (session)
			Map<String, Object> result = new HashMap<>();

			if (userEntity != null) {
				// 로그인 성공
				session.setAttribute("userId", userEntity.getId());
				session.setAttribute("userLoginId", userEntity.getLoginId());
				session.setAttribute("userName", userEntity.getName());
				
				result.put("code", 1);
				result.put("result", "성공");
				
			} else {
				// 로그인 실패
				result.put("code", 500);
				result.put("errorMessage", "사용자 정보가 없습니다.");
			}
			return result;
		}
}
















