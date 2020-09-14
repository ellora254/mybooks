package com.mybooks.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybooks.app.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
