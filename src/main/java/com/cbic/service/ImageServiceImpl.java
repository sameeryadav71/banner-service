package com.cbic.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{

	@Override
	public String uploadImge(String path, MultipartFile multipartFile) throws IOException {
		// TODO Auto-generated method stub
		String name = multipartFile.getOriginalFilename();
		String filePath = path+File.separator+(UUID.randomUUID().toString()).concat(name.substring(name.lastIndexOf(".")));;
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws IOException {
		// TODO Auto-generated method stub
		String fulFilePath = path+File.separator+fileName;
		InputStream inputStream = new FileInputStream(fulFilePath);
		return inputStream;
	}

}
