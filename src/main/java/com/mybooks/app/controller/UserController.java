package com.mybooks.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybooks.app.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> fetchCustomer(@RequestParam("userId") Long userId) {

        return new ResponseEntity<User>(new User(), HttpStatus.OK);

    }
}
