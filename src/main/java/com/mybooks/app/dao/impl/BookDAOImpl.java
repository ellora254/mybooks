package com.mybooks.app.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mybooks.app.dao.BookDAO;
import com.mybooks.app.entities.Book;
import com.mybooks.app.repository.BookRepository;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public Optional<Book> findByBookId(Long bookId) {
		return bookRepository.findById(bookId);
	}
}
