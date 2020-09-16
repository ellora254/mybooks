package com.mybooks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mybooks.app.entities.Book;
import com.mybooks.app.service.BookStorageService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookStorageService bookStorageService;

	@PostMapping(value = "/upload/verification/{verificationId}/book",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> uploadBook(@PathVariable("verificationId") Long verificationId, 
			@RequestParam("file") MultipartFile file) {

		Book uploadedBook = bookStorageService.uploadBook(verificationId, file);
        return new ResponseEntity<>(uploadedBook, HttpStatus.OK);

    }
	
	@GetMapping(value = "/download/verification/{verificationId}/book/{bookId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadBook(@PathVariable("verificationId") Long verificationId, 
			@PathVariable("bookId") Long bookId) {

		Resource downloadFile = bookStorageService.downloadFile(verificationId, bookId);
        return new ResponseEntity<>(downloadFile, HttpStatus.OK);

    }
	
}
