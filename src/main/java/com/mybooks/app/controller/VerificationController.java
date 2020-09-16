//package com.mybooks.app.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.mybooks.app.entities.Book;
//import com.mybooks.app.entities.Verification;
//
//@RestController
//@RequestMapping("/verification")
//public class VerificationController {
//
//	@PostMapping(value = "/create",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Verification> create() {
//
//		Book uploadedBook = bookStorageService.uploadBook(verificationId, file);
//        return new ResponseEntity<>(uploadedBook, HttpStatus.OK);
//
//    }
//}
