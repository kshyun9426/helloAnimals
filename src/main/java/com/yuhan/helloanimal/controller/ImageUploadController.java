package com.yuhan.helloanimal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yuhan.helloanimal.vo.CommunityAttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class ImageUploadController {

	@PostMapping(value="/uploadAjaxAction", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<CommunityAttachVO>> uploadImages(MultipartFile[] uploadFile){
		
		List<CommunityAttachVO> list = new ArrayList<>();
		String uploadFolder = "C:\\uploadEx";
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------업로드 할 이미지 정보------------------");
			log.info("upload File Name: " + multipartFile.getOriginalFilename());
			log.info("upload File Size: " + multipartFile.getSize());
			
			CommunityAttachVO attachVO = new CommunityAttachVO();
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE has file path, 파일명만 가져오기 위한 작업(getOriginalFilename()메서드 호출 시 IE는 경로명까지 포함해서 반환을 하기때문에)
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			attachVO.setImagename(uploadFileName);
			
			//같은 파일명 누락 안되도록 처리
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath,uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					attachVO.setUuid(uuid.toString());
					attachVO.setUploadpath(uploadFolderPath);
					thumbnail.close();
				}
				list.add(attachVO);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("출력 할 fileName: " + fileName);
		
		if(fileName == null || fileName.isEmpty() || fileName.length() == 0) {
			return null;
		}
		
		File file = new File("C:\\uploadEx\\" + fileName);
		log.info("출력 할 file(경로 포함): " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName){
		File file;
		try {
			file = new File("C:\\uploadEx\\"+URLDecoder.decode(fileName,"UTF-8"));
			file.delete();
			String largeFileName = file.getAbsolutePath().replace("s_", "");
			file = new File(largeFileName);
			file.delete();
		}catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String str = sdf.format(todayDate);
		return str.replace("-", File.separator); //시스템마다 다르기 때문에
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

































