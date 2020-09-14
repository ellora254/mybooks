package com.mybooks.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybooks.app.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
