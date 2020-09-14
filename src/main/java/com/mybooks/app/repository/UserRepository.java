package com.mybooks.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybooks.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
