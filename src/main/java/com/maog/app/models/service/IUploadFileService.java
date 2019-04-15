package com.maog.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource load(String filename) throws IOException;
	
	public String copy(MultipartFile file);
	
	public boolean delete(String filename);
	
	public void deleteAll();
	
	public void init() throws IOException;
}
