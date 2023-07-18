package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserRepository;
import com.sns.user.entity.UserEntity;

@Service
public class UserBO {

		@Autowired
		private UserRepository userRepository;
	
		// 아이디 중복 확인
		public UserEntity getUserEntityByLoginId(String loginId) {
			return userRepository.findByLoginId(loginId);
		}
		
		// 데이터 등록 (회원가입)
		public Integer addUser(String loginId, String password, String name, String email) {
			
			UserEntity userEntity = userRepository.save(
					UserEntity.builder()
					.loginId(loginId)
					.password(password)
					.name(name)
					.email(email)
					.build()
					);
			return userEntity == null ? null : userEntity.getId();
		}
		
		// 로그인 정보 확인
		public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
			return userRepository.findByLoginIdAndPassword(loginId, password);
		}
		
}
