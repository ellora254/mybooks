package com.mybooks.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.mybooks.app.dao.BookDAO;
import com.mybooks.app.entities.Book;
import com.mybooks.app.entities.User;
import com.mybooks.app.service.BookStorageService;

@Service
public class BookStorageServiceImpl implements BookStorageService {

	@Autowired
	private TransferManager transferManager;
	
	@Autowired
	private BookDAO bookDAO;

	@Value("${mybooks.s3.bucket}")
	protected String bucketName;
	
	@Value("${mybooks.s3.basepath}")
	protected String basePath;
	
	@Override
	public Book uploadBook(Long verificationId, MultipartFile file) {
		String keyName = basePath + "/" + verificationId + "/" + file.getOriginalFilename();
		ObjectMetadata meta = new ObjectMetadata();
		meta.addUserMetadata("VERIFICATION_ID", verificationId + "");
		meta.setContentLength(file.getSize());
		meta.setContentType(file.getContentType());
		PutObjectRequest request = null;
		try {
			request = new PutObjectRequest(bucketName, keyName, file.getInputStream(), meta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Failed to create upload request");
		}
		request.setGeneralProgressListener(new ProgressListener() {
			@Override
			public void progressChanged(ProgressEvent progressEvent) {
				String transferredBytes = "Uploaded bytes: " + progressEvent.getBytesTransferred();
				System.out.println(transferredBytes);
			}
		});

		Upload upload = transferManager.upload(request);

		// Or you can block and wait for the upload to finish
		Book savedBook = null;
		try {

			upload.waitForCompletion();
			URL url = transferManager.getAmazonS3Client().getUrl(bucketName, keyName);
			System.out.println("BookStorageServiceImpl.uploadBook() = "+url);
			
			Book book = new Book();
			book.setFileName(file.getOriginalFilename());
			book.setFileType(file.getContentType());
			book.setFizeSize(file.getSize());
			book.setOwner(getCurrentUser());
			book.setFilePath(url.toString());
			
			savedBook = bookDAO.save(book);
			

		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return savedBook;
	}

	private User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * DOWNLOAD FILE from Amazon S3
	 * @return 
	 */
	@Override
	public Resource downloadFile(Long verificationId, Long bookId) {
		Optional<Book> optional = bookDAO.findByBookId(bookId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException("Invalid bookID");
		}
		
		Book book = optional.get();
		
		String key = book.getFilePath().substring(book.getFilePath().indexOf("verification"));
		final GetObjectRequest request = new GetObjectRequest(bucketName, key);
		
		
		request.setGeneralProgressListener(new ProgressListener() {
			@Override
			public void progressChanged(ProgressEvent progressEvent) {
				String transferredBytes = "Downloaded bytes: " + progressEvent.getBytesTransferred();
				System.out.println(transferredBytes);
			}
		});
		File file = null;
		try {
			String path = System.getProperty("user.home") + File.separator + book.getFileName();
			file = new File(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Download download = transferManager.download(request, file);
		
		try {
			
			download.waitForCompletion();
			
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		file.deleteOnExit();
		Resource resource = null;
		try {
			resource = new UrlResource(file.toURI());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resource;
	}
	
	
}
