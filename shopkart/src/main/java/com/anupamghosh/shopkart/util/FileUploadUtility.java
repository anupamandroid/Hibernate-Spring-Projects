package com.anupamghosh.shopkart.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = "E:\\JavaCode\\JavaBrains\\Projects\\workspace\\shopkart\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//to get REAL_PATH
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		
		//to check whether directory exists or not
		if(!new File(ABS_PATH).exists()){
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()){
			new File(REAL_PATH).mkdirs();
		}
		
		try{
			//Project Directory Upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			//Server Upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
