package com.maog.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private static final String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);

		Resource recurso = new UrlResource(pathFoto.toUri());
		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto);
		}

		return recurso;
	}

	@Override
	public String copy(MultipartFile file) {
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFilename);

		Path rootAbsolutPath = rootPath.toAbsolutePath();

		log.info("rootPath: " + rootPath);
		log.info("rootAbsolutPath: " + rootAbsolutPath);

		try {

			Files.copy(file.getInputStream(), rootAbsolutPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		
		if(archivo.exists() && archivo.canRead()) {
			return archivo.delete();
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}

}
