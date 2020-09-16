package com.mybooks.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.mybooks.app.entities.Book;

public interface BookStorageService {

	Book uploadBook(Long verificationId, MultipartFile file);

	/**
	 * DOWNLOAD FILE from Amazon S3
	 * @return 
	 */
	Resource downloadFile(Long verificationId, Long fileId);

}
