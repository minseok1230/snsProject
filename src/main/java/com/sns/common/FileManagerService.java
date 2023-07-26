package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

		// 실제 업로드가 된 이미지가 저장될 경로(서버 was)
		public static final String FILE_UPLOAD_PATH = "E:\\6_Spring_Project\\sns\\workspace\\images/";
		
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
}















