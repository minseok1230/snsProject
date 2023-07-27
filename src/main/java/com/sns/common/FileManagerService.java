package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

		private Logger logger = LoggerFactory.getLogger(this.getClass());
	
		// 실제 업로드가 된 이미지가 저장될 경로(서버 was)
		public static final String FILE_UPLOAD_PATH = "D:\\choiminseok\\6_spring_project\\sns\\images/";
		
		// input: 이미지파일(file), loginId(유일한 파일이름 만들기위해)
		// output: imagePath
		public String saveFile(String loginId, MultipartFile file) {
			String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
			String filePath = FILE_UPLOAD_PATH + directoryName;
			
			//폴더
			File directory = new File(filePath);
			if (directory.mkdir() == false) {
				// 폴더 만드는데 실패시 이미지 경로 mull로 리턴
				return null;
			}
			
			//★ ★★★★★★★★★★★ 질문!!!!
			byte[] bytes;
			try {
				bytes = file.getBytes();
				// !!!!! 한글 이름 이미지는 올릴 수 없다!!!!!
				Path path = Paths.get(filePath + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return "/images/" + directoryName + file.getOriginalFilename();
		}
		
		// 파일 삭제 메소드
		// input: imagePath
		// output: void
		public void deleteFile (String imagePath) {
			// db주소 원래의 파일 경로로 변환
			Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", "")); 
			
			// 삭제할 파일이 존재할 경우 이미지/폴더 삭제 
			if (Files.exists(path)) {
				//이미지 삭제
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.warn("######[FileManagerService 이미지 삭제 실패] imagePath:{}", imagePath);
				}
				
				// 폴더 삭제
				path = path.getParent();
				if (Files.exists(path)) {
					try {
						Files.delete(path);
					} catch (IOException e) {
						logger.info("#####[FileManagerService 폴더 삭제 실패] imagePath:{}", imagePath);
					}
				}
			}
			
		}
}















