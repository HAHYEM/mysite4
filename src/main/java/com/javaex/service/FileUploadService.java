package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class FileUploadService {

	// 갤러리 불러오기 기능
	
		@Autowired
		GalleryDao galleryDao;
		
		public List<GalleryVo> load() {
			return galleryDao.getFileList();
		}
		
	
	public String restore(MultipartFile file) {
		String saveDir = "D:\\javaStudy\\upload";
		
		GalleryVo galleryVo = new GalleryVo();
		
		//파일 정보 수집
		
		//원 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		
		//파일위치(패스)
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		
		//vo만들어서 dao통해서 저장
		galleryVo.setOrgName(orgName);
		galleryVo.setExName(exName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		
		galleryDao.insert(galleryVo);
		
		//파일 copy
		try{
			
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		return saveName; 
	}
	
	public List<GalleryVo> getList(){
		List<GalleryVo> gList = galleryDao.getFileList();
		System.out.println(gList.toString());
		return gList;
	}
	
	public void delete(int no){
		galleryDao.delete(no);
	}
	
}
