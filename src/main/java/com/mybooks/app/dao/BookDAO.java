package com.mybooks.app.dao;

import java.util.Optional;

import com.mybooks.app.entities.Book;

public interface BookDAO {

	Book save(Book book);

	Optional<Book> findByBookId(Long bookId);

}
